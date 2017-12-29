/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.pldialog;

import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vdnh
 */
public class DialogInputs_plTest {
    
    public DialogInputs_plTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findByPrimaryKey method, of class DialogInputs_pl.
     */
    @Test
    public void testFindByPrimaryKey() {
        System.out.println("findByPrimaryKey");
        Long primaryKey = 1001001L;
        DialogInputs_pl instance = new DialogInputs_pl();
        //DialogInputs_pl expResult = null;
        DialogInputs_pl result = instance.findByPrimaryKey(primaryKey);
        //assertEquals(expResult, result);
        assertNotNull(result);
        System.out.println("This is : "+result.getDialogInputId());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findByDialogOutputId method, of class DialogInputs_pl.
     */
//    @Test
//    public void testFindByDialogOutputId() {
//        System.out.println("findByDialogOutputId");
//        Long dialogOutputId = null;
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Collection expResult = null;
//        Collection result = instance.findByDialogOutputId(dialogOutputId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDialogInputId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetDialogInputId() {
//        System.out.println("getDialogInputId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getDialogInputId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDialogId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetDialogId() {
//        System.out.println("getDialogId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getDialogId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDialogOutputId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetDialogOutputId() {
//        System.out.println("getDialogOutputId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getDialogOutputId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAnswerLabel method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetAnswerLabel() {
//        System.out.println("getAnswerLabel");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getAnswerLabel();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecordingTimeout method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetRecordingTimeout() {
//        System.out.println("getRecordingTimeout");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getRecordingTimeout();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecordingErrorMessageId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetRecordingErrorMessageId() {
//        System.out.println("getRecordingErrorMessageId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getRecordingErrorMessageId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecordingTimeoutErrorMessag method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetRecordingTimeoutErrorMessag() {
//        System.out.println("getRecordingTimeoutErrorMessag");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getRecordingTimeoutErrorMessag();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRecordingDestination method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetRecordingDestination() {
//        System.out.println("getRecordingDestination");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getRecordingDestination();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPostProcedureId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetPostProcedureId() {
//        System.out.println("getPostProcedureId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getPostProcedureId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPostKoProcedureId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetPostKoProcedureId() {
//        System.out.println("getPostKoProcedureId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getPostKoProcedureId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNextDialogOutputId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetNextDialogOutputId() {
//        System.out.println("getNextDialogOutputId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getNextDialogOutputId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPostKoNextDialogOutputId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetPostKoNextDialogOutputId() {
//        System.out.println("getPostKoNextDialogOutputId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getPostKoNextDialogOutputId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDtmfMask method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetDtmfMask() {
//        System.out.println("getDtmfMask");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getDtmfMask();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValMin method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetValMin() {
//        System.out.println("getValMin");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getValMin();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getValMax method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetValMax() {
//        System.out.println("getValMax");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getValMax();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMessNumber method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetMessNumber() {
//        System.out.println("getMessNumber");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getMessNumber();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getVariableFiltre method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetVariableFiltre() {
//        System.out.println("getVariableFiltre");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getVariableFiltre();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAffectParameterId method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetAffectParameterId() {
//        System.out.println("getAffectParameterId");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        Long expResult = null;
//        Long result = instance.getAffectParameterId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNextDialogOutputVar method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetNextDialogOutputVar() {
//        System.out.println("getNextDialogOutputVar");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getNextDialogOutputVar();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPostKoNextDialogOutputVar method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetPostKoNextDialogOutputVar() {
//        System.out.println("getPostKoNextDialogOutputVar");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getPostKoNextDialogOutputVar();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getConfirmRecording method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetConfirmRecording() {
//        System.out.println("getConfirmRecording");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getConfirmRecording();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTofile method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetTofile() {
//        System.out.println("getTofile");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getTofile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFileWav method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetFileWav() {
//        System.out.println("getFileWav");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getFileWav();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMessageCode method, of class DialogInputs_pl.
//     */
//    @Test
//    public void testGetMessageCode() {
//        System.out.println("getMessageCode");
//        DialogInputs_pl instance = new DialogInputs_pl();
//        String expResult = "";
//        String result = instance.getMessageCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
