
package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.UsuariosDAO;
import cashFlow.MVC.Models.Usuarios;
import java.util.List;

public class UsuariosCtrl {

    private final UsuariosDAO persistUsuarios;

    public UsuariosCtrl() {
        this.persistUsuarios = new UsuariosDAO();
    }

    public boolean autenticar(String usuario, String senha) {
        List<Usuarios> listaUsuarios = persistUsuarios.getListaUsuarios();
        boolean existe = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            String usuarioTemp = listaUsuarios.get(i).getNickUsuario();
            String senhaTemp = listaUsuarios.get(i).getSenhaUsuario();
            if (usuarioTemp.equalsIgnoreCase(usuario) && senhaTemp.equalsIgnoreCase(senha)) {
                existe = true;
            }
        }
        return existe;
    }
}