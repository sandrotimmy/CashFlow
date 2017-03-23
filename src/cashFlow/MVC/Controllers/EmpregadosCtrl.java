package cashFlow.MVC.Controllers;

import cashFlow.MVC.DAO.EmpregadosDAO;
import cashFlow.MVC.Models.Empregados;
import java.util.List;

public class EmpregadosCtrl {

    private final EmpregadosDAO persistEmpregados;

    public EmpregadosCtrl() {
        this.persistEmpregados = new EmpregadosDAO();
    }

    public Empregados pesquisaEmpregado(int idEmpregado) {
        return persistEmpregados.pesquisaEmpregado(idEmpregado);
    }

    public List getListaEmpregados() {
        return persistEmpregados.getListaEmpregados();
    }

    public int getProximoEmpregado() {
        return persistEmpregados.getProximoEmpregado();
    }

    public void cadastraEmpregados(Empregados empregado) {
        persistEmpregados.cadastraEmpregado(empregado);
    }

    public boolean removeEmpregado(int codEmpregado) {
        return persistEmpregados.removeEmpregado(codEmpregado);
    }
    
    public void atualizaEmpregados(Empregados empregado){
    persistEmpregados.atualizaEmpregados(empregado);
    }
}
