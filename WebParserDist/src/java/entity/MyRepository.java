/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author denis
 */
@Stateless
public class MyRepository implements MyRepositoryLocal {
    @PersistenceContext
        EntityManager em;

    @Override
    public List<Person> getAllPerson() {
        return em.createNamedQuery("Person.findAll").getResultList();
    }

    
}
