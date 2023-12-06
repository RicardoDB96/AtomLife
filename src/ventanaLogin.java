import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancelar;
    private JPanel miPanel;

    public ventanaLogin() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(txtPassword.getPassword());
                if (txtUsuario.getText().equals("admin") && password.equals("1234")) {
                    // Admin valido
                    dispose();
                    String[] tipoUsuario = {"admin"};
                    ventanaMain.main(tipoUsuario);
                } else if (txtUsuario.getText().equals("usuario") && password.equals("4567")) {
                    // Usuario valido
                    dispose();
                    String[] tipoUsuario = {"usuario"};
                    ventanaMain.main(tipoUsuario);
                } else {
                    // Usuario o contraseña no validas
                    JOptionPane.showMessageDialog(miPanel, "El usuario o la contraseña son invalidos", "Inicio de sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Salir del programa
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(miPanel, "¿Desea salir de AtomLife?", "Salir", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        ventanaLogin vLogin = new ventanaLogin();
        vLogin.setContentPane(vLogin.miPanel);
        vLogin.setSize(250, 250);
        vLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        vLogin.setVisible(true);
        vLogin.setLocationRelativeTo(null);
    }
}
