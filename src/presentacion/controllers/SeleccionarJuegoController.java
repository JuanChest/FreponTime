package presentacion.controllers;

import Logic.GestorArchivos;
import Logic.GestorReserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SeleccionarJuegoController {
    @FXML
    public Button cerrarSesionButton;
    @FXML
    public Button billarButton;
    @FXML
    public Button pingPongButton;
    @FXML
    public Button pagarReservaButton;
    @FXML
    public Button verTicketButton;


    GestorReserva gestorReserva = GestorReserva.getInstance();
    @FXML
    public void cerrarSesion(ActionEvent event) {
        System.out.println(gestorReserva.buscarEstudiante(true));
        gestorReserva.buscarEstudiante(true).setEnLinea(false);

        GestorArchivos.guardarReservas(gestorReserva, gestorReserva.getReservaFile());
        NavegacionInterfaces.cambiarVentana((Stage) cerrarSesionButton.getScene().getWindow(), "/presentacion/views/login.fxml", "Login...");
        System.out.println(gestorReserva.buscarEstudiante(true));
    }
    @FXML
    public void seleccionarBillar(ActionEvent event) { //0 --> Billar, 1--> PingPong
        HorarioJuegosController.juegoAux = gestorReserva.getJuegosDisponibles().get(0);

        NavegacionInterfaces.cambiarVentana((Stage) billarButton.getScene().getWindow(), "/presentacion/views/HorarioJuegos.fxml", "Horarios Disponibles");
    }
    @FXML
    public void seleccionarPingPong(ActionEvent event) {
        HorarioJuegosController.juegoAux = gestorReserva.getJuegosDisponibles().get(1);

        NavegacionInterfaces.cambiarVentana((Stage) pingPongButton.getScene().getWindow(), "/presentacion/views/HorarioJuegos.fxml", "Horarios Disponibles");
    }
    @FXML
    public void pagarReserva  (ActionEvent event) {
        if(gestorReserva.buscarEstudiante(true).getNumerosDeReservas().isEmpty()){
            NavegacionInterfaces.mostrarAlerta("ERROR...", "No existe reservas del estudiante");
            return;
        }
        int numeroDeReservaAux = gestorReserva.buscarEstudiante(true).getNumerosDeReservas().getLast();
        if(!gestorReserva.getReservasDeEstudiantes().get(numeroDeReservaAux).getEstadoDeReserva()){
            NavegacionInterfaces.mostrarAlerta("ERROR...", "No existe una reserva a pagar.");
            return;
        }
        NavegacionInterfaces.cambiarVentana((Stage) pagarReservaButton.getScene().getWindow(), "/presentacion/views/Pagar.fxml", "Pagos");
    }
    @FXML
    public void verTicket(ActionEvent event) {
        //TODO: revisar lógica de los tickets.
        NavegacionInterfaces.cambiarVentana((Stage) verTicketButton.getScene().getWindow(),
                "/presentacion/views/Ticket.fxml", "Ticket...");
    }
}
