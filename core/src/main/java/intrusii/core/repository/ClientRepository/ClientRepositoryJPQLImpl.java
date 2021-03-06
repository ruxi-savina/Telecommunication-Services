package intrusii.core.repository.ClientRepository;

import intrusii.core.model.Client;
import intrusii.core.model.SubscriptionType;
import intrusii.core.repository.CustomRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("JPQL")
public class ClientRepositoryJPQLImpl extends CustomRepositorySupport implements ClientRepositoryCustom {
    @Override
    public List<Client> findAllWithContracts() {
        System.out.println("---JPQL---");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct c from Client c " +
                "left join fetch c.contracts ");
        List<Client> clients = query.getResultList();

        return clients;
    }

    @Override
    public List<Client> findAllWithSubscriptionType(SubscriptionType subscriptionType) {
        System.out.println("---JPQL---");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery("select distinct cl from Client cl " +
                "left join fetch cl.contracts co " +
                "left join fetch co.subscription s " +
                "where s.type = ?1");

        List<Client> clients = query.setParameter(1, subscriptionType).getResultList();

        return clients;
    }
}
