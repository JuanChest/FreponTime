package Logic;

import java.util.ArrayList;

public class Estudiante {
    private String apellido;
    private String nombre;
    private String usuario;
    private String correoElectrónico;
    private String contraseña;

    private ArrayList<Integer> numerosDeReservas;
    private boolean enLínea;

    public Estudiante(String apellido, String nombre, String usuario, String correoElectrónico, String contraseña) {
        numerosDeReservas = new ArrayList<>();
        this.apellido = apellido;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correoElectrónico = correoElectrónico;
        this.contraseña = contraseña;
        this.enLínea = false;
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

    public String getCorreoElectrónico() {
        return correoElectrónico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setEnLinea(boolean enLínea) {
        this.enLínea = enLínea;
    }

    public boolean getEnLinea() {
        return this.enLínea;
    }

    public void  setNumerosDeReservas(int numerosDeReservas) {
        this.numerosDeReservas.add(numerosDeReservas);
    }

    public boolean isEnLínea() {
        return enLínea;
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
                ", correoElectrónico='" + correoElectrónico + '\'' +
                ", enLínea=" + enLínea +
                ", numerosDeReservas=" + numerosDeReservas +
                '}';
    }
}
