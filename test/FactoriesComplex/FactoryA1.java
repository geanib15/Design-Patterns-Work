/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactoriesComplex;

import Implementations.ImplementationA1;
import Implementations.ImplementationB1;
import Implementations.ImplementationC1;
import common.DependencyException;
import complex.Factory;
import interfaces.InterfaceB;
import interfaces.InterfaceC;

/**
 *
 * @author genis
 */
public class FactoryA1 implements Factory<ImplementationA1> {
    
    @Override
    public ImplementationA1 create (Object... parameters) throws DependencyException{
        InterfaceB b;
        InterfaceC c;
        
        try{
            b = (ImplementationB1) parameters[0];
            c = (ImplementationC1) parameters[1];
        
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationA1(b, c); 
    }
    
}
