package T1_Layouts.SendMail;

import java.awt.*;
import javax.swing.*;
import T1_Layouts.SendMail.Email.EmailWindow;

public class UIComponentsManager {

    private final EmailWindow emailWindow;

    public UIComponentsManager(EmailWindow emailWindow) {
        this.emailWindow = emailWindow;
    }

    public void initializeComponents() {
        // Inicializar etiquetas y campos de texto
        emailWindow.labelSubject = new JLabel("Asunto");
        emailWindow.labelTo = new JLabel("Para");
        emailWindow.labelCC = new JLabel("CC");
        emailWindow.labelBCC = new JLabel("Cco");
        emailWindow.labelFile = new JLabel("Archivo");

        emailWindow.txtSubject = new JTextField();
        emailWindow.txtCC = new JTextField();
        emailWindow.txtBCC = new JTextField();
        emailWindow.txtTo = new JTextField();
        emailWindow.txtContent = new JTextArea(5, 20); // Especificamos el tamaño preferido

        emailWindow.btnClear = new JButton("Borrar");
        emailWindow.btnSend = new JButton("Enviar");
        emailWindow.btnAttach = new JButton("Adjuntar...");

        emailWindow.panelButtons = new JPanel();
        emailWindow.panelButtons.add(emailWindow.btnSend);
        emailWindow.panelButtons.add(emailWindow.btnClear);

        emailWindow.container = emailWindow.getContentPane();

        // Inicializa la lista de archivos adjuntos
        emailWindow.fileListModel = new DefaultListModel<>(); // Modelo para el JList
        emailWindow.fileList = new JList<>(emailWindow.fileListModel); // Crear el JList
        emailWindow.fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Selección múltiple
        emailWindow.fileList.setVisibleRowCount(5); // Número de filas visibles
        JScrollPane listScrollPane = new JScrollPane(emailWindow.fileList); // Añadir scroll

        configureLayout(listScrollPane); // Pasar el JScrollPane al método de layout
    }

    private void configureLayout(JScrollPane listScrollPane) {
        emailWindow.container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // Añadir componentes al layout con restricciones
        constraints.insets = new Insets(5, 5, 5, 5); // Añadir márgenes entre los componentes
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Primera fila: Para (To)
        addComponent(emailWindow.container, constraints, 0, 0, 1, emailWindow.labelTo);
        addComponent(emailWindow.container, constraints, 1, 0, 3, emailWindow.txtTo);

        // Segunda fila: CC y BCC
        addComponent(emailWindow.container, constraints, 0, 1, 1, emailWindow.labelCC);
        addComponent(emailWindow.container, constraints, 1, 1, 1, emailWindow.txtCC);
        addComponent(emailWindow.container, constraints, 2, 1, 1, emailWindow.labelBCC);
        addComponent(emailWindow.container, constraints, 3, 1, 1, emailWindow.txtBCC);

        // Tercera fila: Adjuntar archivo
        addComponent(emailWindow.container, constraints, 0, 2, 1, emailWindow.labelFile);
        addComponent(emailWindow.container, constraints, 1, 2, 1, emailWindow.btnAttach);
        addComponent(emailWindow.container, constraints, 2, 2, 2, listScrollPane); // Usar el JScrollPane con JList

        // Cuarta fila: Asunto
        addComponent(emailWindow.container, constraints, 0, 3, 1, emailWindow.labelSubject);
        addComponent(emailWindow.container, constraints, 1, 3, 3, emailWindow.txtSubject);

        // Quinta fila: Contenido del mensaje
        constraints.fill = GridBagConstraints.BOTH; // Permitir que JTextArea ocupe el espacio
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        addComponent(emailWindow.container, constraints, 0, 4, 1, new JLabel("Texto"));

        JScrollPane scrollPane = new JScrollPane(emailWindow.txtContent);
        addComponent(emailWindow.container, constraints, 1, 4, 3, scrollPane);

        // Sexta fila: Panel de botones (Enviar, Borrar)
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0;
        constraints.weighty = 0;
        addComponent(emailWindow.container, constraints, 0, 5, 4, emailWindow.panelButtons);
    }

    private void addComponent(Container container, GridBagConstraints c, int x, int y, int width, JComponent component) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        container.add(component, c);
    }
}