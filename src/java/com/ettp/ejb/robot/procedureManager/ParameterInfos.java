package com.ettp.ejb.robot.procedureManager;

import java.io.Serializable;


public interface ParameterInfos extends Serializable {
  public Long getParameterId();

  public Class getParameterClass();

  public String getParameterValue();

  public String getParameterLabel();
}
