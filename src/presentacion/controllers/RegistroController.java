package presentacion.controllers;

import Logic.Estudiante;
import Logic.GestorReserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField contraseniaField;
    @FXML
    private TextField usuarioField;

    @FXML
    private Button registerButton;
    @FXML
    private Button regresarButton;

    //Clase general:
    GestorReserva gestorReserva = GestorReserva.getInstance();

    @FXML
    public void registrar(ActionEvent event){
        String nombreAux = nombreField.getText();
        String apellidoAux = apellidoField.getText();
        String emailAux = emailTextField.getText();
        String contraseniaAux = contraseniaField.getText();
        String usuarioAux = usuarioField.getText();

        Estudiante estudianteAux = new Estudiante(apellidoAux, nombreAux, usuarioAux, emailAux, contraseniaAux);
        if(gestorReserva.buscarEstudiante(estudianteAux.getUsuario())){
            NavegacionInterfaces.mostrarAlerta("Operación Fallida", "El usuario ingresado ya se " +
                    "encuentra registrado... Ingrese otro Usuario");
            return;
        }
        if(gestorReserva.buscarCorreo(estudianteAux.getCorreoElectrónico())){
            NavegacionInterfaces.mostrarAlerta("Operación Fallida", "El usuario ingresado ya se " +
                    "encuentra registrado... Ingrese su correo Personal");
            return;
        }

        if(!gestorReserva.enviarCodigo(estudianteAux)){
            NavegacionInterfaces.mostrarAlerta("Operación Fallida", "El Correo Proporcionado no pertenece a " +
                    "la Universidad...");
            return;
        }
        gestorReserva.preservarPosibleEstudiante(estudianteAux);
        NavegacionInterfaces.cambiarVentana((Stage) registerButton.getScene().getWindow(), "/presentacion/views/VerificaciónDeCodigo.fxml", "Verificacion");

    }
    @FXML
    public void regresar(ActionEvent event){
        NavegacionInterfaces.cambiarVentana((Stage)regresarButton.getScene().getWindow(), "/presentacion/views/login.fxml", "Login");
    }

}
