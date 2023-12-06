package CustomList;

import entidades.Cita;

import javax.swing.*;
import java.awt.*;

public class CitasListItem extends javax.swing.JPanel {

    private JLabel txtDoctorList;
    private JLabel txtFechaList;
    private JLabel txtPacienteList;
    private JLabel txtHoraList;
    private JLabel txtMotivoList;

    public CitasListItem() {
        initComponents();
    }

    public void setItem(Object obj) {
        if (obj instanceof Cita) {
            Cita item = (Cita) obj;
            txtDoctorList.setText(item.getDoctor());
            txtFechaList.setText(item.getFecha());
            txtPacienteList.setText(item.getPaciente());
            txtHoraList.setText(item.getHora());
            txtMotivoList.setText(item.getMotivo());
        } else {
            txtDoctorList.setText(obj + "");
            txtFechaList.setText(obj + "");
            txtPacienteList.setText(obj + "");
            txtHoraList.setText(obj + "");
            txtMotivoList.setText(obj + "");
        }
    }

    private void initComponents() {

        txtDoctorList = new javax.swing.JLabel();
        txtFechaList = new javax.swing.JLabel();
        txtPacienteList = new javax.swing.JLabel();
        txtHoraList = new javax.swing.JLabel();
        txtMotivoList = new javax.swing.JLabel();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Configurar restricciones para el primer JLabel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Ancho de una columna
        gbc.fill = GridBagConstraints.HORIZONTAL; // El JLabel se expandirá horizontalmente
        gbc.weightx = 1.0; // Expande horizontalmente
        gbc.insets = new Insets(2, 2, 2, 2); // Márgenes
        add(txtDoctorList, gbc);

        // Configurar restricciones para el segundo JLabel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expande horizontalmente
        add(txtFechaList, gbc);

        // Configurar restricciones para el tercer JLabel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Ancho de una columna
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Expande horizontalmente
        add(txtPacienteList, gbc);

        // Configurar restricciones para el cuarto JLabel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0; // Expande horizontalmente
        add(txtHoraList, gbc);

        // Configurar restricciones para el quinto JLabel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // El JLabel ocupará el espacio restante en ambas direcciones
        gbc.weightx = 1.0; // Expande horizontalmente
        gbc.weighty = 1.0; // Expande horizontalmente
        add(txtMotivoList, gbc);
    }
}