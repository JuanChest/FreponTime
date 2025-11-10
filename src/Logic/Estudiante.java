package Logic;

import java.util.ArrayList;

public class Estudiante {
    private String apellido;
    private String nombre;
    private String usuario;
    private String correoElectronico;
    private String contrasenia;

    private ArrayList<Integer> numerosDeReservas;
    private boolean enLinea;

    public Estudiante(String apellido, String nombre, String usuario, String correoElectronico, String contrasenia) {
        numerosDeReservas = new ArrayList<>();
        this.apellido = apellido;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.enLinea = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setEnLinea(boolean enLínea) {
        this.enLinea = enLínea;
    }

    public boolean getEnLinea() {
        return this.enLinea;
    }

    public void  setNumerosDeReservas(int numerosDeReservas) {
        this.numerosDeReservas.add(numerosDeReservas);
    }

    public boolean isEnLinea() {
        return enLinea;
    }

    public ArrayList<Integer> getNumerosDeReservas() {
        return numerosDeReservas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", correoElectrónico='" + correoElectronico + '\'' +
                ", enLínea=" + enLinea +
                ", numerosDeReservas=" + numerosDeReservas +
                '}';
    }
}
