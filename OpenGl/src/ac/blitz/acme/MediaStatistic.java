/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class MediaStatistic {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected MediaStatistic(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MediaStatistic obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_MediaStatistic(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setIntItems(KVIArray value) {
    ACMEJNI.MediaStatistic_IntItems_set(swigCPtr, this, KVIArray.getCPtr(value), value);
  }

  public KVIArray getIntItems() {
    long cPtr = ACMEJNI.MediaStatistic_IntItems_get(swigCPtr, this);
    return (cPtr == 0) ? null : new KVIArray(cPtr, false);
  }

  public void setStringItems(KVSArray value) {
    ACMEJNI.MediaStatistic_StringItems_set(swigCPtr, this, KVSArray.getCPtr(value), value);
  }

  public KVSArray getStringItems() {
    long cPtr = ACMEJNI.MediaStatistic_StringItems_get(swigCPtr, this);
    return (cPtr == 0) ? null : new KVSArray(cPtr, false);
  }

  public void setChannelItems(ChannelStatisticArray value) {
    ACMEJNI.MediaStatistic_ChannelItems_set(swigCPtr, this, ChannelStatisticArray.getCPtr(value), value);
  }

  public ChannelStatisticArray getChannelItems() {
    long cPtr = ACMEJNI.MediaStatistic_ChannelItems_get(swigCPtr, this);
    return (cPtr == 0) ? null : new ChannelStatisticArray(cPtr, false);
  }

  public MediaStatistic() {
    this(ACMEJNI.new_MediaStatistic(), true);
  }

}
