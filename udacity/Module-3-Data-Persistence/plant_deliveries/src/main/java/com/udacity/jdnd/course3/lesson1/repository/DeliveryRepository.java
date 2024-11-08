package com.udacity.jdnd.course3.lesson1.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.lesson1.data.Delivery;
import com.udacity.jdnd.course3.lesson1.data.Plant;
import com.udacity.jdnd.course3.lesson1.data.RecipientAndPrice;

@Repository
@Transactional    
/*  meaning that any database operations performed within the marked method or class will be executed within a transaction. 
    If the transaction is successful, the changes will be committed to the database
*/
public class DeliveryRepository {

    // Do some basic persistence CRUD operations save/find/update/delete
    @PersistenceContext
    EntityManager entityManager;


    public void persist(Delivery delivery)
    {
        /* 
            Takes an Entity not yet managed. 
            The Entity becomes managed and will be saved to the database
        */
        entityManager.persist(delivery); 
    }   

    public Delivery find(Long id)
    {
        /*
            Looks up an id in the database and returns a managed Entity instance by its key.
        */
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery)
    {
        /*  
            Updates an Entity that is in the detached state. 
            Returns an instance of that Entity that is now managed. 
            If Entity was not found in the database to update, persists Entity as a new row.
        */
        return entityManager.merge(delivery);
    }
    
    public void delete(Long id)
    {
       /*
            Detaches an entity. 
            Retrieve an instance by its key, and deletes it from the database.
       */ 
        Delivery delivery = find(id);
        entityManager.remove(delivery); 
    }


    public List<Delivery> findDeliveriesByName(String name){
       TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
       query.setParameter("name", name);
       return query.getResultList();
   }

      // One possible way to solve this - query a list of Plants with deliveryId matching
   // the one provided and sum their prices.
   public RecipientAndPrice getBill(Long deliveryId) {
       CriteriaBuilder cb = entityManager.getCriteriaBuilder();
       CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
       Root<Plant> root = query.from(Plant.class);
       query.select(
               cb.construct(
                       RecipientAndPrice.class,
                       root.get("delivery").get("name"),
                       cb.sum(root.get("price"))))
               .where(cb.equal(root.get("delivery").get("id"), deliveryId));
       return entityManager.createQuery(query).getSingleResult();
   }
}
