package com.ettp.plmessage;
import com.ettp.OracleJDBCConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DialogOutputMessages_pl
{
  //Les parametres
  private Long DIALOG_ID= null;
  private String ORIGIN= null;
  private String MESSAGE_CODE= null;
  private Long DIALOG_OUTPUT_ID= null;
  private Long MESSAGE_ORDER= null;

  public DialogOutputMessages_pl()
  {
    this.DIALOG_ID= null;
    this.ORIGIN= null;
    this.MESSAGE_CODE= null;
    this.DIALOG_OUTPUT_ID= null;
    this.MESSAGE_ORDER= null;

    //
    //System.out.println("DialogOutputMessages_pl - Fini initialisation DialogOutputMessages_pl");
  }

  //****Partie de remplacement de DialogOutputMessagesLocalHome***//
  /*
  DialogOutputMessagesLocal findByPrimaryKey(DialogOutputMessagesPK primaryKey)
    throws FinderException;

  Collection findAll() throws FinderException;
  */
  public Collection findByDOIdOriginGroupCode(Long dialogOutputId, String originGroupCode)
  {
      List ls = new LinkedList();
      Collection collection= (Collection) ls;
      //DialogOutputMessages_pl dom_pl=new DialogOutputMessages_pl();
    try{
      Connection con = OracleJDBCConnection.getJDBCConnection();
      Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
      String sql= "select * from robot.DIALOG_OUTPUT_MESSAGES where DIALOG_OUTPUT_ID = '"+dialogOutputId.toString()+
                  "' AND ORIGIN = '"+originGroupCode+"'";
      ResultSet resultat = statement.executeQuery(sql);
      while(resultat.next()){
        DialogOutputMessages_pl dom_pl=new DialogOutputMessages_pl();

        dom_pl.DIALOG_ID= new Long(resultat.getLong("DIALOG_ID"));
        dom_pl.ORIGIN= resultat.getString("ORIGIN");
        dom_pl.MESSAGE_CODE= resultat.getString("MESSAGE_CODE");
        dom_pl.DIALOG_OUTPUT_ID= new Long(resultat.getLong("DIALOG_OUTPUT_ID"));
        dom_pl.MESSAGE_ORDER= new Long(resultat.getLong("MESSAGE_ORDER"));
        //ajouter dans un Collection
        collection.add(dom_pl);
      }
      statement.close();
      con.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return collection;
  }
  //*/


  //****Partie de remplacement de DialogOutputMessagesLocal***//
  public Long getDialogId(){return this.DIALOG_ID;}
  //public void setDialogId(Long dialogId){this.dialog_id= dialogId;}

  public String getOrigin(){return this.ORIGIN;}
  //public void setOrigin(String origin){this.origin= origin;}

  public String getMessageCode(){return this.MESSAGE_CODE;}
  //public void setMessageCode(String messageCode){this.message_code= messageCode;}

  public Long getDialogOutputId(){return this.DIALOG_OUTPUT_ID;}
 // public void setDialogOutputId(Long doId){this.do_id= doId;}

  public Long getMessageOrder(){return this.MESSAGE_ORDER;}
  //public void setMessageOrder(Long messageOrder){this.message_order= messageOrder;}
}