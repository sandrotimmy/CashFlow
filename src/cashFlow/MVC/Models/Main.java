package cashFlow.MVC.Models;

import cashFlow.MVC.Views.TelaLogin;
import java.sql.SQLException;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new TelaLogin();
    }
}  