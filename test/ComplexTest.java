
import FactoriesComplex.*;
import Implementations.*;
import common.DependencyException;
import complex.Container;
import complex.Injector;
import interfaces.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author genis
 */
public class ComplexTest {
    
    
    private Injector injector;
    
    @Before
    public void SimpleTest(){
        injector = new Container();
    }
    
    @Test
    public void testD() throws DependencyException{   

        System.out.println("INICI TEST 1");
        System.out.println("");
        injector.registerConstant(Integer.class, 42);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        InterfaceD d = injector.getObject(InterfaceD.class);
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
        System.out.println("");
        System.out.println("TEST 1 FINALITZAT CORRECTAMENT");
    }
    
    @Test
    public void testC() throws DependencyException{ 

        System.out.println("INICI TEST 2");
        System.out.println("");
        injector.registerConstant(String.class, "Testing");
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        InterfaceC c = injector.getObject(InterfaceC.class);
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(c1.s, is("Testing"));
        System.out.println("");
        System.out.println("TEST 2 FINALITZAT CORRECTAMENT");
    }
    
    @Test
    public void testB() throws DependencyException{
        System.out.println("INICI TEST 3");
        System.out.println("");
        injector.registerConstant(Integer.class, 42);
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        InterfaceB b = injector.getObject(InterfaceB.class);
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
        System.out.println("");
        System.out.println("TEST 2 FINALITZAT CORRECTAMENT");        
    }
    
    @Test
    public void testA() throws DependencyException{
        System.out.println("INICI TEST 4");
        System.out.println("");
        injector.registerConstant(Integer.class, 42);
        injector.registerConstant(String.class, "Testing");
        injector.registerFactory(InterfaceD.class, new FactoryD1(), Integer.class);
        injector.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        injector.registerFactory(InterfaceB.class, new FactoryB1(), InterfaceD.class);
        injector.registerFactory(InterfaceA.class, new FactoryA1(), InterfaceB.class, InterfaceC.class);
        InterfaceA a = injector.getObject(InterfaceA.class);
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        ImplementationA1 a1 = (ImplementationA1) a;
        assertThat(a1.b, is(instanceOf(ImplementationB1.class)));
        assertThat(a1.c, is(instanceOf(ImplementationC1.class)));
        System.out.println("");
        System.out.println("TEST 2 FINALITZAT CORRECTAMENT"); 
    }
    
     @Test(expected = DependencyException.class)
    public void alreadyExistingConstantInMapOfConstants() throws DependencyException{
        Container c = new Container();
        c.registerConstant(Integer.class, 3);
        c.registerConstant(Integer.class, 5);       
    }
    
    @Test(expected = DependencyException.class)
    public void alreadyExistingFactoryInMapOfFactories() throws DependencyException{
        Container c = new Container();
        c.registerFactory(InterfaceA.class, new FactoryA1(), ImplementationB1.class, ImplementationC1.class);
        c.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
        c.registerFactory(InterfaceC.class, new FactoryC1(), String.class);
    }
    
     @Test(expected = DependencyException.class)
    public void getObjectthatDoesntExistis() throws DependencyException{
        Container c = new Container();
        
    }
    
}
