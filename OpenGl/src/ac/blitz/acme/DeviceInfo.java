/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class DeviceInfo {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected DeviceInfo(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DeviceInfo obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_DeviceInfo(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setIndex(int value) {
    ACMEJNI.DeviceInfo_Index_set(swigCPtr, this, value);
  }

  public int getIndex() {
    return ACMEJNI.DeviceInfo_Index_get(swigCPtr, this);
  }

  public void setDisplayName(String value) {
    ACMEJNI.DeviceInfo_DisplayName_set(swigCPtr, this, value);
  }

  public String getDisplayName() {
    return ACMEJNI.DeviceInfo_DisplayName_get(swigCPtr, this);
  }

  public void setGuid(String value) {
    ACMEJNI.DeviceInfo_Guid_set(swigCPtr, this, value);
  }

  public String getGuid() {
    return ACMEJNI.DeviceInfo_Guid_get(swigCPtr, this);
  }

  public DeviceInfo() {
    this(ACMEJNI.new_DeviceInfo(), true);
  }

}
