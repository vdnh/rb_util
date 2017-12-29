/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp.ejb.robot.messagesDescriptor;

import com.ettp.OracleJDBCConnection;
import com.ettp.ejb.util.EJBLog;
import com.ettp.plmessage.AllGroups_pl;
import com.ettp.plmessage.AudioPK;
import com.ettp.plmessage.Audio_pl;
import com.ettp.plmessage.DialogOutputMessages_pl;
import com.ettp.plmessage.KEYS_RECORDING_BLOBS;
import com.ettp.plmessage.Message_pl;
import com.ettp.plmessage.MessagesPK;
import com.ettp.plsession.CommonBlobs_pl;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

/**
 *
 * @author vdnh
 */
@Stateful (mappedName = "messagesDescriptor")
public class MessagesDescriptorBean implements MessagesDescriptorBeanRemote {

  public final int SEVERE = 10;
  public final int WARNING = 7;
  public final int INFO = 5;
  public final int FINE = 3;
  public final int FINEST = 0;
  private int CURENT_LEVEL = FINEST;
  public String ttsdaemon_url = "http://jupiter/cgi-bin/robot/ttsdaemon.pl";
  public String tts_url = "http://jupiter/robot/ttsdaemon/";
  public String promptsUrl;
  private SessionContext context;
  public Integer messageLevel;

  //private MessagesLocalHome messagesHome;
  //ajout de Hung
  private Message_pl messagesHome= new Message_pl();
  //pour chercher BlobId
  //private MESSAGES_RECORDING_BLOBS mrBlob= new MESSAGES_RECORDING_BLOBS();
  //
  //private DialogOutputMessagesLocalHome dialogOutputMessagesHome;
  //ajout de Hung
  private DialogOutputMessages_pl dialogOutputMessagesHome= new DialogOutputMessages_pl();;
  //
  //private AudioLocalHome audioHome;
  //ajout de Hung
  private Audio_pl audioHome= new Audio_pl();
  //
  //private AllGroupsLocalHome allGroupsHome;
  //*
  //ajout de Hung
  private AllGroups_pl allGroupsHome= new AllGroups_pl();
  //*/

  //private AllGroupsLocal allGroups;
  private EJBLog ejbLog;

  //private String language;
  //public String originGroupCode;
  
//    public MessagesDescriptorBeanRemote create(String sessionId) throws CreateException, RemoteException {
//        initLogger(sessionId);
//        setMessageLevel(1);
//        try {
//          ejbLog.info("MessagesDescriptor created : " /* + language*/);
//        }
//        catch (Exception ex) {
//          ejbLog.severe(ex);
//        }
//          return this;
//        return null;
//    }

