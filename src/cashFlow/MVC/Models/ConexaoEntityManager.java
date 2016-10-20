package cashFlow.MVC.Models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoEntityManager {

    private static EntityManager em;
    private static EntityManagerFactory emf;

    public static EntityManager getInstance() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("BANCOCF");
            em = emf.createEntityManager();
        } else {
            em = emf.createEntityManager();
        }
        return em;
    }
}
