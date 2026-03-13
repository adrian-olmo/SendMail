package T1_Layouts.SendMail;

import javax.swing.*;
import java.io.IOException;
import T1_Layouts.SendMail.Email.EmailWindow;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(EmailWindow::new);
    }
}