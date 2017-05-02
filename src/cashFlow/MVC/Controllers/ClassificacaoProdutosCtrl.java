package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.ClassificacaoProdutosDAO;
import cashFlow.MVC.Models.ClassificacaoProdutos;
import java.util.List;

public class ClassificacaoProdutosCtrl {

    private final ClassificacaoProdutosDAO persistClassificacao;

    public ClassificacaoProdutosCtrl() {
        this.persistClassificacao = new ClassificacaoProdutosDAO();
    }

    public List getListaClassificacao() {
        return persistClassificacao.getListaClassificacao();
    }

    public boolean removeClassificacao(int cod) {
        return persistClassificacao.removeClassificacao(cod);
    }

    public int getProximoCodClassificacao() {
        return persistClassificacao.getProximoCodClassificacao();
    }

    public void cadastrarClassificacao(ClassificacaoProdutos classificacao) {
        persistClassificacao.cadastrarClassificacao(classificacao);
    }

    public void atualizaClassificacao(ClassificacaoProdutos classificacao) {
        persistClassificacao.atualizaClassificacao(classificacao);
    }

    public ClassificacaoProdutos getClassificacao(int idClassificacao) {
        return persistClassificacao.getClassificacao(idClassificacao);
    }
}
