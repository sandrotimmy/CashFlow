package cashFlow.MVC.DAO;

import cashFlow.MVC.Models.ConexaoEntityManager;
import cashFlow.MVC.Models.Produtos;
import cashFlow.MVC.Models.ReajusteProdutos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    private EntityManager em;
    private final ArrayList<Produtos> listaProdutos;

    public ProdutosDAO() {
        this.listaProdutos = new ArrayList();
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

    public ArrayList getListaProdutos() {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Produtos> q = em.createQuery("FROM Produtos p ORDER BY p.cod", Produtos.class);
        for (Produtos each : q.getResultList()) {
            listaProdutos.add(new Produtos(each.getCod(), each.getIdentificador(), each.getDescricao(), each.getUnidade(), each.getQuantidade(), each.getValorUnitario(), each.getValorTotal(), each.getValorUnitarioVenda(), each.getValorTotalVenda()));
        }
        return listaProdutos;
    }

    public ArrayList getUmProduto(int cod) {
        em = ConexaoEntityManager.getInstance();
        TypedQuery<Produtos> q = em.createQuery("FROM Produtos where cod = "+cod, Produtos.class);
        for (Produtos each : q.getResultList()) {
            listaProdutos.add(new Produtos(each.getCod(), each.getIdentificador(), each.getDescricao(), each.getUnidade(), each.getQuantidade(), each.getValorUnitario(), each.getValorTotal(), each.getValorUnitarioVenda(), each.getValorTotalVenda()));
        }
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
