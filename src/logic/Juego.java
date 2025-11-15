package logic;


public class Juego {
    private String nombre;
    private boolean buenEstado;
    private double precioPorHora;
    private String image;

    public Juego(String nombre, boolean estado, double precioPorHora, String ubImage) {
        this.nombre = nombre;
        this.buenEstado = estado;
        this.precioPorHora = precioPorHora;
        this.image = ubImage;
    }


    public boolean getEstado() {
        return buenEstado;
    }

    @Override
    public String toString() {
        return "Juego: " +
                "nombre='" + nombre + '\'' +
                ", buenEstado=" + buenEstado +
                ", precioPorHora=" + precioPorHora;
    }

    public void crearJuego(Juego juego) {
        this.nombre = juego.nombre;
        this.buenEstado = juego.buenEstado;
        this.precioPorHora = juego.precioPorHora;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Juego juego = (Juego) obj;
        return this.nombre.equals(juego.nombre) && this.buenEstado == juego.buenEstado
                && this.precioPorHora == juego.precioPorHora;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getTiempoPorHora() {
        return this.precioPorHora;
    }

    public double getPrecioPorHora() {
        return precioPorHora;
    }

    public String getUbicacion() {
        return this.image;
    }
}
