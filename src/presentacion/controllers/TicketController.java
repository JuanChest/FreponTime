package presentacion.controllers;

import logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class TicketController {

    @FXML
    private Button continuarButton;

    @FXML
    private TableView<Estudiante> tablaEstudiante;
    @FXML
    private TableView<Ticket> tablaTicket;
    @FXML
    private TableView<Horario> tablaHorario;

    @FXML
    private TableColumn<Estudiante, String> columnaNombre;
    @FXML
    private TableColumn<Estudiante, String> columnApellido;
    @FXML
    private TableColumn<Estudiante, String> columnaUsuario;

    @FXML
    private TableColumn<Ticket, String> columnaCodigo;
    @FXML
    private TableColumn<Ticket, String> columnaFecha;
    @FXML
    private TableColumn<Ticket, String> columnaHora;

    @FXML
    private TableColumn<Horario, String> columnaFecha1;
    @FXML
    private TableColumn<Horario, String> columnaHora1;
    @FXML
    private TableColumn<Horario, Integer> columnaDuracion;

    @FXML
    private ImageView imageView;

    ObservableList<Estudiante> estudiante = FXCollections.observableArrayList();
    ObservableList<Horario> horario = FXCollections.observableArrayList();
    GestorReserva gestorReserva = GestorReserva.getInstance();
    ObservableList<Ticket> ticket = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (gestorReserva.buscarEstudiante(true).getNumerosDeReservas().isEmpty()) {
            NavegacionInterfaces.mostrarAlerta("ERROR", "El estudiante no tiene tickets disponibles.");
            return;
        }
        int indiceAux = gestorReserva.buscarEstudiante(true).getNumerosDeReservas().getLast();
        if (gestorReserva.getReservasDeEstudiantes().get(indiceAux).getEstadoDeReserva()) {
            NavegacionInterfaces.mostrarAlerta("ERROR", "El estudiante no ha cancelado su reserva.");
            return;
        }

        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
        this.columnaHora.setCellValueFactory(new PropertyValueFactory<>("horaReserva"));

        this.ticket.add(gestorReserva.getReservasDeEstudiantes().get(indiceAux).getPago().getTicket());
        this.tablaTicket.setItems(this.ticket);

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        this.estudiante.add(gestorReserva.buscarEstudiante(true));
        this.tablaEstudiante.setItems(this.estudiante);

        this.columnaFecha1.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.columnaHora1.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.columnaDuracion.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        int aux = gestorReserva.buscarEstudiante(true).getNumerosDeReservas().getLast();
        this.horario.add(gestorReserva.getReservasDeEstudiantes().get(aux).getHorario());
        this.tablaHorario.setItems(this.horario);

        imageView.setImage(new Image(getClass()
                .getResource(gestorReserva.getReservasDeEstudiantes().get(indiceAux).getJuego().getUbicacion())
                .toExternalForm()));
    }

    @FXML
    public void continuar(ActionEvent event) {

        NavegacionInterfaces.cambiarVentana((Stage) continuarButton.getScene().getWindow(),
                "/presentacion/views/SeleccionarJuego.fxml", "Seleccionar Juego");
    }

}
