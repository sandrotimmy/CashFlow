
package cashFlow.MVC.Models;
 
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class ConexaoBanco {

    private static final ConexaoBanco conn = new ConexaoBanco();
    private static final Statement s = conecta();

    public ConexaoBanco() {
    }

    public static ConexaoBanco getConn() {
        return conn;
    }

    public static Statement getStatement() {
        return s;
    }

    public static Statement conecta() {
        Statement st = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            File diretorioBanco = new File("BANCO\\BANCOCF.FDB");
            Connection conn = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:" + diretorioBanco.getAbsolutePath(), "sysdba", "masterkey");

            Statement s = conn.createStatement();
            st = s;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }
}
