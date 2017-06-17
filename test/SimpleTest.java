
import Factories.*;
import Implementations.*;
import common.DependencyException;
import interfaces.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import simple.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author genis
 */
public class SimpleTest {
    
    private Injector injector;
    
    @Before
    public void SimpleTest(){
        injector = new Container();
    }
    
    
    
    @Test
    public void testD() throws DependencyException{   

        System.out.println("INICI TEST 1");
        System.out.println("");
        injector.registerConstant("I",42);
        injector.registerFactory("D",new FactoryD1(),"I");
        InterfaceD d = (InterfaceD) injector.getObject("D");
        assertThat(d,is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 =(ImplementationD1) d;
        assertThat(d1.i, is(42));
        System.out.println("");
        System.out.println("TEST 1 FINALITZAT CORRECTAMENT");
    }

    @Test
    public void testC() throws DependencyException{ 

        System.out.println("INICI TEST 2");
        System.out.println("");
        injector.registerConstant("S","Testing");
        injector.registerFactory("C",new FactoryC1(),"S");
        InterfaceC c = (InterfaceC) injector.getObject("C");
        assertThat(c,is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 =(ImplementationC1) c;
        assertThat(c1.s, is("Testing"));
        System.out.println("");
        System.out.println("TEST 2 FINALITZAT CORRECTAMENT");
    }
    
    @Test
    public void testB() throws DependencyException{
        
        System.out.println("INICI TEST 3");
        System.out.println("");
        injector.registerConstant("I",42);
        injector.registerFactory("B", new FactoryB1(),"D");
        injector.registerFactory("D",new FactoryD1(),"I");
        InterfaceB b = (InterfaceB) injector.getObject("B");
        assertThat(b,is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 =(ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
        System.out.println("");
        System.out.println("TEST 3 FINALITZAT CORRECTAMENT");        
    }
    
    @Test
    public void testA() throws DependencyException{
        System.out.println("INICI TEST 4");
        System.out.println("");
        injector.registerConstant("I",42);
        injector.registerConstant("S","Testing");
        injector.registerFactory("D",new FactoryD1(),"I");
        injector.registerFactory("C",new FactoryC1(),"S");
        injector.registerFactory("B", new FactoryB1(),"D");
        injector.registerFactory("A", new FactoryA1(), "B", "C");
        InterfaceA a = (InterfaceA) injector.getObject("A");
        assertThat(a,is(instanceOf(ImplementationA1.class)));
        ImplementationA1 a1 =(ImplementationA1) a;
        assertThat(a1.b, is(instanceOf(ImplementationB1.class)));
        assertThat(a1.c, is(instanceOf(ImplementationC1.class)));
        System.out.println("");
        System.out.println("TEST 4 FINALITZAT CORRECTAMENT"); 
    }
    
    @Test(expected = DependencyException.class)
    public void alreadyExistingConstantInMapOfConstants() throws DependencyException{
        Container c = new Container();
        c.registerConstant("I", 3);
        c.registerConstant("I", 4);       
    }
    
    @Test(expected = DependencyException.class)
    public void alreadyExistingFactoryInMapOfFactories() throws DependencyException{
        Container c = new Container();
        String[] str = new String[10];
        c.registerFactory("A", new FactoryA1(),str);
        c.registerFactory("A", new FactoryA1(),str);
    }
    
    @Test(expected = DependencyException.class)
    public void getObjectthatDoesntExistis() throws DependencyException{
        Container c = new Container();
        c.getObject("A");
    }
}