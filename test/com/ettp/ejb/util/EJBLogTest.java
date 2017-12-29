/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author vdnh
 */
public class EJBLogTest {
    
    public EJBLogTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSomeMethod() {
    }

    @Test
    public void testClose() {
        System.out.println("close");
        EJBLog instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInfo_String() {
        System.out.println("info");
        String message = "";
        EJBLog instance = null;
        instance.info(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFine_String() {
        System.out.println("fine");
        String message = "";
        EJBLog instance = null;
        instance.fine(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFinest_String() {
        System.out.println("finest");
        String message = "";
        EJBLog instance = null;
        instance.finest(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSevere_String() {
        System.out.println("severe");
        String message = "";
        EJBLog instance = null;
        instance.severe(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testWarning_String() {
        System.out.println("warning");
        String message = "";
        EJBLog instance = null;
        instance.warning(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInfo_Exception() {
        System.out.println("info");
        Exception ex = null;
        EJBLog instance = null;
        instance.info(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFine_Exception() {
        System.out.println("fine");
        Exception ex = null;
        EJBLog instance = null;
        instance.fine(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFinest_Exception() {
        System.out.println("finest");
        Exception ex = null;
        EJBLog instance = null;
        instance.finest(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSevere_Exception() {
        System.out.println("severe");
        Exception ex = null;
        EJBLog instance = null;
        instance.severe(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testWarning_Exception() {
        System.out.println("warning");
        Exception ex = null;
        EJBLog instance = null;
        instance.warning(ex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsDebugMode() {
        System.out.println("isDebugMode");
        EJBLog instance = null;
        boolean expResult = false;
        boolean result = instance.isDebugMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
