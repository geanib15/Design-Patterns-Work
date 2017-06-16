/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

import interfaces.InterfaceA;
import interfaces.InterfaceB;
import interfaces.InterfaceC;

/**
 *
 * @author genis
 */
public class ImplementationA1 implements InterfaceA {
    
    public InterfaceB b;
    public InterfaceC c;
    
    public ImplementationA1(InterfaceB b, InterfaceC c){
        
        this.b = b;
        this.c = c;
    }    
}
