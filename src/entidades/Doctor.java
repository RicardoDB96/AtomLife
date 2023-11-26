package entidades;

import java.io.Serializable;

public class Doctor extends Persona implements Serializable {

    // Variables especificas de Doctor
    private String especialidad, consultorio;

    /*/**
     *
     * Constructor para un doctor
     *
     * @param ID ID del doctor
     * @param nombre Nombre del doctor
     * @param apellido Apellidos del doctor
     * @param edad Edad del doctor
     * @param genero Genero del doctor
     * @param especialidad Edad del doctor
     * @param consultorio Consultorio designado del doctor
     */
    /*public Doctor(Long ID, String nombre, String apellido, int edad, String genero, String especialidad, String consultorio) {
        super(ID, nombre, apellido, edad, genero);
        this.especialidad = especialidad;
        this.consultorio = consultorio;
    }*/

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
}
