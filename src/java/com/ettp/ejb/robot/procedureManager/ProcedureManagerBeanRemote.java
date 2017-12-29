/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.procedureManager;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.Remote;

/**
 *
 * @author vdnh
 */
@Remote
public interface ProcedureManagerBeanRemote {

    public void init(String sessionId, Long dialogId);
    
    ParameterInfos[] getProcedureParameters(Long procedureId) throws RemoteException;

    String getProcedureType(Long procedureId) throws RemoteException;

    ParameterInfos getParamaterInfos(Long parameterId) throws RemoteException;

    String getProcedureName(Long procedureId) throws RemoteException;

    void remove();

    
}
