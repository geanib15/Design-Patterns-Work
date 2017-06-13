/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author genis
 */
public class Container implements Injector {

    Map<String,Object> map = new HashMap<String,Object>();
    
    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        if (map.containsKey(name)) throw new DependencyException("Already existing constant");
        map.put(name, value);
    }
    
    @Override
    public void registerFactory(String name, Factory creator, String... parameters) throws DependencyException {
       if (map.containsKey(name)) throw new DependencyException("Already existing machine");
       
        
    }

    @Override
    public Object getObject(String name) throws DependencyException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
