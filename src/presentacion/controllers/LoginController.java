package presentacion.controllers;

import Logic.GestorReserva;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField correoElectronico;
    @FXML
    private PasswordField contrasenia;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    GestorReserva gestorReserva = GestorReserva.getInstance();

    // Método para manejar el evento de inicio de sesión
    @FXML
    private void iniciarSesion(ActionEvent event) {
        if(!gestorReserva.iniciarSesion(this.correoElectronico.getText(), this.contrasenia.getText())){
            NavegacionInterfaces.mostrarAlerta("Error al Iniciar","Sus credenciales no son correctas");
            return;
        }
        NavegacionInterfaces.cambiarVentana((Stage)loginButton.getScene().getWindow(), "/presentacion/views/SeleccionarJuego.fxml", "Juegos");
    }

    @FXML
    private void registrar(ActionEvent event) {
        NavegacionInterfaces.cambiarVentana((Stage)registerButton.getScene().getWindow(),"/presentacion/views/registro.fxml", "Registro");
    }

}
