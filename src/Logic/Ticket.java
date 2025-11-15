package Logic;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
    private String codigo;
    private boolean validez;
    private LocalDate fechaReserva;
    private LocalTime horaReserva;

    public Ticket(String código, LocalDate fechaReserva, LocalTime horaReserva, boolean validez) {
        this.codigo = código;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.validez = validez;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public boolean isValidez() {
        return validez;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }
}
