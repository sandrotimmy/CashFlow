package cashFlow.MVC.Models;

import cashFlow.MVC.Views.TelaLogin;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException, IOException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
    }
}  