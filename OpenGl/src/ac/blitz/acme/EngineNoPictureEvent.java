/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class EngineNoPictureEvent extends EngineEvent {
  private long swigCPtr;

  protected EngineNoPictureEvent(long cPtr, boolean cMemoryOwn) {
    super(ACMEJNI.EngineNoPictureEvent_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(EngineNoPictureEvent obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_EngineNoPictureEvent(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public static String TypeName() {
    return ACMEJNI.EngineNoPictureEvent_TypeName();
  }

  public String EventName() {
    return ACMEJNI.EngineNoPictureEvent_EventName(swigCPtr, this);
  }

  public void setUserId(String value) {
    ACMEJNI.EngineNoPictureEvent_UserId_set(swigCPtr, this, value);
  }

  public String getUserId() {
    return ACMEJNI.EngineNoPictureEvent_UserId_get(swigCPtr, this);
  }

  public EngineNoPictureEvent() {
    this(ACMEJNI.new_EngineNoPictureEvent(), true);
  }

}
