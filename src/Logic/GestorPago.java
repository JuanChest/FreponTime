package Logic;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorPago {
    private ArrayList<Pago> pagos;
    private ArrayList<Ticket> tickets;

    public GestorPago() {
        pagos = new ArrayList<>();
        tickets = new ArrayList<>();
        GestorArchivos.cargarPagos(this, new File("src/Datos/Pagos.txt"));
        GestorArchivos.cargarTicket(this, new File("src/Datos/Tickets.txt"));
        GestorArchivos.cargarPagosTicket(this, new File("src/Datos/PagosTickets.txt"));
    }

    public void crearPagoDeReserva(Reserva nuevaReserva, ArrayList<Reserva> reservasDeEstudiantes, Juego juego) {
        Ticket ticket = new Ticket("T-N" + nuevaReserva.getNumero(), LocalDate.now(), LocalTime.now(), false);
        Pago nuevoPago = new Pago(reservasDeEstudiantes.size(), juego.getPrecioPorHora() * nuevaReserva.getHorario().getTiempo().toHours(), false);
        nuevoPago.setTicket(ticket);
        tickets.add(ticket);
        pagos.add(nuevoPago);
        nuevaReserva.setPago(nuevoPago); // Asociar el pago a la reserva
        System.out.println("Reserva creada con éxito. Ticket generado: " + ticket.getCodigo());
    }
    public boolean pagarReserva(Reserva reservaAPagar) {
        if (reservaAPagar.getPago().isEstadoPago()) {
            System.out.println("El pago ya fue confirmado.");
            return false;
        }

        reservaAPagar.getPago().setEstadoPago(true); // Marcar el pago como pagado
        reservaAPagar.setEstadoReserva(true); // Activa la reserva
        System.out.println("Pago confirmado. Reserva activada para uso.");
        return true;
    }

    public void agregarPagos(Pago pago) {
        pagos.add(pago);
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void agregarTickets(Ticket ticket) {
        tickets.add(ticket);
    }

    public void guardarPagoYTicket() {
        GestorArchivos.guardarPagos(this, new File("src/Datos/Pagos.txt"));
        GestorArchivos.guardarTickets(this, new File("src/Datos/Tickets.txt"));
        GestorArchivos.guardarPagosTicket(this, new File("src/Datos/PagosTickets.txt"));
    }
}
