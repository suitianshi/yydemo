/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class ReceiveVideoStatistics {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected ReceiveVideoStatistics(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ReceiveVideoStatistics obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_ReceiveVideoStatistics(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setRtt(int value) {
    ACMEJNI.ReceiveVideoStatistics_Rtt_set(swigCPtr, this, value);
  }

  public int getRtt() {
    return ACMEJNI.ReceiveVideoStatistics_Rtt_get(swigCPtr, this);
  }

  public void setFrameRate(int value) {
    ACMEJNI.ReceiveVideoStatistics_FrameRate_set(swigCPtr, this, value);
  }

  public int getFrameRate() {
    return ACMEJNI.ReceiveVideoStatistics_FrameRate_get(swigCPtr, this);
  }

  public void setReceiveBitRate(int value) {
    ACMEJNI.ReceiveVideoStatistics_ReceiveBitRate_set(swigCPtr, this, value);
  }

  public int getReceiveBitRate() {
    return ACMEJNI.ReceiveVideoStatistics_ReceiveBitRate_get(swigCPtr, this);
  }

  public void setEstimatedBandwidth(int value) {
    ACMEJNI.ReceiveVideoStatistics_EstimatedBandwidth_set(swigCPtr, this, value);
  }

  public int getEstimatedBandwidth() {
    return ACMEJNI.ReceiveVideoStatistics_EstimatedBandwidth_get(swigCPtr, this);
  }

  public void setPacketLoss(int value) {
    ACMEJNI.ReceiveVideoStatistics_PacketLoss_set(swigCPtr, this, value);
  }

  public int getPacketLoss() {
    return ACMEJNI.ReceiveVideoStatistics_PacketLoss_get(swigCPtr, this);
  }

  public ReceiveVideoStatistics() {
    this(ACMEJNI.new_ReceiveVideoStatistics(), true);
  }

}