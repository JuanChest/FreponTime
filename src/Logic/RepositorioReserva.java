package Logic;

import java.io.File;

public class RepositorioReserva {
    private final File reservasFile;
    private final File juegoFile;
    private final File reservasEstudianteFile;

    public RepositorioReserva(String rutaReservas, String rutaJuegos, String rutaReservasEstudiante){
        this.reservasFile = new File(rutaReservas);
        this.juegoFile = new File(rutaJuegos);
        this.reservasEstudianteFile = new File(rutaReservasEstudiante);
    }
    public void cargarDatos(GestorReserva gestor) {
        GestorArchivos.cargarJuegos(gestor, juegoFile);
        GestorArchivos.cargarReservas(gestor, reservasFile);
        GestorArchivos.cargarReservasDeEstudiantes(gestor, reservasEstudianteFile);
    }
    public void guardarReserva(GestorReserva gestor) {
        GestorArchivos.guardarReservas(gestor, reservasFile);
    }
    public void guardarReservasDeEstudiante(GestorReserva gestor) {
        GestorArchivos.guardarReservasDeEstudiantes(gestor, reservasEstudianteFile);
    }
    public File getReservasFile() {
        return this.reservasFile;
    }
}
