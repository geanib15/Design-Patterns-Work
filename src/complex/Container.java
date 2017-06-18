/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.DependencyException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author genis
 */
public class Container implements Injector {
    
    Map<Class<?>,Object> constants = new HashMap<>();
    Map<Class<?>,Factory<?>> factories = new HashMap<>();
    Map<Class<?>,Class[]> factoryDependences = new HashMap<>();    
    

    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
       if (constants.containsKey(name)) throw new DependencyException("Already existing constant");
       constants.put(name, value);
    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
       if (factories.containsKey(name) || factoryDependences.containsKey(name)) throw new DependencyException("Already existing machine");
       factories.put(name, creator);
       factoryDependences.put(name, parameters);
    }

    @Override
    public <E> E getObject(Class<E> name) throws DependencyException {
       if (constants.containsKey(name)){
            return (E) constants.get(name);
        }
       else if (factories.containsKey(name)){
           Factory factory = factories.get(name);
           Object[] obj = new Object[factoryDependences.get(name).length];
           for (int i = 0; i < obj.length; ++i){
                obj[i] = this.getObject(factoryDependences.get(name)[i]);
            }
           return (E) factory.create(obj);
       }
        else{
            throw new DependencyException("There is no object");            
        }  
    }
    
}
