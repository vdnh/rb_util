/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.sessionManager;

import com.ettp.ejb.util.EJBLog;
import com.ettp.ejb.util.sequence.SequenceRemote;
import com.ettp.pldialog.DialogOutputs_pl;
import com.ettp.plmessage.Message_pl;
import com.ettp.plsession.Bugs_pl;
import com.ettp.plsession.CallsLog_pl;
import com.ettp.plsession.CommonBlobs_pl;
import com.ettp.plsession.OnlineTelServices_pl;
import com.ettp.plsession.ProceduresLog_pl;
import com.ettp.plsession.PushedKeyLog_pl;
import com.ettp.plsession.RecordingBlobs_Hard_pl;
import com.ettp.plsession.RecordingBlobs_pl;
import com.ettp.plsession.RecordingLog_pl;
import com.ettp.plsession.Services_pl;
import com.ettp.plsession.UsersPersons_pl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vdnh
 */
@Stateful (mappedName = "sessionManager")
public class SessionManagerBean implements SessionManagerBeanRemote {
    private static int SHORT_MESSAGE = 0;
    private static int LONG_MESSAGE = 1;
    private SessionContext context;
    private int messagePolicy = LONG_MESSAGE;
  //ajout de Hung
    protected String originGroupCode;
    private PushedKeyLog_pl pklHome = new PushedKeyLog_pl();
    private RecordingLog_pl rlHome = new RecordingLog_pl();
    private RecordingBlobs_pl rbHome = new RecordingBlobs_pl();
    private RecordingBlobs_Hard_pl rbhHome = new RecordingBlobs_Hard_pl();
    private CommonBlobs_pl bcHome = new CommonBlobs_pl();
    private ProceduresLog_pl plHome = new ProceduresLog_pl();
    private Bugs_pl bugsHome = new Bugs_pl();
    private CallsLog_pl cl= null;// = new CallsLog_pl();
    private PushedKeyLog_pl currentPkl = null; //new PushedKeyLog_pl();
    private RecordingLog_pl rl= null;
    private UsersPersons_pl up = new UsersPersons_pl();
  //
    private String language = null;
    private int increment_recording = 0;
    private Hashtable sessionParameters = new Hashtable();
    Long userPersonId;
    public String calledNumber;
    public String callerNumber;
    public Long serviceId;
    public Long serviceDialogId;
    protected EJBLog ejbLog;


//    public SessionManagerBeanRemote create() throws CreateException, RemoteException {
//        //return this;
//        return null;
//    }

//    public void ejbCreate() throws CreateException {
//    }

//    public SessionManagerBeanRemote create(String sessionId) throws CreateException, RemoteException {
////        initLogger(sessionId);
////        return this;
//        return null;
//    }
//
//    public void ejbCreate(String sessionId) throws CreateException {
//    }

//    public SessionManagerBeanRemote create(String sessionId, String calledNumber, String callingNumber) throws CreateException, RemoteException {
//        initLogger(sessionId);
//
//    try {
//      this.calledNumber = calledNumber;
//      this.callerNumber = callingNumber;
//      //ajout de Hung
//      OnlineTelServices_pl onlineTelService = null;
//      Services_pl service = null;
//      //try{
//        OnlineTelServices_pl onlineTelServiceHome= new OnlineTelServices_pl();
//        //OnlineTelServices_pl
//        onlineTelService = onlineTelServiceHome.findByServicePhoneNumber(calledNumber);
//      //}
//      //catch(Exception e){
//      if(onlineTelService == null || onlineTelService.getDialogId().toString().equalsIgnoreCase("0")){
//        System.out.println("Jouer avec table All_Top_Service");
//        Services_pl serviceHome= new Services_pl();
//        //Services_pl
//        service = serviceHome.findByServicePhoneNumber(calledNumber);
//      }
//      // ajout de Hung, condition pour chercher dialogId
//      if(onlineTelService !=null && !onlineTelService.getDialogId().toString().equalsIgnoreCase("0")) {
//        serviceDialogId = onlineTelService.getDialogId();
//      }
//      else {
//        serviceDialogId = service.getDialogId();
//        serviceId = service.getServiceId();
//      }
//      //
//      System.out.println("dialogId= "+this.serviceDialogId.toString());
//      this.setLanguage("FR");
//
//      ejbLog.info("SessionManager created");
//
//      userPersonId = new Long(10000);
//
//      try {
//
//        //ajout de Hung
//        UsersPersons_pl user = up.findByMobilePhone(callingNumber);
//        //
//
//        if (user != null) {
//          userPersonId = user.getUserPersonId();
//        }
//      }
//      catch (Exception ex) {
//        ejbLog.info("no user with : " + callingNumber);
//      }
//
//      InitialContext ctx = new InitialContext();
//      //ajout de Hung
//      CallsLog_pl clHome = new CallsLog_pl();
//      System.out.println("CallsLog_pl est OK");
//      //ajout de Hung, pour ONLINE_TEL_SERVICE
//      if(onlineTelService!=null && !onlineTelService.getDialogId().toString().equalsIgnoreCase("0")) {
//        this.cl = clHome.create(calledNumber, userPersonId, getDate(), "I", new Long(2));
//      }
//      else this.cl = clHome.create(calledNumber, userPersonId, getDate(), "I", serviceId);
//      ejbLog.info("new call log : " + cl.getCallLogId());
//      cl.setCallerNumber(callingNumber);
//    }
//    catch (Exception ex) {
//      ejbLog.severe(ex);
//    }
//        return this;
//        return null;
//    }

//    public void ejbCreate(String calledNumber, String callingNumber) throws CreateException {
//    }
    public void init(String sessionId) {
    initLogger(sessionId);
  }

