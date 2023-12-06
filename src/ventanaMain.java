import CustomList.CitasList;
import DAO.CitasCRUD;
import DAO.DoctoresCRUD;
import DAO.PacientesCRUD;
import entidades.Cita;
import entidades.Doctor;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ventanaMain extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel miPanel;
    private JTextField txtIDDoctor;
    private JTextField txtNombreDoctor;
    private JTextField txtApellidosDoctor;
    private JTextField txtEdadDoctor;
    private JComboBox cmbGeneroDoctor;
    private JTextField txtEspecialidadDoctor;
    private JTextField txtConsultorioDoctor;
    private JButton btnBuscarDoctor;
    private JButton btnAgregarDoctor;
    private JButton btnEliminarDoctor;
    private JButton btnGuardarDoctor;
    private JTextField txtIDPaciente;
    private JTextField txtNombrePaciente;
    private JTextField txtApellidosPaciente;
    private JTextField txtEdadPaciente;
    private JComboBox cmbGeneroPaciente;
    private JTextField txtAlergiasPaciente;
    private JTextField txtContactoPaciente;

    private JButton btnBuscarPaciente;
    private JButton btnAgregarPaciente;
    private JButton btnGuardarPaciente;
    private JButton btnEliminarPaciente;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAnio;
    private JComboBox cmbHora;
    private JComboBox cmbMinuto;
    private JComboBox cmbDoctores;
    private JComboBox cmbPacientes;
    private JTextField txtMotivo;
    private JButton btnAgregarCita;
    private JTextField txtDoctorSeleccionado;
    private JTextField txtPacienteSeleccionado;
    private JScrollPane sPanel;
    private JList<String> lCitas;

    /**
     * Metodo para validar si la ID ingresada es valida
     *
     * @return Boolean diciendo si la ID es valida
     */
    public boolean validarID(String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para validar si la edad ingresada es valida
     *
     * @return Boolean diciendo si la edad es valida
     */
    public boolean validarEdad(String age) {
        try {
            int edad = Integer.parseInt(age);
            if (edad < 0) {// La edad debe ser mayor a 0
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Metodo para comprobar que la fecha ingresada es valida
     *
     * @param fecha a comprobar su validez
     * @return boolean con la validez de la cita
     */
    public boolean validarFecha(String fecha) {
        try { // Se prueba la fecha para comprobar si es valida
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            Date miFecha = formatoFecha.parse(fecha);
        } catch (Exception e) { // Si tenemos algun error con la fecha, retornamos falso
            return false;
        }
        return true; // Retornamos verdadero si la fecha es valida
    }

    /**
     * Metodo para limpiar los datos en el formulario de Doctor
     */
    public void limpiarDatosDoctor() {
        txtIDDoctor.setText("");
        txtNombreDoctor.setText("");
        txtApellidosDoctor.setText("");
        txtEdadDoctor.setText("");
        cmbGeneroDoctor.setSelectedItem(null);
        txtEspecialidadDoctor.setText("");
        txtConsultorioDoctor.setText("");
    }

    /**
     * Metodo para limpiar los datos en el formulario de Paciente
     */
    public void limpiarDatosPaciente() {
        txtIDPaciente.setText("");
        txtNombrePaciente.setText("");
        txtApellidosPaciente.setText("");
        txtEdadPaciente.setText("");
        cmbGeneroPaciente.setSelectedItem(null);
        txtAlergiasPaciente.setText("");
        txtContactoPaciente.setText("");
    }

    /**
     * Metodo para limpiar los datos en el formulario de Cita
     */
    public void limpiarDatosCita() {
        cmbDia.setSelectedItem(null);
        cmbMes.setSelectedItem(null);
        cmbHora.setSelectedItem(null);
        cmbMinuto.setSelectedItem(null);
        txtMotivo.setText("");
        cmbDoctores.setSelectedItem(null);
        txtDoctorSeleccionado.setText("");
        cmbPacientes.setSelectedItem(null);
        txtPacienteSeleccionado.setText("");
    }

    /**
     * Metodo para crear un Doctor nuevo con los datos del formulario, ademas de validar si son correctos los datos
     *
     * @return Doctor con los datos ingresados, si no retorna null
     */
    public Doctor obtenerDoctor() {
        // Checamos si hay datos que ingresar y si estos son validos
        if (!validarDatosDoctor()) {
            return null;
        }

        Doctor d = new Doctor();

        // Vamos creando al doctor
        d.setID(Long.parseLong(txtIDDoctor.getText()));
        d.setNombre(txtNombreDoctor.getText());
        d.setApellido(txtApellidosDoctor.getText());
        d.setEdad(Integer.parseInt(txtEdadDoctor.getText()));
        d.setGenero(cmbGeneroDoctor.getSelectedItem().toString());
        d.setEspecialidad(txtEspecialidadDoctor.getText());
        d.setConsultorio(txtConsultorioDoctor.getText());

        // Retornamos el nuevo Doctor
        return d;
    }

    /**
     * Metodo para crear un Paciente nuevo con los datos del formulario, ademas de validar si son correctos los datos
     *
     * @return Paciente con los datos ingresados, si no retorna null
     */
    public Paciente obtenerPaciente() {
        // Checamos si hay datos que ingresar y si estos son validos
        if (!validarDatosPaciente()) {
            return null;
        }

        Paciente p = new Paciente();

        // Vamos creando al paciente
        p.setID(Long.parseLong(txtIDPaciente.getText()));
        p.setNombre(txtNombrePaciente.getText());
        p.setApellido(txtApellidosPaciente.getText());
        p.setEdad(Integer.parseInt(txtEdadPaciente.getText()));
        p.setGenero(cmbGeneroPaciente.getSelectedItem().toString());
        p.setAlergias(txtAlergiasPaciente.getText());
        p.setContactoEmergencias(txtContactoPaciente.getText());

        // Retornamos el nuevo Paciente
        return p;
    }

    /**
     * Metodo para crear una Cita medica nueva con los datos del formulario, ademas de validar si son correctos los datos
     *
     * @return Cita con los datos ingresados, si no retorna null
     */
    public Cita obtenerCita() {
        // Checamos si hay datos que ingresar y si estos son validos
        if (!validarDatosCitas()) {
            return null;
        }

        Cita c = new Cita();

        // Vamos creando la cita
        c.setFecha(obtenerFecha());
        c.setHora(obtenerHora());
        c.setMotivo(txtMotivo.getText());
        c.setDoctor(txtDoctorSeleccionado.getText());
        c.setPaciente(txtPacienteSeleccionado.getText());

        // Retonamos la nueva cita
        return c;
    }

    private String obtenerFecha() {
        try {
            return cmbDia.getSelectedItem().toString() + "/" + cmbMes.getSelectedItem().toString() + "/" + cmbAnio.getSelectedItem().toString();
        } catch (Exception e) {
            return "00/00/000";
        }
    }

    private String obtenerHora() {
        return cmbHora.getSelectedItem().toString() + ":" + cmbMinuto.getSelectedItem().toString();
    }

    /**
     * Metodo para validar si se ingresaron los datos necesarios para crear un Doctor
     *
     * @return Boolean con el valor si se tiene los datos para crear un doctor, ademas de que si alguno es incorrecto, se notifica
     */
    public boolean validarDatosDoctor() {
        // Se valida si algun dato esta vacio
        if (txtIDDoctor.getText().isEmpty() || !validarID(txtIDDoctor.getText())) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una ID válida, debe solo contener números");
            return false;
        }
        if (txtNombreDoctor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un nombre");
            return false;
        }
        if (txtApellidosDoctor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un apellido");
            return false;
        }
        if (txtEdadDoctor.getText().isEmpty() || !validarEdad(txtEdadDoctor.getText())) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una edad válida, debe ser solo números y ser mayor o igual a 0");
            return false;
        }
        if (cmbGeneroDoctor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(miPanel, "Seleccione un género");
            return false;
        }
        if (txtEspecialidadDoctor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una especialidad");
            return false;
        }
        if (txtConsultorioDoctor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un consultorio");
            return false;
        }

        // Si todos los datos tienen información, se considera correctos
        return true;
    }

    /**
     * Metodo para validar si se ingresaron los datos necesarios para crear un Paciente
     *
     * @return Boolean con el valor si se tiene los datos para crear un paciente, ademas de que si alguno es incorrecto, se notifica
     */
    public boolean validarDatosPaciente() {
        // Se valida si algun dato esta vacio
        if (txtIDPaciente.getText().isEmpty() || !validarID(txtIDPaciente.getText())) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una ID válida, debe solo contener números");
            return false;
        }
        if (txtNombrePaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un nombre");
            return false;
        }
        if (txtApellidosPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un apellido");
            return false;
        }
        if (txtEdadPaciente.getText().isEmpty() || !validarEdad(txtEdadPaciente.getText())) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una edad válida, debe ser solo números y ser mayor o igual a 0");
            return false;
        }
        if (cmbGeneroPaciente.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(miPanel, "Seleccione un género");
            return false;
        }
        if (txtAlergiasPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un alergias o en su defecto \"Ninguna\"");
            return false;
        }
        if (txtContactoPaciente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un contacto de emergencia");
            return false;
        }

        // Si todos los datos tienen información, se considera correctos
        return true;
    }

    /**
     * Metodo para validar si se ingresaron los datos necesarios para crear una Cita médica
     *
     * @return Boolean con el valor si se tiene los datos para crear una CIta, ademas de que si alguno es incorrecto, se notifica
     */
    public boolean validarDatosCitas() {
        // Se valida si algun dato esta vacio o no es valido
        if (!validarFecha(obtenerFecha())) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una fecha valida");
            return false;
        }
        if (cmbHora.getSelectedItem() == null || cmbMinuto.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese una hora para la cita médica");
            return false;
        }
        if (txtMotivo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un motivo para la cita médica");
            return false;
        }
        if (txtDoctorSeleccionado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un Doctor para la cita médica");
            return false;
        }
        if (txtPacienteSeleccionado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Ingrese un Paciente para la cita médica");
            return false;
        }
        // Si todos los datos tienen información y son validos, se considera correctos
        return true;
    }

    /**
     * Metodo con las acciones a realizar al pulsar buscar doctor
     *
     * @param d  Doctor que recuperamos al buscar
     * @param id ID con la que buscamos al doctor
     */
    public void doctorRespuesta(Doctor d, Long id, String tipoUsuario) {
        if (d == null) {
            if (!tipoUsuario.equals("usuario")) { // Se verifica si no es un usuario para realizar acciones importantes
                int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra el doctor con la ID: " + id + "\n¿Desea dar de alta?", "Doctor", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    // Se quiere dar de alta un nuevo Doctor
                    btnAgregarDoctor.setEnabled(true);
                    txtNombreDoctor.requestFocus();
                    limpiarDatosDoctor();
                    txtIDDoctor.setText(String.valueOf(id));
                    txtIDDoctor.setEnabled(false);

                } else if (respuesta == 1) {
                    // No se quiere dar de alta un nuevo Doctor, se limpia el formulario
                    limpiarDatosDoctor();
                }
            } else {
                JOptionPane.showMessageDialog(miPanel, "No se encuentra el doctor con la ID: " + id);
                limpiarDatosDoctor();
            }
        } else {
            txtNombreDoctor.setText(d.getNombre());
            txtApellidosDoctor.setText(d.getApellido());
            txtEdadDoctor.setText(String.valueOf(d.getEdad()));
            cmbGeneroDoctor.setSelectedItem(d.getGenero());
            txtEspecialidadDoctor.setText(d.getConsultorio());
            txtConsultorioDoctor.setText(d.getEspecialidad());
        }
    }

    /**
     * Metodo con las acciones a realizar al pulsar buscar paciente
     *
     * @param p  Paciente que recuperamos al buscar
     * @param id ID con la que buscamos al paciente
     */
    public void pacienteRespuesta(Paciente p, Long id, String tipoUsuario) {
        if (p == null) {
            if (!tipoUsuario.equals("usuario")) { // Se verifica si no es un usuario para realizar acciones importantes
                int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra al paciente con la ID: " + id + "\n¿Desea dar de alta?", "Paciente", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    // Se quiere dar de alta un nuevo Doctor
                    btnAgregarPaciente.setEnabled(true);
                    txtNombrePaciente.requestFocus();
                    limpiarDatosPaciente();
                    txtIDPaciente.setText(String.valueOf(id));
                    txtIDPaciente.setEnabled(false);
                } else if (respuesta == 1) {
                    // No se quiere dar de alta un nuevo Doctor, se limpia el formulario
                    limpiarDatosPaciente();
                }
            } else {
                JOptionPane.showMessageDialog(miPanel, "No se encuentra al paciente con la ID: " + id);
                limpiarDatosPaciente();
            }
        } else {
            txtNombrePaciente.setText(p.getNombre());
            txtApellidosPaciente.setText(p.getApellido());
            txtEdadPaciente.setText(String.valueOf(p.getEdad()));
            cmbGeneroPaciente.setSelectedItem(p.getGenero());
            txtAlergiasPaciente.setText(p.getAlergias());
            txtContactoPaciente.setText(p.getContactoEmergencias());
        }
    }

    public void rellenarDoctoresCmb() {
        cmbDoctores.removeAllItems();
        DoctoresCRUD dCRUD = new DoctoresCRUD();
        ArrayList<Doctor> doctoresList = dCRUD.leerArchivo();
        if (doctoresList != null) {
            for (Doctor d : doctoresList) {
                cmbDoctores.addItem(d.getID());
            }
        }
    }

    public void rellenarPacientesCmb() {
        cmbPacientes.removeAllItems();
        PacientesCRUD pCRUD = new PacientesCRUD();
        ArrayList<Paciente> pacientesList = pCRUD.leerArchivo();
        if (pacientesList != null) {
            for (Paciente p : pacientesList) {
                cmbPacientes.addItem(p.getID());
            }
        }
    }

    public ventanaMain(String tipoUsuario) {

        // Ponemos los ComboBox en null para manejar un poco mejor el manejo de validación de datos
        cmbGeneroDoctor.setSelectedItem(null);
        cmbGeneroPaciente.setSelectedItem(null);
        cmbDia.setSelectedItem(null);
        cmbMes.setSelectedItem(null);
        cmbHora.setSelectedItem(null);
        cmbMinuto.setSelectedItem(null);

        // Acciones de Doctor
        btnBuscarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctoresCRUD crud = new DoctoresCRUD();
                boolean resultado = validarID(txtIDDoctor.getText());

                if (resultado) {
                    long id = Long.parseLong(txtIDDoctor.getText());
                    Doctor doctor = crud.buscarDoctorPorID(id);
                    doctorRespuesta(doctor, id, tipoUsuario);
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Error en la ID, ingrese solo numeros");
                    txtIDDoctor.requestFocus();
                }
            }
        });
        btnAgregarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctoresCRUD crud = new DoctoresCRUD();
                Doctor doctor = obtenerDoctor();
                if (doctor != null) {
                    crud.insertarDoctor(doctor);
                    JOptionPane.showMessageDialog(miPanel, "Doctor ingresado exitosamente");
                    limpiarDatosDoctor();
                    btnAgregarDoctor.setEnabled(false);
                    txtIDDoctor.setEnabled(true);
                }
            }
        });

        // Acciones de Paciente
        btnBuscarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacientesCRUD crud = new PacientesCRUD();
                boolean resultado = validarID(txtIDPaciente.getText());

                if (resultado) {
                    long id = Long.parseLong(txtIDPaciente.getText());
                    Paciente paciente = crud.buscarPacientePorID(id);
                    pacienteRespuesta(paciente, id, tipoUsuario);
                } else {
                    JOptionPane.showMessageDialog(miPanel, "Error en la ID, ingrese solo numeros");
                    txtIDPaciente.requestFocus();
                }
            }
        });
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PacientesCRUD crud = new PacientesCRUD();
                Paciente paciente = obtenerPaciente();
                if (paciente != null) {
                    crud.insertarPaciente(paciente);
                    JOptionPane.showMessageDialog(miPanel, "Paciente ingresado exitosamente");
                    limpiarDatosPaciente();
                    btnAgregarPaciente.setEnabled(false);
                    txtIDPaciente.setEnabled(true);
                }
            }
        });

        // Acciones de Cita

        // Cada vez que se selecciona un Doctor, se muestra el nombre del mismo
        cmbDoctores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(cmbDoctores.getSelectedItem().toString());
                    DoctoresCRUD dCRUD = new DoctoresCRUD();
                    Doctor doc = dCRUD.buscarDoctorPorID(id);
                    if (doc != null) {
                        txtDoctorSeleccionado.setText(doc.getApellido() + ", " + doc.getNombre());
                    } else {
                        txtDoctorSeleccionado.setText("");
                    }
                } catch (Exception ex) {
                    txtDoctorSeleccionado.setText("");
                }
            }
        });

        // Cada vez que se selecciona un Paciente, se muestra el nombre del mismo
        cmbPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(cmbPacientes.getSelectedItem().toString());
                    PacientesCRUD pCRUD = new PacientesCRUD();
                    Paciente pac = pCRUD.buscarPacientePorID(id);
                    if (pac != null) {
                        txtPacienteSeleccionado.setText(pac.getApellido() + ", " + pac.getNombre());
                    } else {
                        txtPacienteSeleccionado.setText("");
                    }
                } catch (Exception ex) {
                    txtPacienteSeleccionado.setText("");
                }
            }
        });
        btnAgregarCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CitasCRUD crud = new CitasCRUD();
                Cita cita = obtenerCita();
                if (cita != null) {
                    crud.insertarCita(cita);
                    JOptionPane.showMessageDialog(miPanel, "Cita médica agregada exitosamente");
                    limpiarDatosCita();
                }
            }
        });

        // Creación de lista de citas médicas
        tabbedPane1.addChangeListener(e -> {
            if (tabbedPane1.getSelectedIndex() == 2) {
                // Rellenamos los comboBox de Doctores y pacientes con datos si es que tenemos
                rellenarDoctoresCmb();
                rellenarPacientesCmb();
                cmbDoctores.setSelectedItem(null);
                cmbPacientes.setSelectedItem(null);

                // Verificamos si tenemos un admin o no
                if (tipoUsuario != "admin") {
                    btnAgregarCita.setEnabled(false);
                }
            }
            if (tabbedPane1.getSelectedIndex() == 3) {
                createList();
            }
        });
    }

    private void createList() {
        CitasList<Cita> citasList = new CitasList<>();
        citasList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        citasList.setToolTipText("");
        sPanel.setViewportView(citasList);

        CitasCRUD crud = new CitasCRUD();
        ArrayList<Cita> citas = crud.leerArchivo();

        if (citas != null) {
            for (Cita c : citas) {
                citasList.addItem(c);
            }
        }
    }

    public static void main(String[] args) {
        ventanaMain v = new ventanaMain(args[0]);
        v.setContentPane(v.miPanel);
        v.setSize(500, 500);
        v.setTitle("AtomLife");
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
}
