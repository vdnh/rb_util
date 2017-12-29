package com.ettp.ejb.robot.procedureManager;

public class ParameterInfosImpl implements ParameterInfos {
  Long parameterId = null;
  Class parameterClass = null;
  String parameterLabel = null;
  String parameterValue = null;

  public ParameterInfosImpl(Long parameterId, String className, String parameterLabel, String parameterValue) {
    this.parameterId = parameterId;

    try {
      this.parameterClass = Class.forName("java.lang.String");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    this.parameterLabel = parameterLabel;
    this.parameterValue = parameterValue;
  }

  @Override
  public Long getParameterId() {
    return this.parameterId;
  }

  @Override
  public Class getParameterClass() {
    return this.parameterClass;
  }

  @Override
  public String getParameterValue() {
    return this.parameterValue;
  }

  @Override
  public String getParameterLabel() {
    return this.parameterLabel;
  }
}
