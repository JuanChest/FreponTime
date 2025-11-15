package presentacion.controllers;

import Logic.Horario;
import Logic.Juego;
import Logic.GestorReserva;
import Logic.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HorarioJuegosController {
    public static Juego juegoAux;
    @FXML
    private Button regresarButton;
    @FXML
    private Button reservarButton;
    @FXML
    private CheckBox seleccionar910Button, seleccionar1011Button, seleccionar1112Button, seleccionar1213Button;
    @FXML
    private CheckBox seleccionar1314Button, seleccionar1415Button, seleccionar1516Button, seleccionar1617Button;
    @FXML
    private CheckBox seleccionar1718Button;

    GestorReserva gestorReserva = GestorReserva.getInstance();
    private Horario horario;
    private Duration duracion = Duration.ofHours(1);
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public void initialize() {
        checkBoxes.add(seleccionar910Button);
        checkBoxes.add(seleccionar1011Button);
        checkBoxes.add(seleccionar1112Button);
        checkBoxes.add(seleccionar1213Button);
        checkBoxes.add(seleccionar1314Button);
        checkBoxes.add(seleccionar1415Button);
        checkBoxes.add(seleccionar1516Button);
        checkBoxes.add(seleccionar1617Button);
        checkBoxes.add(seleccionar1718Button);

        LocalDate fecha = LocalDate.now();
        horario = new Horario(fecha, null, duracion);
    }

    @FXML
    private void regresarSeleccionarJuego(ActionEvent event) {
        NavegacionInterfaces.cambiarVentana((Stage)regresarButton.getScene().getWindow(),"/presentacion/views/SeleccionarJuego.fxml", "Reservar Juego");
    }

    @FXML
    private void reservarJuego(ActionEvent event) {
        if(gestorReserva.existirReservasDuplicadas(juegoAux, horario)){
            NavegacionInterfaces.mostrarAlerta("ERROR", "Existe una reserva en ese horario");
            return;
        }
        if(!gestorReserva.buscarEstudiante(true).getNumerosDeReservas().isEmpty()){
            int indice = gestorReserva.buscarEstudiante(true).getNumerosDeReservas().getLast();
            if(gestorReserva.getReservasDeEstudiantes().get(indice).getEstadoDeReserva()){
                NavegacionInterfaces.mostrarAlerta("ERROR", "Existe una reserva del estudiante");
                return;
            }
        }
        if(horario.getHora() == null){
            NavegacionInterfaces.mostrarAlerta("ERROR", "No hay horario seleccionado.");
            return;
        }
        ConfirmarReservaController.reservaAux = new Reserva(gestorReserva.getReservasDeEstudiantes().size(),juegoAux, horario);
        NavegacionInterfaces.cambiarVentana((Stage)reservarButton.getScene().getWindow(),"/presentacion/views/ConfirmarReserva.fxml", "Confirmar Reserva");
    }

    @FXML
    private void seleccionar910(ActionEvent event) {
        seleccionarHora(0, 9, 0);
    }

    @FXML
    private void seleccionar1011(ActionEvent event) {
        seleccionarHora(1, 10, 0);
    }
    @FXML
    private void seleccionar1112(ActionEvent event) {
        seleccionarHora(2, 11, 0);
    }

    @FXML
    private void seleccionar1213(ActionEvent event) {
        seleccionarHora(3, 12, 0);
    }

    @FXML
    private void seleccionar1314(ActionEvent event) {
        seleccionarHora(4, 13, 0);
    }

    @FXML
    private void seleccionar1415(ActionEvent event) {
        seleccionarHora(5, 14, 0);
    }

    @FXML
    private void seleccionar1516(ActionEvent event) {
        seleccionarHora(6, 15, 0);
    }

    @FXML
    private void seleccionar1617(ActionEvent event) {
        seleccionarHora(7, 16, 0);
    }

    @FXML
    private void seleccionar1718(ActionEvent event) {
        seleccionarHora(8, 17, 0);
    }
    private void cambiarDeshabilitadoAlCheckBoxes(CheckBox checkBox, boolean estado) {
        for(CheckBox aux : checkBoxes) {
            if(aux != checkBox) {
                aux.setDisable(estado);
            }
        }
    }
    private void seleccionarHora(int numeroArreglo, int hora, int minuto) {
        if(checkBoxes.get(numeroArreglo).isSelected()) {
            horario.setHora(LocalTime.of(hora,minuto));

            cambiarDeshabilitadoAlCheckBoxes(checkBoxes.get(numeroArreglo), true);
            return;
        }
        if(!(checkBoxes.get(numeroArreglo).isSelected() && checkBoxes.get(numeroArreglo).isIndeterminate())) {
            horario.setHora(null);
            cambiarDeshabilitadoAlCheckBoxes(checkBoxes.get(numeroArreglo), false);
        }
    }
}
