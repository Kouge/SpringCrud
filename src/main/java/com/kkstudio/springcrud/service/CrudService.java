/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkstudio.springcrud.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Xiangsheng Li
 */
public interface CrudService {

    public <T> void create(T entity);

    public <T> void edit(T entity);

    public <T> void remove(T entity);
    
    public <T> T find(Class<T> entityClass, Object id);
    
    public <T> List findAll(Class<T> entityClass);

    public List findWithNamedQuery(String queryName);

    public List findWithNamedQuery(String queryName, int resultLimit);

    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

    public List findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);
    
    public <T> int count(Class<T> entityClass);
}
