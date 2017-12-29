package com.ettp.ejb.robot.sessionManager;

//ajout classe de Hung
import com.ettp.plsession.RecordingBlobs_pl;
//
//import com.ettp.ejb.robot.cmp.recordingblobs.RecordingBlobsLocal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


//public class BlobWriter implements Runnable {
//ajout de Hung
public class BlobWriter implements Runnable {
//
  //private RecordingBlobsLocal recordingBlob;
  //ajout de Hung
  private RecordingBlobs_pl recordingBlob= new RecordingBlobs_pl();
  //
  private byte[] bytes;
  private SessionManagerBean smb;

  //public BlobWriter(SessionManagerBean smb, RecordingBlobsLocal rb, byte[] bytes) {
  //ajout de Hung
  public BlobWriter(SessionManagerBean smb, RecordingBlobs_pl rb, byte[] bytes) {
  //
    this.smb = smb;
    this.recordingBlob = rb;
    this.bytes = bytes;
  }

  public void run() {
  //ajout de Hung
  //public void run_write() {
  //
    try {
      smb.ejbLog.info("BLOBWRITER : setting blob data in BlobWriter");
      recordingBlob.setRecording(bytes);
      recordingBlob.setRecordingSize(new Double(bytes.length));
      smb.ejbLog.info("BLOBWRITER : blob data set in BlobWriter");
    }
    catch (Exception e) {
      smb.ejbLog.info("BLOBWRITER : error setting blob date in BlobWriter");
      smb.ejbLog.severe(e);

      try {
        smb.saveFile(recordingBlob.getRecordingBlobId().toString() + ".wav", bytes);
      }
      catch (Exception ex) {
        smb.ejbLog.severe(ex);
        smb.saveFile(System.currentTimeMillis() + ".wav", bytes);
      }
    }
  }
}
