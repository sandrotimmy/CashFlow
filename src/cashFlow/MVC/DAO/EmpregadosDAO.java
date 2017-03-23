package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Empregados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.swing.JOptionPane;

public class EmpregadosDAO {

    private EntityManager em;
    private List<Empregados> listaEmpregados;

    public EmpregadosDAO() {
        this.listaEmpregados = new ArrayList();
    }

    public int getProximoEmpregado() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (EMPREGADOS.COD) AS ULTIMO FROM EMPREGADOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

    public void cadastraEmpregado(Empregados empregado) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(empregado);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Empregado Cadastrado com Sucesso!");
    }

    public void atualizaEmpregados(Empregados empregado) {
        if (empregado != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(empregado);
            em.getTransaction().commit();
            em.close();
        }
        JOptionPane.showMessageDialog(null, "Empregado Atualizado com Sucesso!");
    }

    public boolean removeEmpregado(int idEmpregado) {
        em = ConexaoEntityManager.getInstance();
        Empregados empregado = em.find(Empregados.class, idEmpregado);
        try {
            if (empregado != null) {
                em.getTransaction().begin();
                em.remove(empregado);
                em.getTransaction().commit();
                em.close();
                JOptionPane.showMessageDialog(null, "Empregado Excluido com Sucesso!");
            }
        } catch (RollbackException e) {
            JOptionPane.showMessageDialog(null, "Não é possivel Excluir este Empregado\nPossui calculo de Folha de Pgto!");
            return false;
        }
        return true;
    }

    public Empregados pesquisaEmpregado(Integer codEmpregado) {

        em = ConexaoEntityManager.getInstance();
        Empregados empregado = em.find(Empregados.class, codEmpregado);
        return empregado;
    }

    public List getListaEmpregados() {
        em = ConexaoEntityManager.getInstance();
        List listaEmpregados = em.createQuery("FROM Empregados e ORDER BY e.cod" , Empregados.class).getResultList();
        return listaEmpregados;
    }
}
