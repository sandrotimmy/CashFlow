package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.ReajusteProdutos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private EntityManager em;

    public ProdutosDAO() {
    }

    public void cadastrarProduto(Produtos produto) {
        em = ConexaoEntityManager.getInstance();
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
    }

    public void removeProduto(int codProduto) {
        em = ConexaoEntityManager.getInstance();
        Produtos produto = em.find(Produtos.class, codProduto);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Produto Removido com Sucesso!");
        }
    }

    public void atualizaProduto(Produtos produto) {
        if (produto != null) {
            em = ConexaoEntityManager.getInstance();
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
            em.close();
        }
    }

    public List getListaProdutos() {
        em = ConexaoEntityManager.getInstance();
        List<Produtos> listaProdutos = em.createQuery("FROM Produtos p ORDER BY p.cod").getResultList();
        return listaProdutos;
    }
    public List getListaProdutosPorNome() {
        em = ConexaoEntityManager.getInstance();
        List<Produtos> listaProdutos = em.createQuery("FROM Produtos p ORDER BY p.descricao").getResultList();
        return listaProdutos;
    }
    public List getUmProduto(int cod) {
        em = ConexaoEntityManager.getInstance();
        List<Produtos> listaProdutos = em.createQuery("FROM Produtos where cod = "+cod).getResultList();
        return listaProdutos;
    }

    public List getListaReajustes(Integer codProduto) {
        em = ConexaoEntityManager.getInstance();
        Produtos produto = em.find(Produtos.class, codProduto);
        List<ReajusteProdutos> listaReajustes = produto.getReajusteProdutos();

        if (produto.getReajusteProdutos() != null) {
            for (ReajusteProdutos each : listaReajustes) {
                listaReajustes.add(new ReajusteProdutos(
                        each.getDataReajuste(),
                        each.getMotivo(),
                        each.getTipoReajuste(),
                        each.getValorAntigo(),
                        each.getReajuste(),
                        each.getValorReajustado(),
                        each.getProduto()));
            }
        }
        return listaReajustes;
    }

    public Produtos pesquisaProdutos(Integer codProduto) {
        em = ConexaoEntityManager.getInstance();
        Produtos produto = em.find(Produtos.class, codProduto);
        return produto;
    }

    public int getProximoProduto() {
        em = ConexaoEntityManager.getInstance();
        int posicao;
        Object ultimo;
        ultimo = em.createNativeQuery("select max (PRODUTOS.COD) AS ULTIMO FROM PRODUTOS").getSingleResult();
        if (ultimo == null) {
            posicao = 1;
        } else {
            posicao = Integer.parseInt(ultimo.toString()) + 1;
        }
        return posicao;
    }

}
