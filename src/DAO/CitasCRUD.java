package DAO;

import entidades.Cita;
import entidades.Doctor;

import java.io.*;
import java.util.ArrayList;

public class CitasCRUD {

    // Ruta del archivo que contiene la información de las Citas
    private final String filepath = "C:\\tmp\\Citas.txt";

    private void crearArchivo() {
        File archivo = new File(filepath);
        try {
            //Intentamos crear el archivo
            if (archivo.createNewFile()) {
                // Se crea el archivo con el ArrayList dentro para evitar cualquier error
                ArrayList<Cita> lista = new ArrayList<Cita>();
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

    public ArrayList<Cita> leerArchivo() {
        try {
            FileInputStream leer = new FileInputStream(filepath);
            System.out.println(leer.available());
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            return (ArrayList<Cita>) o;
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer lista de clase Citas");
        }
        return null;
    }

    private void escribirArchivo(ArrayList<Cita> c) {
        // Escribimos la lista nueva sobre la lista anterior
        try {
            FileOutputStream escribir = new FileOutputStream(filepath);
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(c);
            miStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        }
    }

    /**
     * Método para ingresar una Cita medica al sistema
     *
     * @param c Cita medica a ingresar al sistema
     */
    public void insertarCita(Cita c) {
        crearArchivo();

        ArrayList<Cita> citas = leerArchivo();

        if (citas != null) {
            citas.add(c);
            escribirArchivo(citas);
        } else {
            System.out.println("Error al intentar agregar un doctor");
        }
    }
}
