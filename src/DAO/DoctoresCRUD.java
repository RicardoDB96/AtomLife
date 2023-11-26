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

    /**
     * Método para ingresar un Doctor al sistema
     *
     * @param d Doctor a ingresar al sistema
     */
    public void insertarDoctor(Doctor d) {
        crearArchivo();
        try {
            FileInputStream leer = new FileInputStream(filepath);
            System.out.println(leer.available());
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Doctor> doctoresList = (ArrayList<Doctor>) o;

            doctoresList.add(d); // Añadimos el doctor a la lista recuperada

            // Escribimos la lista nueva sobre la lista anterior
            FileOutputStream escribir = new FileOutputStream(filepath);
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(doctoresList);
            miStream.close();
            miStream2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer lista de clase Doctores");
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
        try {
            FileInputStream leer = new FileInputStream(filepath);
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Doctor> doctoresList = (ArrayList<Doctor>) o;

            // Comprobamos si la lista contiene el ID del doctor que buscamos
            for (Doctor d : doctoresList) {
                if (d.getID() == ID) {
                    return d;
                }
            }

            // No se encontro un doctor con la ID ingresada
            miStream2.close();
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
