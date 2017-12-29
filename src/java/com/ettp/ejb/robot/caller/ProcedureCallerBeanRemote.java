/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.caller;

import java.rmi.RemoteException;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.ejb.Remote;

/**
 *
 * @author vdnh
 */
@Remote
public interface ProcedureCallerBeanRemote {

//    ProcedureCallerBeanRemote create(String sessionId) throws CreateException, RemoteException;
    public void init(String sessionId);
    
    Hashtable invokeActivitiesProcedure(String procName, String[] parameters) throws RemoteException;

    void closeConnection() throws RemoteException;

    void doCommit() throws RemoteException;

    void doRollback() throws RemoteException;

    void sendMail(String expediteur, String destinataire, String sujet, String message) throws RemoteException;

    void remove();
    
}
