package DAO;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

public class PacientesCRUD {

    // Ruta del archivo que contiene la información de los Pacientes
    private final String filepath = "C:\\tmp\\Pacientes.txt";

    private void crearArchivo() {
        File archivo = new File(filepath);
        try {
            //Intentamos crear el archivo
            if (archivo.createNewFile()) {
                // Se crea el archivo con el ArrayList dentro para evitar cualquier error
                ArrayList<Paciente> lista = new ArrayList<Paciente>();
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

    private ArrayList<Paciente> leerArchivo() {
        try {
            FileInputStream leer = new FileInputStream(filepath);
            System.out.println(leer.available());
            ObjectInputStream miStream = new ObjectInputStream(leer);
            Object o = miStream.readObject();
            miStream.close();
            return (ArrayList<Paciente>) o;
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

    private void escribirArchivo(ArrayList<Paciente> p) {
        // Escribimos la lista nueva sobre la lista anterior
        try {
            FileOutputStream escribir = new FileOutputStream(filepath);
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(p);
            miStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e);
        }
    }

    /**
     * Método para ingresar un Paciente al sistema
     *
     * @param p Paciente a ingresar al sistema
     */
    public void insertarPaciente(Paciente p) {
        crearArchivo();

        ArrayList<Paciente> pacientes = leerArchivo();

        if (pacientes != null) {
            pacientes.add(p);
            escribirArchivo(pacientes);
        } else {
            System.out.println("Error al intentar agregar un paciente");
        }
    }

    /**
     * Metodo para buscar un Paciente con un ID ingresada en el sistema
     *
     * @param ID del Paciente que estamos buscando
     * @return Paciente si es que existe uno con la ID, por el contrario se retorna null
     */
    public Paciente buscarPacientePorID(long ID) {
        crearArchivo();

        // Obtener lista de pacientes desde Archivo
        ArrayList<Paciente> pacientes = leerArchivo();

        // Comprobamos si existe la lista
        if (pacientes == null) {
            return null;
        }

        // Comprobamos si la lista contiene el ID del paciente que buscamos
        for (Paciente p : pacientes) {
            if (p.getID() == ID) {
                return p;
            }
        }

        // No se encontro un paciente con la ID ingresada
        return null;
    }
}
