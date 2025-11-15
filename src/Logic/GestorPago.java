package Logic;

import java.util.logging.Logger;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestorPago {
    private ArrayList<Pago> pagos;
    private ArrayList<Ticket> tickets;
    private static final Logger logger = Logger.getLogger(GestorPago.class.getName());

    public GestorPago() {
        pagos = new ArrayList<>();
        tickets = new ArrayList<>();
        GestorArchivos.cargarPagos(this, new File("src/Datos/Pagos.txt"));
        GestorArchivos.cargarTicket(this, new File("src/Datos/Tickets.txt"));
        GestorArchivos.cargarPagosTicket(this, new File("src/Datos/PagosTickets.txt"));
    }

    public void crearPagoDeReserva(Reserva nuevaReserva, List<Reserva> reservasDeEstudiantes, Juego juego) {
        Ticket ticket = new Ticket("T-N" + nuevaReserva.getNumero(), LocalDate.now(), LocalTime.now(), false);
        Pago nuevoPago = new Pago(reservasDeEstudiantes.size(), juego.getPrecioPorHora() * nuevaReserva.getHorario().getTiempo().toHours(), false);
        nuevoPago.setTicket(ticket);
        tickets.add(ticket);
        pagos.add(nuevoPago);
        nuevaReserva.setPago(nuevoPago); // Asociar el pago a la reserva
        logger.info("Reserva creada con éxito. Ticket generado: " + ticket.getCodigo());
    }
    public boolean pagarReserva(Reserva reservaAPagar) {
        if (reservaAPagar.getPago().isEstadoPago()) {
            logger.info("El pago ya fue confirmado.");
            return false;
        }

        reservaAPagar.getPago().setEstadoPago(true); // Marcar el pago como pagado
        reservaAPagar.setEstadoReserva(true); // Activa la reserva
        logger.info("Pago confirmado. Reserva activada para uso.");
        return true;
    }

    public void agregarPagos(Pago pago) {
        pagos.add(pago);
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public List<Ticket> getTickets() {
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
