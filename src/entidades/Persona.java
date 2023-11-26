package entidades;

import java.io.Serializable;

public class Persona implements Serializable {

    // Variables de de la clase persona, como su ID, su nombre y el apellido
    private Long ID;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;

    /*public Persona(Long ID, String nombre, String apellido, int edad, String genero) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
    }*/

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
