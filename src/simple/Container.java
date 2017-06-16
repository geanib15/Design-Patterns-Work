/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author genis
 */
public class Container implements Injector {

    Map<String,Object> constants = new HashMap<String,Object>();
    Map<String,Factory> factories = new HashMap<String,Factory>();
    Map<String,String[]> factoryDependences = new HashMap<String,String[]>();
    
    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (constants.containsKey(name)) throw new DependencyException("Already existing constant");
        constants.put(name, value);
    }
    
    @Override
    public void registerFactory(String name, Factory creator, String[] parameters) throws DependencyException {
       if (constants.containsKey(name) || factories.containsKey(name)) throw new DependencyException("Already existing machine");
       factories.put(name, creator);
       factoryDependences.put(name, parameters);
        
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        if (constants.containsKey(name)){
            return constants.get(name);
        }        
        else if (factories.containsKey(name)){
            Factory factory = factories.get(name);
            Object[] obj = new Object[factoryDependences.get(name).length];
            for (int i = 0; i < obj.length; ++i){
                obj[i] = this.getObject(factoryDependences.get(name)[i]);
            }            
           return factory.create(obj);            
        }
        else{
            throw new DependencyException("Already existing object");            
        }    
    }
    
    
    
}
