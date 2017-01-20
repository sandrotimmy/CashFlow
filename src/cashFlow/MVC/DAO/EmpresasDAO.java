package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Empresa;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class EmpresasDAO {

    private EntityManager em;

    public EmpresasDAO() {
    }

    public void cadastraEmpresa(Empresa empresa) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(empresa);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Empresa Cadastrada com Sucesso!");
    }

    public Empresa getCadastroEmpresa() {
        em = ConexaoEntityManager.getInstance();
        Empresa empresaBusca = em.find(Empresa.class, 1);
        if (empresaBusca != null) {
            return empresaBusca;
        } else {
            return null;
        }
    }

    public void removeEmpresa() {
        em = ConexaoEntityManager.getInstance();
        Empresa empresa = em.find(Empresa.class, 1);
        if (empresa != null) {
            em.getTransaction().begin();
            em.remove(empresa);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Empresa Removida com Sucesso!");
        }
    }

    public void atualizaEmpresa(Empresa empresa) {
        if (empresa != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(empresa);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Empresa Atualizada com Sucesso!");
    }
}
