package presentacion.controllers;

import Logic.GestorReserva;
import Logic.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmarReservaController {

    @FXML
    private Button confirmarReservaButton;

    @FXML
    private Button cancelarReservaButton;

    public static Reserva reservaAux;
    GestorReserva gestorReserva = GestorReserva.getInstance();

    @FXML
    private void  confirmarReserva(ActionEvent event) {
        gestorReserva.crearReserva(reservaAux.getJuego(), reservaAux.getHorario());
        NavegacionInterfaces.cambiarVentana((Stage)confirmarReservaButton.getScene().getWindow(), "/presentacion/views/Pagar.fxml", "Seleccionar Juego");
    }

    @FXML
    private void cancelarReserva(ActionEvent event) {
        NavegacionInterfaces.cambiarVentana((Stage)cancelarReservaButton.getScene().getWindow(),"/presentacion/views/SeleccionarJuego.fxml", "Seleccionar Juego");
        }

}
