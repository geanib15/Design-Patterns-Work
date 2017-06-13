/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import Implementations.ImplementationB1;
import Implementations.ImplementationD1;
import common.DependencyException;
import interfaces.InterfaceD;
import simple.Factory;

/**
 *
 * @author genis
 */
public class FactoryB1 implements Factory{
    
    @Override
    public ImplementationB1 create (Object... parameters) throws DependencyException{
       
        InterfaceD d;
        try{
            d = (ImplementationD1) parameters[0];
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        
        return new ImplementationB1(d);
    }
    
}
