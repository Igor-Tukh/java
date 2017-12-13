package ru.spbau.mit.tukh.test;

import org.junit.Test;
import ru.spbau.mit.tukh.cw02.Injector;
import ru.spbau.mit.tukh.testClasses.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestInjector {
    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.ClassWithoutDependencies", Collections.emptyList());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                "ru.spbau.mit.tukh.testClasses.ClassWithOneClassDependency",
                Collections.singletonList("ru.spbau.mit.tukh.testClasses.ClassWithoutDependencies")
        );
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                "ru.spbau.mit.tukh.testClasses.ClassWithOneInterfaceDependency",
                Collections.singletonList("ru.spbau.mit.tukh.testClasses.InterfaceImpl")
        );
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }

    @Test(expected = ru.spbau.mit.tukh.cw02.InjectionCycleException.class)
    public void injectorShouldFindCircleDependencyException() throws Exception {
        ArrayList<String> dependencies = new ArrayList();
        dependencies.add("ru.spbau.mit.tukh.testClasses.CircleDependencySecondClass");
        dependencies.add("ru.spbau.mit.tukh.testClasses.CircleDependencyThirdClass");
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.CircleDependencyFirstClass",
                dependencies);
    }

    @Test(expected = ru.spbau.mit.tukh.cw02.AmbiguousImplementationException.class)
    public void injectorShouldFindAmbiguousImplementationException() throws Exception {
        ArrayList<String> dependencies = new ArrayList();
        dependencies.add("ru.spbau.mit.tukh.testClasses.FirstImplClass");
        dependencies.add("ru.spbau.mit.tukh.testClasses.SecondImplClass");
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.TwoInterfacesImplClass",
                dependencies);
    }

    @Test(expected = ru.spbau.mit.tukh.cw02.ImplementationNotFoundException.class)
    public void injectorShouldFindNoImplementationException() throws Exception {
        ArrayList<String> dependencies = new ArrayList();
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.ClassWithOneInterfaceDependency",
                dependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithInterfaceAndClassDependency() throws Exception {
        ArrayList<String> dependencies = new ArrayList();
        dependencies.add("ru.spbau.mit.tukh.testClasses.InterfaceImpl");
        dependencies.add("ru.spbau.mit.tukh.testClasses.ClassWithOneInterfaceDependency");
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.InterfaceAndClassDependencyClass",
                dependencies);
        assertTrue(object instanceof InterfaceAndClassDependencyClass);
    }

    @Test
    public void injectorShouldMakeOnlyOneObject() throws Exception {
        Object object = Injector.initialize("ru.spbau.mit.tukh.testClasses.TwoDependenciesToOneClass",
                Collections.singletonList("ru.spbau.mit.tukh.testClasses.CounterClass"));
        assertEquals(1, CounterClass.count);
    }
}