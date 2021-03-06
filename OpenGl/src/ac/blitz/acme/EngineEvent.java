/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class EngineEvent {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected EngineEvent(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_EngineEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static String TypeName() {
    return ACMEJNI.EngineEvent_TypeName();
  }

  public String EventName() {
    return ACMEJNI.EngineEvent_EventName(swigCPtr, this);
  }

  public void setEngineIdentify(int value) {
    ACMEJNI.EngineEvent_EngineIdentify_set(swigCPtr, this, value);
  }

  public int getEngineIdentify() {
    return ACMEJNI.EngineEvent_EngineIdentify_get(swigCPtr, this);
  }

}
