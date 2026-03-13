package T1_Layouts.SendMail.Email;

import java.util.*;
import java.io.File;
import javax.swing.*;

public class AttachService {
    private final List<File> selectedFiles = new ArrayList<>();

    public List<File> openFiles() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true); // Permitir seleccionar múltiples archivos
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            selectedFiles.clear(); // Limpiar archivos anteriores
            Collections.addAll(selectedFiles, files); // Añadir los archivos seleccionados
        }
        return selectedFiles;
    }

    public List<File> getSelectedFiles() {
        return selectedFiles; // Retornar la lista de archivos seleccionados
    }
}