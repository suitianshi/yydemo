/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class EngineFailedEvent extends EngineEvent {
  private long swigCPtr;

  protected EngineFailedEvent(long cPtr, boolean cMemoryOwn) {
    super(ACMEJNI.EngineFailedEvent_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineFailedEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_EngineFailedEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public static String TypeName() {
    return ACMEJNI.EngineFailedEvent_TypeName();
  }

  public String EventName() {
    return ACMEJNI.EngineFailedEvent_EventName(swigCPtr, this);
  }

  public void setCode(int value) {
    ACMEJNI.EngineFailedEvent_Code_set(swigCPtr, this, value);
  }

  public int getCode() {
    return ACMEJNI.EngineFailedEvent_Code_get(swigCPtr, this);
  }

  public EngineFailedEvent() {
    this(ACMEJNI.new_EngineFailedEvent(), true);
  }

}
