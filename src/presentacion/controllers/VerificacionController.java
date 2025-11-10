package presentacion.controllers;

import Logic.GestorReserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VerificacionController {
    @FXML
    private TextField codigoField;
    @FXML
    private Button confirmacionButton;
    @FXML
    private Button regresarButton;

    private GestorReserva reserva = GestorReserva.getInstance();

    @FXML
    public void confirmar(ActionEvent event) {
        if(!reserva.verificarCodigo(this.codigoField.getText())){
            NavegacionInterfaces.mostrarAlerta("ERROR...", "El código enviado no es correcto.");
            return;
        }
        reserva.guardarPosibleEstudiante();
        NavegacionInterfaces.mostrarAlerta("PROCESO EXITOSO", "FELICIDADES usted se ha " +
                "registrado con éxito...\nInicie sesión para continuar.");
        NavegacionInterfaces.cambiarVentana((Stage) confirmacionButton.getScene().getWindow(),
                "/presentacion/views/login.fxml", "Login");
    }
    @FXML
    public void regresar(ActionEvent event) {
        NavegacionInterfaces.cambiarVentana((Stage) regresarButton.getScene().getWindow(),
                "/presentacion/views/registro.fxml", "Registro");
    }
}
