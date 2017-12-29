package com.ettp.plprocedure;

import java.io.Serializable;


public class ProcedureParametersPK implements Serializable {
  public Long parameterRank;
  public Long procedureId;

  public ProcedureParametersPK() {
  }

  public ProcedureParametersPK(Long procedureId, Long parameterRank) {
    this.procedureId = procedureId;
    this.parameterRank = parameterRank;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ProcedureParametersPK) {
      final ProcedureParametersPK otherProcedureParametersPK = (ProcedureParametersPK) other;

      // The following assignment statement is auto-maintained and may be overwritten.
      boolean areEqual = (otherProcedureParametersPK.procedureId.equals(procedureId) &&
        otherProcedureParametersPK.parameterRank.equals(parameterRank));

      return areEqual;
    }

    return false;
  }

  @Override
  public int hashCode() {
    // Add custom hashCode() impl here
    return super.hashCode();
  }
}
