import DAO.DoctoresCRUD;
import DAO.PacientesCRUD;
import entidades.Doctor;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
     * Metodo para crear un Doctor nuevo con los datos del formulario, ademas de validad si son correctos los datos
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
     * Metodo para crear un Paciente nuevo con los datos del formulario, ademas de validad si son correctos los datos
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
     * Metodo con las acciones a realizar al pulsar buscar doctor
     * @param d Doctor que recuperamos al buscar
     * @param id ID con la que buscamos al doctor
     */
    public void doctorRespuesta(Doctor d, Long id) {
        if (d == null) {
            int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra el doctor con la ID: " + id + "\n¿Desea dar de alta?", "Doctor", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                // Se quiere dar de alta un nuevo Doctor
                btnAgregarDoctor.setEnabled(true);
                txtNombreDoctor.requestFocus();

            } else if (respuesta == 1) {
                // No se quiere dar de alta un nuevo Doctor, se limpia el formulario
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
     * @param p Paciente que recuperamos al buscar
     * @param id ID con la que buscamos al paciente
     */
    public void pacienteRespuesta(Paciente p, Long id) {
        if (p == null) {
            int respuesta = JOptionPane.showConfirmDialog(miPanel, "No se encuentra al paciente con la ID: " + id + "\n¿Desea dar de alta?", "Paciente", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                // Se quiere dar de alta un nuevo Doctor
                btnAgregarPaciente.setEnabled(true);
                txtNombrePaciente.requestFocus();

            } else if (respuesta == 1) {
                // No se quiere dar de alta un nuevo Doctor, se limpia el formulario
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

    public ventanaMain() {

        // Ponemos los ComboBox en null para manejar un poco mejor el manejo de validación de datos
        cmbGeneroDoctor.setSelectedItem(null);
        cmbGeneroPaciente.setSelectedItem(null);

        // Acciones de Doctor
        btnBuscarDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoctoresCRUD crud = new DoctoresCRUD();
                boolean resultado = validarID(txtIDDoctor.getText());

                if (resultado) {
                    long id = Long.parseLong(txtIDDoctor.getText());
                    Doctor doctor = crud.buscarDoctorPorID(id);
                    doctorRespuesta(doctor, id);
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
                    pacienteRespuesta(paciente, id);
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
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ventanaMain v = new ventanaMain();
        v.setContentPane(v.miPanel);
        v.setSize(500, 500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
