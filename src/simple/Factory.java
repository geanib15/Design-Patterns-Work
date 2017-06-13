/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import common.DependencyException;

/**
 *
 * @author genis
 */
public interface Factory {
    
    Object create(Object... parameters) throws DependencyException;
    
}
