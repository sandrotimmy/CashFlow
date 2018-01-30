package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.UsuariosDAO;
import cashFlow.MVC.Models.Usuarios;
import java.util.List;

public class UsuariosCtrl {

    private final UsuariosDAO persistUsuarios;

    public UsuariosCtrl() {
        this.persistUsuarios = new UsuariosDAO();
    }

    public Usuarios autenticar(String usuario, String senha) {
        List<Usuarios> listaUsuarios = persistUsuarios.getListaUsuarios();
////        boolean existe = false;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            String nomeTemp = listaUsuarios.get(i).getNickUsuario();
            String senhaTemp = listaUsuarios.get(i).getSenhaUsuario();
            if (nomeTemp.equalsIgnoreCase(usuario) && senhaTemp.equalsIgnoreCase(senha)) {
                return listaUsuarios.get(i);
            }
        }
        return null;
    }
}
