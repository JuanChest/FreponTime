package Logic;

import java.io.File;
import java.util.ArrayList;

public class GestorReserva {
    private ArrayList<Reserva> reservasDeEstudiantes;
    private ArrayList<Juego> juegos;
    private GestorEstudiante gestorEstudiante;
    private GestorPago gestorPago;
    private File reservasFile;
    private File juegosFile;
    private File reservasEstudiantesFile;
    private String verificacionDePago = "CANCELADO24";

    private static GestorReserva instance;

    private GestorReserva(){
        reservasDeEstudiantes = new ArrayList<Reserva>();
        juegos = new ArrayList<Juego>();
        gestorEstudiante = new GestorEstudiante();
        gestorPago = new GestorPago();

        reservasFile = new File("src/Datos/Reserva.txt");
        juegosFile = new File("src/Datos/Juegos.txt");
        reservasEstudiantesFile = new File("src/Datos/ReservasDeEstudiante.txt");

        GestorArchivos.cargarJuegos(this, juegosFile);
        GestorArchivos.cargarReservas(this, reservasFile);
        GestorArchivos.cargarReservasDeEstudiantes(this, reservasEstudiantesFile);

        cargarPagosYTicket();
    }

    private void cargarPagosYTicket() {
        for(int i =0; i < gestorPago.getPagos().size(); i++){
            gestorPago.getPagos().get(i).setTicket(gestorPago.getTickets().get(i));
            reservasDeEstudiantes.get(i).setPago(gestorPago.getPagos().get(i));
        }
    }

    public static GestorReserva getInstance() {
        if (instance == null) {
            instance = new GestorReserva();
        }
        return instance;
    }

    public Reserva crearReserva(Juego juego, Horario horario) {
        Reserva potencialReserva = null;
        if(existirReservasDuplicadas(juego, horario)){
            System.out.println("Ya existe una reserva para este juego y horario.");
            return null;
        }
        for(Estudiante estudianteEnLínea: gestorEstudiante.getEstudiantes()){
            if(estudianteEnLínea.isEnLínea()){
                Reserva nuevaReserva = new Reserva(reservasDeEstudiantes.size(), juego, horario);
                potencialReserva = nuevaReserva;
                estudianteEnLínea.getNumerosDeReservas().add(nuevaReserva.getNumero());
                reservasDeEstudiantes.add(nuevaReserva);

                gestorPago.crearPagoDeReserva(nuevaReserva, reservasDeEstudiantes, juego);
                GestorArchivos.guardarReservas(this, reservasFile);
                GestorArchivos.guardarReservasDeEstudiantes(this, reservasEstudiantesFile);
                return potencialReserva;
            }
        }
        return potencialReserva;
    }

    public boolean existirReservasDuplicadas(Juego juego, Horario horario) {
        for(int i = 0; i < reservasDeEstudiantes.size(); i++){
            if(reservasDeEstudiantes.get(i).getJuego().equals(juego) && reservasDeEstudiantes.get(i).getHorario().equals(horario)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Juego> getJuegosDisponibles() {
        ArrayList<Juego> auxJuegos = new ArrayList();
        for(int i = 0; i < juegos.size(); i++){
            if(juegos.get(i).getEstado()){
                auxJuegos.add(juegos.get(i));
            }
        }
        return auxJuegos;
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        return gestorEstudiante.iniciarSesion(correo, contrasena);
    }

    public boolean enviarCodigo(Estudiante estudianteAux) {
        return gestorEstudiante.enviarCodigo(estudianteAux);
    }

    public boolean buscarEstudiante(String usuario) {
        return gestorEstudiante.buscarEstudiante(usuario);
    }
    public Estudiante buscarEstudiante(boolean estadoEstudiante){
        return gestorEstudiante.buscarEstudiante(estadoEstudiante);
    }

    public boolean verificarCodigo(String codigo) {
        return gestorEstudiante.verificarCodigo(codigo);
    }

    public void preservarPosibleEstudiante(Estudiante estudianteAux) {
        gestorEstudiante.preservarPosibleEstudiante(estudianteAux);
    }

    public void guardarPosibleEstudiante() {
        gestorEstudiante.guardarPosibleEstudiante();
    }

    public boolean buscarCorreo(String correoElectrónico) {
        return gestorEstudiante.buscarCorreo(correoElectrónico);
    }

    public void agregarJuegos(Juego juego) {
        juegos.add(juego);
    }

    public void agregarReserva(Reserva reserva) {
        this.reservasDeEstudiantes.add(reserva);
    }

    public GestorEstudiante getGestorEstudiante() {
        return gestorEstudiante;
    }

    public void agregarReservasAlEstudiante(String usuarioEstudiante, int numeroDeReserva) {
        gestorEstudiante.agregarReservas(usuarioEstudiante, numeroDeReserva);
    }

    public ArrayList<Reserva> getReservasDeEstudiantes() {
        return reservasDeEstudiantes;
    }

    public GestorPago getGestorPago() {
        return gestorPago;
    }

    public String getVerificacionDePago() {
        return this.verificacionDePago;
    }

    public void setEstadoDeReserva(boolean estadoDeReserva) {
        int indice = buscarEstudiante(true).getNumerosDeReservas().getLast();
        reservasDeEstudiantes.get(indice).setEstadoReserva(estadoDeReserva);
    }

    public void guardarPagoYTicket() {
        gestorPago.guardarPagoYTicket();
    }

    public File getReservaFile() {
        return this.reservasFile;
    }
}
