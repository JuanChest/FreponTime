package Logic;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Horario {
    private LocalDate fecha;
    private LocalTime hora;
    private Duration tiempo;

    public Horario(LocalDate fecha, LocalTime hora, Duration tiempo) {
        this.fecha = fecha;
        this.hora = hora;
        this.tiempo = tiempo;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Horario horario = (Horario) obj;
        return this.fecha.equals(horario.fecha) &&
                this.hora.equals(horario.hora) &&
                this.tiempo.equals(horario.tiempo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, hora, tiempo);
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public Duration getTiempo() {
        return this.tiempo;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


}