  private void initLogger(String sessionId) {
    ejbLog = new EJBLog(sessionId);
  }

  public void init(String sessionId, String calledNumber, String callingNumber) {
    initLogger(sessionId);

    try {
      this.calledNumber = calledNumber;
      this.callerNumber = callingNumber;
      //ajout de Hung
      OnlineTelServices_pl onlineTelService = null;
      Services_pl service = null;
      //try{
        OnlineTelServices_pl onlineTelServiceHome= new OnlineTelServices_pl();
        //OnlineTelServices_pl
        onlineTelService = onlineTelServiceHome.findByServicePhoneNumber(calledNumber);
      //}
      //catch(Exception e){
      if(onlineTelService == null || onlineTelService.getDialogId().toString().equalsIgnoreCase("0")){
        System.out.println("Jouer avec table All_Top_Service");
        Services_pl serviceHome= new Services_pl();
        //Services_pl
        service = serviceHome.findByServicePhoneNumber(calledNumber);
      }
      // ajout de Hung, condition pour chercher dialogId
      if(onlineTelService !=null && !onlineTelService.getDialogId().toString().equalsIgnoreCase("0")) {
        serviceDialogId = onlineTelService.getDialogId();
      }
      else {
        serviceDialogId = service.getDialogId();
        serviceId = service.getServiceId();
      }
      //
      System.out.println("dialogId= "+this.serviceDialogId.toString());
      this.setLanguage("FR");

      ejbLog.info("SessionManager created");

      userPersonId = new Long(10000);

      try {

        //ajout de Hung
        UsersPersons_pl user = up.findByMobilePhone(callingNumber);
        //

        if (user != null) {
          userPersonId = user.getUserPersonId();
        }
      }
      catch (Exception ex) {
        ejbLog.info("no user with : " + callingNumber);
      }

      InitialContext ctx = new InitialContext();
      //ajout de Hung
      CallsLog_pl clHome = new CallsLog_pl();
      System.out.println("CallsLog_pl est OK");
      //ajout de Hung, pour ONLINE_TEL_SERVICE
      if(onlineTelService!=null && !onlineTelService.getDialogId().toString().equalsIgnoreCase("0")) {
        this.cl = clHome.create(calledNumber, userPersonId, getDate(), "I", new Long(2));
      }
      else this.cl = clHome.create(calledNumber, userPersonId, getDate(), "I", serviceId);
      ejbLog.info("new call log : " + cl.getCallLogId());
      cl.setCallerNumber(callingNumber);
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
    ejbLog.close();
  }

  public void setSessionContext(SessionContext ctx) {
    this.context = ctx;
  }

  public void afterBegin() {
  }

  public void beforeCompletion() {
  }

  public void afterCompletion(boolean committed) {
  }

  /*
   * buisness methods
   */
  public int getMessagePolicy() {
    return this.messagePolicy;
  }

  public void setOriginGroupCode(String originGroupCode) {
    this.originGroupCode= originGroupCode;
    cl.setOriginGroupCode(originGroupCode);
    //ajout de Hung
    System.out.println("originGroupCode= "+originGroupCode);
    //
  }

    @Override
  public void closeCall() {
    try {
      this.cl.setDateEndCall(getDate());
      //this.logCallV2.setDateEndCall(getDate());
    }
    catch (Exception e) {
      ejbLog.severe(e);
    }
  }

  protected java.sql.Timestamp getDate() {
    return new java.sql.Timestamp(System.currentTimeMillis());
  }

  protected java.sql.Timestamp getTime() {
    return new java.sql.Timestamp(System.currentTimeMillis());
  }

  public String concatane_Id(Long doid) {
    StringBuffer retour= new StringBuffer("");
    DialogOutputs_pl dialogOutput= new DialogOutputs_pl();
    Message_pl message_pl= new Message_pl();
    boolean existe;
    dialogOutput.findByPrimaryKey(doid);
    message_pl= message_pl.findByMessageNumber(dialogOutput.getMessNumber());
    retour= retour.append(message_pl.getOriginGroupCode()).append(message_pl.getLanguageCode());

    return retour.toString();
  }
  //ajout de Hung
    @Override
  public Long addRecording(byte[] recording, Double duration, String groupCode, Long doid) {
  //
    try {
      ejbLog.info("adding a recording log");
      //garder la valeur de RecordingLogId
      Long recordingLogId= null;
      //
      if (recording != null) {
        this.rl = rlHome.create(this.cl.getCallLogId(), getTime(), groupCode);
        recordingLogId= rl.getRecordingLogId();
        System.out.println("Hung - recordingLogId est: "+recordingLogId);
        ejbLog.fine("recording log created and is setting");

        RecordingBlobs_pl rb;// = rbHome.findByPrimaryKey(recordingBlobId);
          rb = rbHome.create(recordingLogId);
          System.out.println("SessionManagerBean - recordingBlobId est cree : "+rb.getRecordingBlobId());//recordingBlobId);
        rl.setRecordingBlobId(rb.getRecordingBlobId());
        rl.setRecordingDuration(duration);
        ejbLog.finest("before adding blob data");

        if (recording.length < (0 * 256 * 1024)) {
          ejbLog.finest("setting blob data main thread");

          try {
            //System.out.println("Hung - commence recording: "+recordingBlobId);
            rb.setRecording(recording);
            rb.setRecordingSize(new Double(recording.length));
            ejbLog.finest("blob data set in main thread");
          }
          catch (Exception e) {
            ejbLog.finest("error setting blob date in main thread");
            ejbLog.severe(e);

            saveFile(rb.getRecordingBlobId().toString() + ".wav", recording);
          }
        }
        else {
          //System.out.println("Hung - commence recording avec BlobWriter: "+recordingBlobId);
          BlobWriter br = new BlobWriter(this, rb, recording);
          Thread t = new Thread(br);
          t.start();
          //ajout de Hung
          //br.run_write();
          //
        }

        ejbLog.finest("after adding blob data");

        return rl.getRecordingLogId();
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      saveFile(System.currentTimeMillis() + ".wav", recording);
    }

    //TODO save recordign if problem
    return null;
  }

  protected void saveFile(String name, byte[] bytes) {
    try {
      String recDirName = "recordings" + File.separator + "saved_blobs_error" + File.separator;
      File recDir = new File(recDirName);

      if (!recDir.exists()) {
        recDir.mkdirs();
      }

      FileOutputStream fos = new FileOutputStream(recDirName + name);
      fos.write(bytes);
      fos.flush();
      fos.close();
    }
    catch (IOException ex) {
      ejbLog.severe(ex);
    }
  }
  //ajout de Hung
    @Override
  public Long addRecording(String transcription, Double duration, String groupCode, Long doid) {
  //
    try {
      ejbLog.info("adding a recording log");
      //garder la valeur de RecordingLogId
      Long recordingLogId= null;
      //
      if (transcription != null) {
        this.rl = rlHome.create(this.cl.getCallLogId(), getTime(), groupCode);
        recordingLogId= rl.getRecordingLogId();
        ejbLog.fine("recording log created and is setting");
        //ajout de Hung
        RecordingBlobs_pl rb= null;
        //
          rb = rbHome.create(recordingLogId);
          System.out.println("SessionManagerBean - recordingBlobId est cree : "+rb.getRecordingBlobId());//recordingBlobId);

        rl.setRecordingBlobId(rb.getRecordingBlobId());
        rl.setRecordingDuration(duration);
        //
        ejbLog.finest("blob : set transcription");
        rb.setTranscription(transcription);
        rb.setRecordingSize(new Double(transcription.length()));
        ejbLog.finest("blob : transcription set");

        return rl.getRecordingLogId();
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public void addPushedKey(String pushedKey, Long did, Long doid) {
    try {
      this.currentPkl = pklHome.create(this.cl.getCallLogId(), getTime(), pushedKey, did, doid);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public void addRecordingPushedKey(String pushedKey, Timestamp ts, Long did, Long doid, Long recordingLogId) {
    try {
      this.currentPkl = pklHome.create(this.cl.getCallLogId(), ts, pushedKey, did, doid, recordingLogId);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public void validatePushedKey() {
    try {
      this.currentPkl.setValid("Y");
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public String getLanguage() {
    return this.language;
  }

    @Override
  public void setLanguage(String newLanguage) {
    this.language = newLanguage;
  }

    @Override
  public String getCallerNumber() {
    try {
      return callerNumber;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public String getCalledNumber() {
    try {
      return calledNumber;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public void setCallerNumber(String callerNumber) {
    this.callerNumber = callerNumber;
  }

    @Override
  public Long getServiceDialogId() {
    return serviceDialogId;
  }

    @Override
  public Hashtable setParam(int idProcedure, String value) {
    return null;
  }

    @Override
  public Long getServiceId() {
    return serviceId;
  }

    @Override
  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }

    @Override
  public void setUserInfos(Long userId, String calledNumber, String languageCode) {
    try {
      userPersonId = userId;
      this.setLanguage(languageCode);
      cl.setCalledNumber(calledNumber);
      cl.setUserPersonId(userPersonId);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

  public Collection getRecordingLogs(boolean all) {
    try {
      Collection recLogs = rlHome.findIfFileNotNull();
      Iterator iter = recLogs.iterator();
      ejbLog.finest("RecordingLogs found : " + recLogs.size());

      return recLogs;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

    @Override
  public byte[] getRecordingBlobData(Long recordingLogId) {
    try {
      Long recordingBlobId = rlHome.findByPrimaryKey(recordingLogId).getRecordingBlobId();
      RecordingBlobs_pl rb = rbHome.findByPrimaryKey(recordingBlobId);
      //
      ejbLog.fine("get log data : " + recordingLogId);

      //ejbLog.fine("\tget blob data : " + rb.getRecordingBlobId() + " " + rb.getRecording().length);
      return rb.getRecording();
      //return rbh.getRecording();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  public void setRecordingBlobData(Long recordingLogId, byte[] recording) {
    try {
      Long recordingBlobId = rlHome.findByPrimaryKey(recordingLogId).getRecordingBlobId();
      RecordingBlobs_pl rb = rbHome.findByPrimaryKey(recordingBlobId);
      //
      ejbLog.fine("set blob data : " + rb.getRecordingBlobId() + " " + recording.length);
      ejbLog.finest("before setting blob data");

      if (recording.length < (256 * 1024)) {
        ejbLog.finest("setting blob data main thread");
        rb.setRecording(recording);
        ejbLog.finest("blob data set in main thread");
      }
      else {
        BlobWriter br = new BlobWriter(this, rb, recording);
        (new Thread(br)).start();
        //ajout de Hung
        //br.run_write();
        //
      }

      ejbLog.finest("after setting blob data");
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public void addProceduresLog(String logCall, String logReturn) {
    try {
      plHome.create(this.cl.getCallLogId(), logCall, logReturn, getTime());
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public void setCalledNumber(String calledNumber) {
    this.calledNumber = calledNumber;
  }

    @Override
  public Long getCallLogId() {
    return cl.getCallLogId();
  }

    @Override
  public Long getUserPersonId() {
    return userPersonId;
  }

    @Override
  public void createBug(Timestamp date, String description, String auteur, String etat, String originGroupCode) {
    //try {
      bugsHome.create(date, description, auteur, etat, originGroupCode);
  }

  ///* ajout de Hung
    @Override
  public byte[] getRecordingBlobDataDirect(Long recordingBlobId)
  {
    try {
      RecordingBlobs_pl rb = rbHome.findByPrimaryKey(recordingBlobId);
      RecordingBlobs_Hard_pl rbh = rbhHome.findByPrimaryKey(recordingBlobId);
      //ejbLog.fine("get Blob data : " + recordingBlobId);
      //return rb.getRecording();
      return rbh.getRecording();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

    @Override
  public byte[] getRecordingBlobDataCommon(String blobCode)
  {
    try {
      CommonBlobs_pl bc = bcHome.findByPrimaryKey(blobCode);
      //ejbLog.fine("get Blob data : " + blobCode);
      return bc.getRecording();
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

  private SequenceRemote getSequenceHome() throws NamingException {
    final InitialContext context = new InitialContext();

    return (SequenceRemote) context.lookup("squence");
  }

  public void init()
  {
  }

    @Override
    public void remove() {
        ejbLog.close();
    }
}
