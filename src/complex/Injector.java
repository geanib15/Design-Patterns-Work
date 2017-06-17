/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complex;

import common.DependencyException;

/**
 *
 * @author genis
 */
public interface Injector {
    
    <E> void registerConstant(Class<E> name, E value) throws DependencyException;
    <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters)throws DependencyException;
    <E> E getObject(Class<E> name) throws DependencyException;
    
}
