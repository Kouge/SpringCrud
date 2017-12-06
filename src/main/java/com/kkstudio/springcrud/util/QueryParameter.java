/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkstudio.springcrud.util;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Xiangsheng Li
 */
public class QueryParameter {

    private Map parameters = null;

    private QueryParameter(String name, Object value) {
        this.parameters = new HashMap();
        this.parameters.put(name, value);
    }

    public static QueryParameter with(String name, Object value) {
        return new QueryParameter(name, value);
    }

    public QueryParameter and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map parameters() {
        return this.parameters;
    }
}
