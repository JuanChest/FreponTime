package Logic;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GestorArchivos {

    public static void guardarEstudiantes(GestorEstudiante gestorEstudiante, File archivoEstudiante) {
        try {
            FileWriter writer = new FileWriter(archivoEstudiante);
            for (Estudiante estudiante : gestorEstudiante.getEstudiantes()) {
                writer.write(estudiante.getApellido() + " "
                        + estudiante.getNombre() + " "
                        + estudiante.getUsuario() + " "
                        + estudiante.getCorreoElectrónico() + " "
                        + estudiante.getContraseña() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void cargarEstudiantes(GestorEstudiante gestorEstudiante, File archivoEstudiante) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoEstudiante));
            String estudiante;
            String apellidoEstudiante;
            String nombreEstudiante;
            String usuarioEstudiante;
            String correoEstudiante;
            String contraseñaEstudiante;
            while ((estudiante = reader.readLine()) != null) {
                // Procesar la línea
                apellidoEstudiante = estudiante.split(" ")[0];
                nombreEstudiante = estudiante.split(" ")[1];
                usuarioEstudiante = estudiante.split(" ")[2];
                correoEstudiante = estudiante.split(" ")[3];
                contraseñaEstudiante = estudiante.split(" ")[4];
                gestorEstudiante.agregarEstudiante(new Estudiante(apellidoEstudiante, nombreEstudiante, usuarioEstudiante, correoEstudiante, contraseñaEstudiante));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void cargarReservas(GestorReserva gestorReserva, File archivoReservas) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoReservas))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                // Separar los elementos de la línea
                String[] datos = linea.split(" ");
                int numero = Integer.parseInt(datos[0]);
                String nombreJuego = datos[1];
                boolean estadoDeReserva = Boolean.parseBoolean(datos[2]);
                LocalDate fecha = LocalDate.parse(datos[3]); // Formato: yyyy-MM-dd
                LocalTime hora = LocalTime.parse(datos[4]);   // Formato: HH:mm
                Duration tiempo = Duration.ofMinutes(Long.parseLong(datos[5])); // Minutos

                // Crear el objeto Horario
                Horario horario = new Horario(fecha, hora, tiempo);

                // Obtener los detalles del juego
                boolean estadoJuego = Boolean.parseBoolean(datos[6]);
                double precioPorHora = Double.parseDouble(datos[7]);
                String ubImage = datos[8];

                // Crear el objeto Juego
                Juego juego = new Juego(nombreJuego, estadoJuego, precioPorHora, ubImage);

                // Crear el objeto Reserva
                Reserva reserva = new Reserva(numero, juego, horario, estadoDeReserva);

                // Agregar la reserva a la lista
                gestorReserva.agregarReserva(reserva);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }

    public static void cargarJuegos(GestorReserva gestorReserva, File archivoJuegos) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivoJuegos));
            String juego;
            String nombreJuego;
            boolean buenEstado;
            double precioPorHora;
            String ubImage;
            while ((juego = reader.readLine()) != null) {
                // Procesar la línea
                nombreJuego = juego.split(" ")[0];
                buenEstado = Boolean.parseBoolean(juego.split(" ")[1]);
                precioPorHora = Double.parseDouble(juego.split(" ")[2]);
                ubImage = juego.split(" ")[3];
                //TODO: Hacer función agregarJuegos en gestor reserva
                gestorReserva.agregarJuegos(new Juego(nombreJuego, buenEstado, precioPorHora, ubImage));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void guardarReservas(GestorReserva gestorReserva,File archivoReservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoReservas))) {
            // Formatos de fecha y hora
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

            for (Reserva reserva : gestorReserva.getReservasDeEstudiantes()) {
                // Extraer los datos de la reserva
                int numero = reserva.getNumero();
                String nombreJuego = reserva.getJuego().getNombre();
                boolean estadoDeReserva = reserva.getEstadoDeReserva();

                // Extraer los datos del horario
                Horario horario = reserva.getHorario();
                String fecha = horario.getFecha().format(formatoFecha);
                String hora = horario.getHora().format(formatoHora);
                long duracionMinutos = horario.getTiempo().toMinutes();

                // Extraer los datos del juego
                Juego juego = reserva.getJuego();
                boolean estadoJuego = juego.getEstado();
                double precioPorHora = juego.getTiempoPorHora();
                String ub = juego.getUbicacion();

                // Crear la línea de texto con todos los datos
                String linea = numero + " " + nombreJuego + " " + estadoDeReserva + " " +
                        fecha + " " + hora + " " + duracionMinutos + " " + estadoJuego + " " + precioPorHora + " " +
                        ub;

                // Escribir la línea en el archivo
                writer.write(linea);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void cargarReservasDeEstudiantes(GestorReserva gestorReserva, File reservasDeEstudiantes) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(reservasDeEstudiantes));
            String estudiante;
            String usuarioEstudiante;
            int numeroDeReserva;

            while ((estudiante = reader.readLine()) != null) {
                // Procesar la línea
                usuarioEstudiante = estudiante.split(" ")[0];
                numeroDeReserva =Integer.parseInt(estudiante.split(" ")[1]);
                gestorReserva.agregarReservasAlEstudiante(usuarioEstudiante, numeroDeReserva);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void guardarReservasDeEstudiantes(GestorReserva gestorReserva, File archivoReservasDeEstudiantes) {
        //Formato = usuario nReserva
        try {
            FileWriter writer = new FileWriter(archivoReservasDeEstudiantes);
            for (Estudiante estudiante : gestorReserva.getGestorEstudiante().getEstudiantes()) {
                for(Integer nReserva : estudiante.getNumerosDeReservas()){
                    writer.write(estudiante.getUsuario() + " "
                            + nReserva + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cargarPagos(GestorPago gestorPago, File pagos){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pagos));
            String pago;
            int nPago;
            double monto;
            boolean estadoPago;


            while ((pago = reader.readLine()) != null) {
                // Procesar la línea
                nPago = Integer.parseInt(pago.split(" ")[0]);
                monto = Double.parseDouble(pago.split(" ")[1]);
                estadoPago = Boolean.parseBoolean(pago.split(" ")[2]);
                gestorPago.agregarPagos(new Pago(nPago, monto, estadoPago));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void guardarPagos(GestorPago gestorPago, File pagos){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pagos))) {
            for (Pago pago : gestorPago.getPagos()) {
                // Formato: nPago monto estadoPago
                String linea = pago.getNPago() + " " + pago.getMonto() + " " + pago.isEstadoPago();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de pagos: " + e.getMessage());
        }
    }
    public static void cargarTicket(GestorPago gestorPago, File tickets) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tickets))) {
            String ticket;

            // Leer línea por línea
            while ((ticket = reader.readLine()) != null) {
                // Dividir los datos de la línea solo una vez
                String[] datos = ticket.split(" ");
                if (datos.length != 4) {
                    System.out.println("Formato incorrecto en la línea: " + ticket);
                    continue; // Saltar a la siguiente línea
                }

                try {
                    // Parsear los datos de la línea
                    String codigo = datos[0];
                    boolean validez = Boolean.parseBoolean(datos[1]);
                    LocalDate fecha = LocalDate.parse(datos[2]);
                    LocalTime hora = LocalTime.parse(datos[3]);

                    // Crear el objeto Ticket y agregarlo al gestor
                    gestorPago.agregarTickets(new Ticket(codigo, fecha, hora, validez));
                } catch (DateTimeParseException e) {
                    System.out.println("Error al parsear fecha u hora en la línea: " + ticket);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + tickets.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void guardarTickets(GestorPago gestorPago, File tickets){
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tickets))) {
            for (Ticket ticket : gestorPago.getTickets()) {
                String linea = ticket.getCodigo() + " " + ticket.isValidez() + " " +
                        ticket.getFechaReserva().format(formatoFecha) + " " + ticket.getHoraReserva().format(formatoHora);
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de tickets: " + e.getMessage());
        }
    }
    public static void cargarPagosTicket(GestorPago gestorPago, File pagosTickets) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pagosTickets))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(" ");
                String codigoTicket = datos[0];
                int nPago = Integer.parseInt(datos[1]);

                // Buscar el ticket correspondiente por código
                Ticket ticketEncontrado = null;
                for (Ticket ticket : gestorPago.getTickets()) {
                    if (ticket.getCodigo().equals(codigoTicket)) {
                        ticketEncontrado = ticket;
                        break;
                    }
                }

                // Buscar el pago correspondiente por número de pago
                Pago pagoEncontrado = null;
                for (Pago pago : gestorPago.getPagos()) {
                    if (pago.getNPago() == nPago) {
                        pagoEncontrado = pago;
                        break;
                    }
                }

                // Asociar el ticket al pago, si ambos existen
                if (ticketEncontrado != null && pagoEncontrado != null) {
                    pagoEncontrado.setTicket(ticketEncontrado);
                } else {
                    System.out.println("Error: No se encontró un ticket o pago correspondiente en la línea: " + linea);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de pagos-tickets no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de pagos-tickets: " + e.getMessage());
        }
    }
    public static void guardarPagosTicket(GestorPago gestorPago, File pagosTickets){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pagosTickets))) {
            for (Pago pago : gestorPago.getPagos()) {
                String linea = pago.getTicket().getCodigo() + " " + pago.getNPago();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de pagos-tickets: " + e.getMessage());
        }
    }
}

