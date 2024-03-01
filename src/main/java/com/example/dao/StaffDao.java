package com.example.dao;

import com.example.domain.Staff;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
/**
 * Data Access Object Class for the Staff Entity.
 * Uses only javax.persistence libraries.
 */

@Repository
public class StaffDao {
    private EntityManager em;
    public StaffDao(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }


    public List<Staff> findByLastName(String lastName) {
        TypedQuery<Staff> query = em.createQuery(
                "SELECT s FROM Staff s WHERE s.member.lastName = :lastName", Staff.class);
        return query.setParameter("lastName", lastName).getResultList();
    }

    public List<Staff> find(int pageNumber, int pageSize){
        TypedQuery<Staff> query = em.createQuery(
                "SELECT s FROM Staff s ORDER by s.member.lastName ASC", Staff.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
