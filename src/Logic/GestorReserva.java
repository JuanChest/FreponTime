package Logic;

import java.io.File;
import java.util.ArrayList;

public class GestorReserva {
    private ArrayList<Reserva> reservasDeEstudiantes;
    private ArrayList<Juego> juegos;
    private GestorEstudiante gestorEstudiante;
    private GestorPago gestorPago;
    private String verificacionDePago = "CANCELADO24";
    private RepositorioReserva repositorioReserva;

    private static GestorReserva instance;

    private GestorReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;

        reservasDeEstudiantes = new ArrayList<>();
        juegos = new ArrayList<>();
        gestorEstudiante = new GestorEstudiante();
        gestorPago = new GestorPago();

        repositorioReserva.cargarDatos(this);
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
            instance = new GestorReserva(new RepositorioReserva(
                    "src/Datos/Reserva.txt",
                    "src/Datos/Juegos.txt",
                    "src/Datos/ReservasDeEstudiante.txt"
            ));
        }
        return instance;
    }

    public void crearReserva(Juego juego, Horario horario) {
        if(existirReservasDuplicadas(juego, horario)){
            System.out.println("Ya existe una reserva para este juego y horario.");
            return;
        }
        for(Estudiante estudianteEnLínea: gestorEstudiante.getEstudiantes()){
            if(estudianteEnLínea.isEnLinea()){
                Reserva nuevaReserva = new Reserva(reservasDeEstudiantes.size(), juego, horario);
                estudianteEnLínea.getNumerosDeReservas().add(nuevaReserva.getNumero());
                reservasDeEstudiantes.add(nuevaReserva);

                gestorPago.crearPagoDeReserva(nuevaReserva, reservasDeEstudiantes, juego);
                repositorioReserva.guardarReserva(this);
                repositorioReserva.guardarReservasDeEstudiante(this);
                return;
            }
        }
    }

    public boolean existirReservasDuplicadas(Juego juego, Horario horario) {
            for (Reserva reservasDeEstudiante : reservasDeEstudiantes) {
            if (reservasDeEstudiante.getJuego().equals(juego) && reservasDeEstudiante.getHorario().equals(horario)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Juego> getJuegosDisponibles() {
        ArrayList<Juego> auxJuegos = new ArrayList<>();
        for (Juego juego : juegos) {
            if (juego.getEstado()) {
                auxJuegos.add(juego);
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

    public void guardarReservas() {
        repositorioReserva.guardarReserva(this);
    }

}
