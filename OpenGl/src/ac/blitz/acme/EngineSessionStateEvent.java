/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class EngineSessionStateEvent extends EngineEvent {
  private long swigCPtr;

  protected EngineSessionStateEvent(long cPtr, boolean cMemoryOwn) {
    super(ACMEJNI.EngineSessionStateEvent_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineSessionStateEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_EngineSessionStateEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public static String TypeName() {
    return ACMEJNI.EngineSessionStateEvent_TypeName();
  }

  public String EventName() {
    return ACMEJNI.EngineSessionStateEvent_EventName(swigCPtr, this);
  }

  public void setSessionId(String value) {
    ACMEJNI.EngineSessionStateEvent_SessionId_set(swigCPtr, this, value);
  }

  public String getSessionId() {
    return ACMEJNI.EngineSessionStateEvent_SessionId_get(swigCPtr, this);
  }

  public void setOldState(SessionState value) {
    ACMEJNI.EngineSessionStateEvent_OldState_set(swigCPtr, this, value.swigValue());
  }

  public SessionState getOldState() {
    return SessionState.swigToEnum(ACMEJNI.EngineSessionStateEvent_OldState_get(swigCPtr, this));
  }

  public void setNewState(SessionState value) {
    ACMEJNI.EngineSessionStateEvent_NewState_set(swigCPtr, this, value.swigValue());
  }

  public SessionState getNewState() {
    return SessionState.swigToEnum(ACMEJNI.EngineSessionStateEvent_NewState_get(swigCPtr, this));
  }

  public EngineSessionStateEvent() {
    this(ACMEJNI.new_EngineSessionStateEvent(), true);
  }

}
