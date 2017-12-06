/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkstudio.springcrud.service;


import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xiangsheng Li
 */
@Service("CrudService")
@Transactional
public class CrudServiceBean implements CrudService{
    
    @PersistenceContext(unitName = "SamplePU")
    private EntityManager em;

    @Override
    public <T> void create(T entity) {
        em.persist(entity);
    }

    @Override
    public <T> void edit(T entity) {
       em.merge(entity);
    }

    @Override
    public <T> void remove(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public <T> T find(Class<T> entityClass, Object id) {
        return em.find(entityClass, id);
    }

    @Override
    public List findWithNamedQuery(String queryName) {
        return this.em.createNamedQuery(queryName).getResultList();
    }

    @Override
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();
    }

    @Override
    public List findWithNamedQuery(String queryName, Map<String, Object> parameters) {
        return findWithNamedQuery(queryName, parameters, 0);
    }

    @Override
    public List findWithNamedQuery(String queryName, Map<String, Object> parameters, int resultLimit) {
        Query query = this.em.createNamedQuery(queryName);
        if(resultLimit > 0){
            query.setMaxResults(resultLimit);
        }
            
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public <T> int count(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public <T> List findAll(Class<T> entityClass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    
}