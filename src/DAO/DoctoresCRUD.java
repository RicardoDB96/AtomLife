package DAO;

import entidades.Doctor;

import java.io.*;
import java.util.ArrayList;

public class DoctoresCRUD {

    // Ruta del archivo que contiene la información de los Doctores
    private final String filepath = "C:\\tmp\\Doctores.txt";

    private void crearArchivo() {
        File archivo = new File(filepath);
        try {
            //Intentamos crear el archivo
            if (archivo.createNewFile()) {
                // Se crea el archivo con el ArrayList dentro para evitar cualquier error
                ArrayList<Doctor> lista = new ArrayList<Doctor>();
                FileOutputStream escribir =
                        new FileOutputStream(filepath);
                ObjectOutputStream miStream =
                        new ObjectOutputStream(escribir);
                miStream.writeObject(lista);
                miStream.close();
                System.out.println("El archivo se ha creado correctamente.");
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear o acceder al archivo: " + e.getMessage());
        }
    }

    private ArrayList<Doctor> leerArchivo() {
        try {
            FileInputStream leer = new FileInputStream(filepath);
            System.out.println(leer.available());
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            return (ArrayList<Doctor>) o;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer lista de clase Doctores");
        }
        return null;
    }

    private void escribirArchivo(ArrayList<Doctor> d) {
        // Escribimos la lista nueva sobre la lista anterior
        try {
            FileOutputStream escribir = new FileOutputStream(filepath);
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(d);
            miStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        }
    }

    /**
     * Método para ingresar un Doctor al sistema
     *
     * @param d Doctor a ingresar al sistema
     */
    public void insertarDoctor(Doctor d) {
        crearArchivo();

        ArrayList<Doctor> doctores = leerArchivo();

        if (doctores != null) {
            doctores.add(d);
            escribirArchivo(doctores);
        } else {
            System.out.println("Error al intentar agregar un doctor");
        }
    }

    /**
     * Metodo para buscar un Doctor con un ID ingresada en el sistema
     *
     * @param ID del Doctor que estamos buscando
     * @return Doctor si es que existe uno con la ID, por el contrario se retorna null
     */
    public Doctor buscarDoctorPorID(long ID) {
        crearArchivo();
        // Obtener lista de doctores desde Archivo
        ArrayList<Doctor> doctoresList = leerArchivo();

        // Comprobamos si existe la lista
        if (doctoresList == null) {
            return null;
        }

        // Comprobamos si la lista contiene el ID del doctor que buscamos
        for (Doctor d : doctoresList) {
            if (d.getID() == ID) {
                return d;
            }
        }

        // No se encontro un doctor con la ID ingresada
        return null;
    }
}
