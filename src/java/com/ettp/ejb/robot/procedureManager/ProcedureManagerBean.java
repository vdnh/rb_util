/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.procedureManager;

import com.ettp.ejb.util.EJBLog;
import com.ettp.plprocedure.Parameters_pl;
import com.ettp.plprocedure.ProcedureParameters_pl;
import com.ettp.plprocedure.Procedures_pl;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;

/**
 *
 * @author vdnh
 */
@Stateful (mappedName = "procedureManager")
public class ProcedureManagerBean implements ProcedureManagerBeanRemote, SessionSynchronization {
    
    public static final int SEVERE = 10;
    public static final int WARNING = 7;
    public static final int INFO = 5;
    public static final int FINE = 3;
    public static final int FINEST = 0;
    private static int CURENT_LEVEL = FINEST;
    private SessionContext context;
    private EJBLog ejbLog;

    //ajout de Hung
    //private boolean existe_data;
    private ProcedureParameters_pl pph= new ProcedureParameters_pl();
    //


//    public ProcedureManagerBeanRemote create(String sessionId, Long dialogId) throws CreateException, RemoteException {
//        initLogger(sessionId);
//        try {
//          //ajout de Hung
//          pph= new ProcedureParameters_pl();
//          //
//          ejbLog.info("ProcedureManager created");
//        }
//        catch (Exception ex) {
//          ejbLog.severe(ex);
//        }
//        return this;
//        return null;
//    }

    public void init(String sessionId, Long dialogId){
        initLogger(sessionId);

        try {

          //ajout de Hung
          pph= new ProcedureParameters_pl();
          //
          ejbLog.info("ProcedureManager created");
        }
        catch (Exception ex) {
          ejbLog.severe(ex);
        }
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbRemove() {
    }

    public void setSessionContext(SessionContext ctx) {
      this.context = ctx;
    }

    private void initLogger(String sessionId) {
      ejbLog = new EJBLog(sessionId);
    }

    public void afterBegin() {
    }

    public void beforeCompletion() {
    }

    public void afterCompletion(boolean committed) {
    }

    public ParameterInfos[] getProcedureParameters(Long procedureId) {
      /*
       * on recupere la definition des parametres de la procedure
       */
      try {
      //ProcedureParametersLocalHome pph = getProcedureParametersLocalHome();
      //Collection parameters = pph.findByProcedureId(procedureId);
      //ajout de Hung
      //ProcedureParameters_pl pph= new ProcedureParameters_pl();
        Collection parameters = pph.findByProcedureId(procedureId);
        ProcedureParameters_pl pp;// = new ProcedureParameters_pl();
      //System.out.println("Hung - procedureId: "+procedureId);
      //

        Vector vParameters = new Vector(parameters);
        int nbParameter = vParameters.size();
      //System.out.println("Hung - vParameters.size(): "+vParameters.size());
      //System.out.println("Hung - parameters.size(): "+parameters.size());

      //ParametersLocalHome psh = getParametersLocalHome();
      //ajout de Hung
        Parameters_pl psh = new Parameters_pl();
      //

        ParameterInfos[] parameterInfos = new ParameterInfos[nbParameter];
      //ParameterInfos[] parameterInfos = new ParameterInfos[parameters.size()];

      /* ajout de Hung - utiliser Iterator au lieu de Enumeration
      Iterator disIterator = parameters.iterator();
      for (int i = 0; i < parameters.size(); i++) {
        pp = (ProcedureParameters_pl) disIterator.next();
       //*/
        for (Enumeration e = vParameters.elements(); e.hasMoreElements();) {
        //ProcedureParametersLocal pp = (ProcedureParametersLocal) e.nextElement();
        //ProcedureParametersLocal pp = (ProcedureParametersLocal) disIterator.next();
        //ajout de Hung
        //ProcedureParameters_pl
          pp = (ProcedureParameters_pl) e.nextElement();
        //

          int rank = pp.getParameterRank().intValue();
        //System.out.println("Hung - rank: "+rank);
        //if ((rank > nbParameter) || (rank < 1)) {
          if ((rank > parameters.size()) || (rank < 1)) {
          // le rang nest pas valide il y a trop ou pas assez de parametres
            ejbLog.fine("parameter rank error");
          }
          else {
            Long parameterId = pp.getParameterId();
          //ParametersLocal param = psh.findByPrimaryKey(parameterId);
          //ajout de Hung
          //Parameters_pl param = null; //new Parameters_pl();
          //existe_data= param.findByPrimaryKey(parameterId);
            Parameters_pl param= psh.findByPrimaryKey(parameterId);
          //
            parameterInfos[rank - 1] = new ParameterInfosImpl(param.getParameterId(), param.getParameterType(),
              param.getParameterLabel(), param.getParameterValue());
          //System.out.println("Hung - "+parameterInfos[rank - 1].getParameterId());
          //System.out.println("Hung - "+parameterInfos[rank - 1].getParameterLabel());
          //System.out.println("Hung - "+parameterInfos[rank - 1].getParameterValue());
          }
        }

        return parameterInfos;
      }
      catch (Exception ex) {
        ejbLog.severe(ex);
      }

      return null;
    }

  public String getProcedureName(Long procedureId) {
    try {
      //ProceduresLocalHome ph = getProceduresLocalHome();
      //ProceduresLocal proc = ph.findByPrimaryKey(procedureId);
      //ajout de Hung
      Procedures_pl ph = new Procedures_pl();
      //Procedures_pl proc = new Procedures_pl();
      //existe_data= proc.findByPrimaryKey(procedureId);
      Procedures_pl proc= ph.findByPrimaryKey(procedureId);
      //

      return proc.getProcedureName();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public String getProcedureType(Long procedureId) {
    try {
      //ProceduresLocalHome ph = getProceduresLocalHome();
      //ProceduresLocal proc = ph.findByPrimaryKey(procedureId);
      //ajout de Hung
      Procedures_pl ph= new Procedures_pl();
      //Procedures_pl proc = new Procedures_pl();
      //existe_data= proc.findByPrimaryKey(procedureId);
      Procedures_pl proc= ph.findByPrimaryKey(procedureId);
      //

      return proc.getProcedureType();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public ParameterInfos getParamaterInfos(Long parameterId) {
    try {
      //ParametersLocalHome psh = getParametersLocalHome();
      //ParametersLocal param = psh.findByPrimaryKey(parameterId);
      //ajout de Hung
      Parameters_pl psh = new Parameters_pl();
      //Parameters_pl param = null; //new Parameters_pl();
      //existe_data= param.findByPrimaryKey(parameterId);
      Parameters_pl param= psh.findByPrimaryKey(parameterId);
      //

      return new ParameterInfosImpl(param.getParameterId(), param.getParameterType(), param.getParameterLabel(),
        param.getParameterValue());
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

    @Override
    public void remove() {
        ejbLog.close();
    }

}
