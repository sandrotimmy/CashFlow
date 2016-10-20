package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class UsuariosDAO {

    private EntityManager em;

    public UsuariosDAO() {
    }

    //Pega o proximo codigo a ser utilizado
    public int getProximoCodUsuario() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (USUARIOS.IDUSUARIO)AS ULTIMO FROM USUARIOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void insereUsuario(Usuarios usuario) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
    }

    public void atualizaUsuario(Usuarios usuario) {
        if (usuario != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Usuário Atualizado com Sucesso!");
    }

    public void removeUsuario(int idUsuario) {
        em = ConexaoEntityManager.getInstance();
        Usuarios usuario = em.find(Usuarios.class, idUsuario);
        if (usuario != null) {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso!");
        }
    }

    /**
     *
     * @return Lista de Usuarios
     * 
     */
    public List getListaUsuarios() {
        em = ConexaoEntityManager.getInstance();
        List <Usuarios> listaUsuarios = em.createQuery("FROM Usuarios u ORDER BY u.idUsuario").getResultList();
        return listaUsuarios;
    }
}
