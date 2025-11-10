package presentacion.controllers;

import Logic.GestorReserva;
import Logic.Pago;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;

public class PagarController {

    @FXML
    private Button regresarButton;
    @FXML
    private Button finalizarButton;
    @FXML
    private TextField validarTextField;
    @FXML
    private TableView<Pago> tablaPagos;
    @FXML
    private TableColumn columnaNumeroPago;
    @FXML
    private TableColumn columnaValorReserva;
    @FXML
    private TableColumn columnaTotal;

    private ObservableList<Pago> listaPagos;
    GestorReserva gestorReserva = GestorReserva.getInstance();
    int indiceAux = gestorReserva.buscarEstudiante(true).getNumerosDeReservas().getLast();

    @FXML
    public void initialize() {

        if(gestorReserva.buscarEstudiante(true).getNumerosDeReservas().isEmpty()){
           NavegacionInterfaces.mostrarAlerta("ERROR", "El estudiante no tiene reservas.");
           return;
        }

        if(!gestorReserva.getReservasDeEstudiantes().get(indiceAux).getEstadoDeReserva()){
            NavegacionInterfaces.mostrarAlerta("ERROR", "El estudiante no tiene reservas a pagar");
            return;
        }
        listaPagos = FXCollections.observableArrayList();
        this.columnaNumeroPago.setCellValueFactory(new PropertyValueFactory<>("nPago"));
        this.columnaValorReserva.setCellValueFactory(new PropertyValueFactory<>("monto"));
        this.columnaTotal.setCellValueFactory(new PropertyValueFactory<>("monto"));

        this.listaPagos.add(gestorReserva.getReservasDeEstudiantes().get(indiceAux).getPago());
        this.tablaPagos.setItems(this.listaPagos);
    }

    @FXML
    private void regresarConfirmaReserva(ActionEvent event) {

        NavegacionInterfaces.cambiarVentana((Stage) regresarButton.getScene().getWindow(),
                "/presentacion/views/SeleccionarJuego.fxml", "Seleccionar Juego...");
    }
    @FXML
    private void finalizar(ActionEvent event) {
        if(!validarTextField.getText().equals(gestorReserva.getVerificacionDePago())){
            NavegacionInterfaces.mostrarAlerta("ERROR", "El codigo de verificación no es el correcto...");
            return;
        }
        gestorReserva.setEstadoDeReserva(false);

        gestorReserva.guardarPagoYTicket();
        NavegacionInterfaces.mostrarAlerta("GRACIAS", "Su pago ha sido registrado exitosamente");
        NavegacionInterfaces.cambiarVentana((Stage) finalizarButton.getScene().getWindow(),
                "/presentacion/views/Ticket.fxml", "Ticket...");
    }
}



