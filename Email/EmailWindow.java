package T1_Layouts.SendMail.Email;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.awt.event.*;
import T1_Layouts.SendMail.*;
import java.awt.event.ActionEvent;

public class EmailWindow extends JFrame implements ActionListener {

    public JTextField txtTo, txtBCC, txtCC, txtSubject;
    public JTextArea txtContent;
    public JButton btnSend, btnClear, btnAttach;
    public JPanel panelButtons;
    public JLabel labelSubject, labelTo, labelCC, labelBCC, labelFile;
    public JList<String> fileList;
    public DefaultListModel<String> fileListModel;
    public Container container;
    ConfigManager configManager;
    EmailSender emailSender;
    AttachService attachService;

    public EmailWindow() {
    try {
        configManager = new ConfigManager("cred.properties");
        emailSender = new EmailSender(configManager);  // EmailSender gestionará el envío de correos
        attachService = new AttachService();

        setTitle("Enviar Correo");
        setSize(700, 450);
        setLocationRelativeTo(null);  // Centrar la ventana
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  // Cierra solo esta ventana

        initGUI();
        setVisible(true);
    } catch (IOException e) {
        throw new RuntimeException("Error al inicializar EmailWindow: " + e.getMessage(), e);
    }
}

    public void initGUI () {
        UIComponentsManager uiManager = new UIComponentsManager(this); // Maneja los componentes gráficos
        uiManager.initializeComponents();
        addActions();
    }

    private void addActions () {
        btnClear.addActionListener(this);
        btnSend.addActionListener(this);
        btnAttach.addActionListener(this);
    }

    public void clearFields () {
        txtContent.setText("");
        txtTo.setText("");
        txtBCC.setText("");
        txtCC.setText("");
        txtSubject.setText("");

        fileListModel.clear(); // Limpiar la lista de archivos adjuntos
        btnAttach.setText("Adjuntar...");
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if ( e.getSource() == btnClear ) {
            clearFields();
        } else if ( e.getSource() == btnSend ) {
            List<File> attachments = attachService.getSelectedFiles();
            // Verificar si la dirección de correo electrónico es válida
            if ( txtTo.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa una dirección de correo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            emailSender.sendEmail(txtTo.getText(), txtBCC.getText(), txtCC.getText(), txtSubject.getText(), txtContent.getText(), attachments);
            clearFields();
        } else if ( e.getSource() == btnAttach ) {
            List<File> attachments = attachService.openFiles(); // Obtener archivos
            if ( ! attachments.isEmpty() ) {
                for ( File file : attachments ) {
                    fileListModel.addElement(file.getName()); // Añadir cada archivo al JList
                }
            }
        }
    }
}