    public void init(String sessionId) {
        initLogger(sessionId);
        setMessageLevel(1);
        try {
          ejbLog.info("MessagesDescriptor created : " /* + language*/);
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

  private void initLogger(String sessionId) {
    ejbLog = new EJBLog(sessionId);
  }

  /*/ajout de Hung
  private void initMessage_pl() {
    message_pl= new Message_pl();
  }
  /*/
  public void setSessionContext(SessionContext ctx) {
    this.context = ctx;
  }

  /* *************************************************************************
   *                        references with other home
   ****************************************************************************/

  //private MessagesLocal findMessage(Long messageNumber, String languageCode) {
  //ajout de Hung
  private Message_pl findMessage(Long messageNumber, String languageCode) {
    //TODO add originGroupCode !!!
    try {
      //MessagesHome messHome = getMessagesHome();
      ejbLog.info("getting message " + messageNumber + " in language " + languageCode + " " + this.messageLevel);

      //MessagesLocal mess = messagesHome.findByMessageNumber(messageNumber, languageCode, this.messageLevel);
      //ajout de Hung
      //Message_pl messHome = new Message_pl();
      //System.out.println("MessagesDescriptorBean - Commence Message_pl premier");
      //Message_pl mess = null;
      //System.out.println("MessagesDescriptorBean - Initialise Message_pl premier");
      //boolean existe_data=
      Message_pl mess= messagesHome.findByMessageNumber(messageNumber, languageCode, this.messageLevel);
      //if(existe_data){
        //Message_pl mess = message_pl;
        //
        //ejbLog.info(mess.getMessageNumber()+" "+mess.getMessageCode()+" "+mess.toString());
      	//System.out.println("trouve data avec Message_pl premier");
        return mess;
  	  //}
  	  //else {
        //System.out.println("ne pas trouver data Message_pl premier");
        //return null;
      //}
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
      System.out.print("MessagesDescriptorBean - il y a de probleme avec Message_pl.findMessage");
      return null;
    }
  }

    @Override
  public List getMessageList(String originGroupCode, String languageCode) {
    //For BlobEditor
    List vMessages = new ArrayList();
    boolean hasNext = true;

   // try {
      //Collection messagesCollection = messagesHome.findByOriginGroupLanguage(originGroupCode, languageCode);
      // ajout de Hung
      //Message_pl message_pl = new Message_pl();
      Collection messagesCollection = messagesHome.findByOriginGroupLanguage(originGroupCode, languageCode);
      //
      Iterator disIterator = messagesCollection.iterator();

      for (int i = 0; i < messagesCollection.size(); i++) {
        //MessagesLocal m = (MessagesLocal) disIterator.next();
        //ajout de Hung
        Message_pl m = (Message_pl) disIterator.next();
        //
        if (m != null) {
          //trace de Hung
          //System.out.println("MessagesDescriptorBean.getMessageList");
          MessagePrompt mp = addMessage(m, originGroupCode, languageCode);

          if (mp != null) {
            vMessages.add(mp);
          }
        }
      }
    //}
    /*/catch (FinderException fex) {
    //catch (SQLException fex) {
      ejbLog.warning(fex);
    }*/

    return vMessages;
  }

    @Override
  public List getMessageListDO(Long dialogOutputId, String originGroupCode, String languageCode) {
    List vMessages = new ArrayList();

    try {
      //ajout de Hung
      //dialogOutputMessagesHome = new DialogOutputMessages_pl();
      //
      Collection dialogOutputMessages = dialogOutputMessagesHome.findByDOIdOriginGroupCode(dialogOutputId,
          originGroupCode);
      Iterator disIterator = dialogOutputMessages.iterator();

      for (int i = 0; i < dialogOutputMessages.size(); i++) {
        //DialogOutputMessagesLocal dom = (DialogOutputMessagesLocal) disIterator.next();
        //ajout de Hung
        DialogOutputMessages_pl dom = (DialogOutputMessages_pl) disIterator.next();
        //
        //MessagesLocal m = messagesHome.findByPrimaryKey(new MessagesPK(dom.getMessageCode(), dom.getOrigin(),
        //      languageCode));
        //ajout de Hung
        //Message_pl m = new Message_pl();
        //Message_pl m = null;
        //boolean exist_data=m.findByPrimaryKey(new MessagesPK(dom.getMessageCode(), dom.getOrigin(), languageCode));
        Message_pl m= messagesHome.findByPrimaryKey(new MessagesPK(dom.getMessageCode(), dom.getOrigin(), languageCode));
        //Message_pl m = message_pl;
        // m est remplace par message_pl

        //if (exist_data) {
        if (m != null) {
          //System.out.println("MessagesDescriptorBean.getMessageListDO");
          MessagePrompt mp = addMessage(m, originGroupCode, languageCode);

          if (mp != null) {
            vMessages.add(mp);
          }
        }
      }
    }
    catch (Exception fex) {
      ejbLog.warning(fex);
    }

    return vMessages;
  }

    @Override
  public List getMessageListDI(String messageCode, String originGroupCode, String languageCode) {
    List vMessages = new ArrayList();

    //try {
      //MessagesLocal m = messagesHome.findByPrimaryKey(new MessagesPK(messageCode, originGroupCode, languageCode));
      //ajout de Hung
      //Message_pl m = null;
      //boolean exist_data=m.findByPrimaryKey(new MessagesPK(messageCode, originGroupCode, languageCode));
      Message_pl m= messagesHome.findByPrimaryKey(new MessagesPK(messageCode, originGroupCode, languageCode));
      //Message_pl m = message_pl;
      //

      //if (exist_data) {
      if (m != null) {
        //System.out.println("MessagesDescriptorBean.getMessageListDI");
        MessagePrompt mp = addMessage(m, originGroupCode, languageCode);

        if (mp != null) {
          vMessages.add(mp);
        }
      }
    //}
    /* catch (FinderException fex) {
      ejbLog.warning(fex);
    }*/

    return vMessages;
  }

    @Override
  public List getMessageListMessNum(Long messageNumber, String originGroupCode, String languageCode) {
    //odl method, works with older dialogs
    List vMessages = new ArrayList();
    boolean hasNext = true;
    ejbLog.info("first message is " + messageNumber);

    if ((messageNumber != null)&&(messageNumber!=0)) {
      //MessagesLocal m = null;
      //ajout de Hung
      Message_pl m; // = null;//new Message_pl();
      //

      while (hasNext) {
        m = findMessage(messageNumber, languageCode);
        //boolean existe_data = message_pl.findMessage(messageNumber, languageCode);

        if (m!=null) {
          //System.out.println("MessagesDescriptorBean.getMessageListMessNum");
          MessagePrompt mp = addMessage(m, originGroupCode, languageCode);

          if (mp != null) {
            vMessages.add(mp);
          }

          //hasNext = (m.getNextSubMessageNumber() != null);
          hasNext = ((m.getNextSubMessageNumber() != null)&&(m.getNextSubMessageNumber()!=0));

          if (hasNext) {
            messageNumber = m.getNextSubMessageNumber();
            ejbLog.fine("il y a des sous messages " + messageNumber);
          }
        }
        else {
          return new ArrayList();
        }
      }
    }

    return vMessages;
  }

  /*
   * à partir d'un message on assemble la suite de message
   * dans un vecteur. un message est associé a son type.
   * un son, un fichier ou un texte
   */
    @Override
  public String getVariableAudio(String messageText, String groupCode, String language) {
    try {
      ejbLog.fine("get VARIABLE audio : " + messageText + ", language : " + language + ", groupCode : " + groupCode);

      AudioPK key = new AudioPK(messageText, language, groupCode);
      //AudioLocal audio = audioHome.findByPrimaryKey(key);
      //ajout de Hung
      //audioHome= new Audio_pl();
      Audio_pl audio = audioHome.findByPrimaryKey(key);
      //
      String audioFile = audio.getFileWav();

      if (audioFile.lastIndexOf(".") >= 0) {
        audioFile = audioFile.substring(0, audioFile.lastIndexOf("."));
      }

      audioFile += ".wav";

      //if message audio exists return prompt
      ejbLog.finest("\tVariable message : " + messageText);
      audioFile = this.promptsUrl + groupCode + "/" + language + "/" + audioFile;

      return audioFile;
    }
    catch (Exception ex) {
      return null;
    }
  }

    @Override
  public String getVariableAudio(String messageText, Long groupId, String language) {
    try {
      ejbLog.fine("get VARIABLE audio : " + messageText + ", language : " + language + ", groupId : " + groupId);

      return getVariableAudio(messageText, getGroupCode(groupId), language);
    }
    catch (Exception ex) {
      return null;
    }
  }

    @Override
  public Integer getMessageLevel() {
    return messageLevel;
  }

    @Override
  public void setMessageLevel(Integer messageLevel) {
    this.messageLevel = messageLevel;
  }

  //private MessagePrompt addMessage(MessagesLocal m, String originGroupCode, String languageCode) {
  //ajout de Hung
  private MessagePrompt addMessage(Message_pl m, String originGroupCode, String languageCode) {
  //
    //TODO when the originGroupCode of the message is taken into account finding file should be modified
    try {
      ejbLog.fine("message : " + m.getMessageText() + " in " + languageCode);

      if (m.getOriginTable().equals("VARIABLE")) {
        return (new MessagePromptImpl(m.getMessageCode(), STRING_MESSAGE, m.getMessageText(),
          m.getOriginTable(), m.getLanguageCode(), m.getFormat(), m.getOriginGroupCode()));
      }
      else {
        try {
          /*if ((originGroupCode == null) || (originGroupCode == "") || (originGroupCode.length() < 2)) {
            AllGroupsLocal allGroups = allGroupsHome.findByCode(originGroupCode);
            setOriginGroupCode(allGroups.getGroupCode());
          }*/
          String audioFile = m.getFileWav();
          ejbLog.finest("\taudio : " + audioFile);
          //ici, juste confirmer tous les fichier audio terminent par .wav
          if (audioFile.lastIndexOf(".") >= 0) {
            audioFile = audioFile.substring(0, audioFile.lastIndexOf("."));
          }

          audioFile += ".wav";
          //ici, juste confirmer tous les fichier audio terminent par .wav
          //if message audio exists return prompt
          ejbLog.finest("\tPrompt returned for : " + m.getMessageNumber());
          audioFile = this.promptsUrl + originGroupCode + "/" + m.getLanguageCode() + "/" + audioFile;
          ejbLog.fine("message type: " + FILE_MESSAGE);

          return (new MessagePromptImpl(m.getMessageCode(), FILE_MESSAGE, m.getMessageText(),
            audioFile, m.getOriginTable(), m.getLanguageCode(), m.getFormat(), m.getOriginGroupCode()));
        }
        catch (Exception ex) {
          //ejbLog.severe(ex);
          ejbLog.warning("No audio file for : " + m.getMessageNumber() + " " + ex.getMessage());

          return (new MessagePromptImpl(m.getMessageCode(), STRING_MESSAGE, m.getMessageText(),
            m.getOriginTable(), m.getLanguageCode(), m.getFormat(), m.getOriginGroupCode()));
        }
      }
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }

    return null;
  }

    @Override
  public String getPromptsUrl() {
    return promptsUrl;
  }

    @Override
  public void setPromptsUrl(String promptsUrl) {
    this.promptsUrl = promptsUrl;
  }

    @Override
  public String[] getAudio(Long groupId, String languageCode, String wavName) {
    //for RECORDER only
    try {
      String groupCode = getGroupCode(groupId);
      Hashtable audios = new Hashtable();
      //AudioLocal audio = audioHome.findByWavName(groupCode, languageCode, wavName);
      //ajout de Hung
      //audioHome= new Audio_pl();
      Audio_pl audio = audioHome.findByWavName(groupCode, languageCode, wavName);
      //
      String[] infos = new String[2];
      infos[0] = audio.getFileWav();
      infos[1] = audio.getMessageText();

      return infos;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

    @Override
  public List getAudiosList(Long groupId, String languageCode) {
    //for RECORDER only
    try {
      String groupCode = getGroupCode(groupId);
      String selectQuery = "select message_text, file_wav from audio a where a.ORIGIN_GROUP_CODE = '" + groupCode +
        "' and language_code ='" + languageCode +
        "' AND date_creation_wav is null AND TO_DELETE = 'N' ORDER BY FILE_WAV";

      //and (a.A_EFFACER = 'N' OR MOD(TO_NUMBER(SUBSTR(a.FILE_WAV,1,5)),10000) > 5000 )
      //SAGEwM
      /*String selectQuery =
        "SELECT DISTINCT m.MESSAGE_TEXT MESSAGE_TEXT, A.FILE_WAV FILE_WAV FROM MESSAGES m, AUDIO a WHERE m.ORIGIN_GROUP_ID = " +
        originGroupId +
        " AND m.MESSAGE_TEXT = a.MESSAGE_TEXT AND a.GROUP_ID = m.ORIGIN_GROUP_ID and a.language_code ='" +
        languageCode + "' AND a.date_creation_wav is null AND a.LANGUAGE_CODE = m.LANGUAGE_CODE ORDER BY a.FILE_WAV";
      */
      //SELECT MESSAGE_TEXT,FILE_WAV FROM AUDIO a WHERE a.GROUP_ID = 230 AND (a.A_EFFACER = 'N' OR MOD(TO_NUMBER(SUBSTR(a.FILE_WAV,1,5)),10000) > 5000 ) ORDER BY FILE_WAV;
      ejbLog.info("getAudiosList : " + selectQuery);

      String[] infos;
      List returnList = new ArrayList();
      
      Connection con = OracleJDBCConnection.getJDBCConnection();

      PreparedStatement pstmt = con.prepareStatement(selectQuery);

      //pstmt.setString(1, keyName);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        infos = new String[2];
        infos[0] = rs.getString("FILE_WAV");
        infos[1] = rs.getString("MESSAGE_TEXT");
        returnList.add(infos);
      }

      pstmt.close();
      con.close();

      return returnList;
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

    @Override
  public void setDateCreationWav(String messageText, String languageCode, Long groupId, Timestamp ts, String locutor) {
    try {
      String groupCode = getGroupCode(groupId);
      AudioPK key = new AudioPK(messageText, languageCode, groupCode);
      //AudioLocal audio = audioHome.findByPrimaryKey(key);
      //ajout de Hung
      //audioHome = new Audio_pl();
      Audio_pl audio = audioHome.findByPrimaryKey(key);
      //
      ejbLog.fine("::::::::::::::::::::::::: date creation pour  : " + audio.getFileWav());
      audio.setDateCreationWav(ts);
      audio.setLocutor(locutor);
    }
    catch (Exception ex) {
      ejbLog.severe(ex);
    }
  }

    @Override
  public String getTts_url() {
    return tts_url;
  }

    @Override
  public void setTts_url(String tts_url) {
    this.tts_url = tts_url;
  }

    @Override
  public String getTtsdaemon_url() {
    return ttsdaemon_url;
  }

    @Override
  public void setTtsdaemon_url(String ttsdaemon_url) {
    this.ttsdaemon_url = ttsdaemon_url;
  }

    @Override
  public String getGroupCode(Long groupId) {
    try {
      //return allGroupsHome.findByPrimaryKey(groupId).getGroupCode();
      //*
      // ajoute de Hung "ici, il n'y a pas de changement"
      //allGroupsHome= new AllGroups_pl();
      return allGroupsHome.findByPrimaryKey(groupId).getGroupCode();
      //*/
    }
    catch (Exception ex) {
      ejbLog.severe(ex);

      return null;
    }
  }

/* //Enlève l'évenement d'appeler EJB
  private MessagesLocalHome getMessagesLocalHome() throws NamingException {
    final InitialContext context = new InitialContext();

    return (MessagesLocalHome) context.lookup("java:comp/env/ejb/local/Messages");
  }
*/
/*
  private DialogOutputMessagesLocalHome getDialogOutputMessagesLocalHome()
    throws NamingException {
    final InitialContext context = new InitialContext();

    return (DialogOutputMessagesLocalHome) context.lookup("java:comp/env/ejb/local/DialogOutputMessages");
  }
*/
/*
  private AudioLocalHome getAudioLocalHome() throws NamingException {
    final InitialContext context = new InitialContext();

    return (AudioLocalHome) context.lookup("java:comp/env/ejb/local/Audio");
  }
*/
//Il fault mettre en commentaire
/*
  private AllGroupsLocalHome getAllGroupsLocalHome() throws NamingException {
    final InitialContext context = new InitialContext();

    return (AllGroupsLocalHome) context.lookup("java:comp/env/ejb/local/AllGroups");
  }
*/
/*
  public byte[] getMessageBlob(String messageCode, String originGroupCode, String languageCode) {
    try {
      Message_pl m = messagesHome.findByPrimaryKey(new MessagesPK(messageCode, originGroupCode, languageCode));
      return m.getMessageBlob();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public void setMessageBlob(String messageCode, String originGroupCode, String languageCode, byte[] blob) {
    try {
      Message_pl m = messagesHome.findByPrimaryKey(new MessagesPK(messageCode, originGroupCode, languageCode));
      m.setMessageBlob(blob);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
*/
    @Override
  public Long getBlobId(String originGroupCode, String languageCode, String messageCode)
  {
    KEYS_RECORDING_BLOBS krBlob= new KEYS_RECORDING_BLOBS();
    KEYS_RECORDING_BLOBS krB= krBlob.findByPrimarykey(originGroupCode, languageCode, messageCode);
    if(krB.getRecordingBlobId()!=null){
      return krB.getRecordingBlobId();
    }
    return new Long(0);
  }

    @Override
  public String getBlobCode(String blobCode)
  {
    CommonBlobs_pl bc= new CommonBlobs_pl();
    bc= bc.findByPrimaryKey(blobCode);
    if(bc.getBlobCode()!=""){
      return bc.getBlobCode();
    }
    return "";  }
  //*/

    @Override
    public void remove() {
        ejbLog.close();
    }

}
