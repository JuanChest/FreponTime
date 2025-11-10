package Logic;

public class Reserva {
    private int numero;
    private boolean estadoDeReserva;
    private Horario horario;
    private Juego juego;
    private Pago pago;

    public Reserva(int numero, Juego juego, Horario horario) {
        this.numero = numero;
        this.juego = juego;
        this.estadoDeReserva = true;
        this.horario = horario;
    }

    public Reserva(int numero, Juego juego, Horario horario, boolean estadoDeReserva) {
        this.numero = numero;
        this.juego = juego;
        this.estadoDeReserva = estadoDeReserva;
        this.horario = horario;
    }

    public Juego getJuego() {
        return this.juego;
    }

    public Horario getHorario() {
        return this.horario;
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean getEstadoDeReserva() {
        return this.estadoDeReserva;
    }
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Pago getPago() {
        return pago;
    }

    public void setEstadoReserva(boolean estadoPago) {
        this.estadoDeReserva = estadoPago;
    }

}
