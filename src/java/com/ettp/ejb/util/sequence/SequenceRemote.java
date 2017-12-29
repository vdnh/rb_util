/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.util.sequence;

import java.rmi.RemoteException;
import javax.ejb.Remote;
//import javax.ejb.SessionContext;

/**
 *
 * @author vdnh
 */
@Remote
public interface SequenceRemote {
    public int getNextValue(String keyName) throws RemoteException;
    public int getNextValueByHashval(String keyName) throws RemoteException;
}
