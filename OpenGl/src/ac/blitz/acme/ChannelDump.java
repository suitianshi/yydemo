/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class ChannelDump {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected ChannelDump(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ChannelDump obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_ChannelDump(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setUserId(long value) {
    ACMEJNI.ChannelDump_UserId_set(swigCPtr, this, value);
  }

  public long getUserId() {
    return ACMEJNI.ChannelDump_UserId_get(swigCPtr, this);
  }

  public void setDump(UInt8Array value) {
    ACMEJNI.ChannelDump_Dump_set(swigCPtr, this, UInt8Array.getCPtr(value), value);
  }

  public UInt8Array getDump() {
    long cPtr = ACMEJNI.ChannelDump_Dump_get(swigCPtr, this);
    return (cPtr == 0) ? null : new UInt8Array(cPtr, false);
  }

  public ChannelDump() {
    this(ACMEJNI.new_ChannelDump(), true);
  }

}
