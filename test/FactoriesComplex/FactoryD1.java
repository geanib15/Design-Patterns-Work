/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoriesComplex;

import Implementations.ImplementationD1;
import common.DependencyException;
import complex.Factory;

/**
 *
 * @author genis
 */
public class FactoryD1 implements Factory<ImplementationD1> {
    
     @Override
    public ImplementationD1 create (Object... parameters) throws DependencyException{
        int i;
        try{
            i = (int) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationD1(i);        
    }
    
}
