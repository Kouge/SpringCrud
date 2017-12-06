/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkstudio.springcrud.controller;

import com.kkstudio.springcrud.service.CrudService;
import com.kkstudio.springcrud.entities.Customer;
import com.kkstudio.springcrud.util.QueryParameter;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Xiangsheng Li
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    static final Logger logger = Logger.getLogger(CustomerController.class);
    
    private Class<Customer> type;

    @Autowired
    private CrudService crud;

    @GetMapping(
            value = "/{id}",
            produces = {"application/xml", "application/json"}
    )
    public @ResponseBody Customer find(@PathVariable Integer id) {
        Customer customer = null;
        try {
            customer = crud.find(type, id);
        } catch(Exception ex) {
            logger.info(ex.getMessage());
        }
        return customer;
    }
    
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello, " + name;
    }
    
    @GetMapping(
            produces = {"application/xml", "application/json"}
    )
    public @ResponseBody List<Customer> findAll() {
        return crud.findAll(type);
    }
    
    
    @PostMapping(
            consumes = {"application/xml", "application/json"}
    )
    public void create(Customer entity) {
        crud.create(entity);
    }

   @PutMapping(
            consumes = {"application/xml", "application/json"}
    )
    public void edit(Customer entity) {
        crud.edit(entity);
    }

    
    @DeleteMapping(
            value = "/{id}"
    )
    public void remove(@PathVariable("id") Integer id) {
        crud.remove(crud.find(type, id));
    }
     
    
    @GetMapping(
            value = "/customers/{from}/{to}",
            produces = {"application/xml", "application/json"}
    )
    public @ResponseBody List<Customer> findRange(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {
        return crud.findWithNamedQuery(Customer.WITHIN_RANGE, QueryParameter.with("from", from).and("to", to).parameters());
    }

    @GetMapping(
            value = "/count",
            produces = {"text/plain"}
    )
    public String countREST() {
        return String.valueOf(crud.count(type));
    }
}