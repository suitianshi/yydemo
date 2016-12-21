/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class DeviceInfoArray {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected DeviceInfoArray(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DeviceInfoArray obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_DeviceInfoArray(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public DeviceInfoArray() {
    this(ACMEJNI.new_DeviceInfoArray__SWIG_0(), true);
  }

  public DeviceInfoArray(long n) {
    this(ACMEJNI.new_DeviceInfoArray__SWIG_1(n), true);
  }

  public long size() {
    return ACMEJNI.DeviceInfoArray_size(swigCPtr, this);
  }

  public long capacity() {
    return ACMEJNI.DeviceInfoArray_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    ACMEJNI.DeviceInfoArray_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return ACMEJNI.DeviceInfoArray_isEmpty(swigCPtr, this);
  }

  public void clear() {
    ACMEJNI.DeviceInfoArray_clear(swigCPtr, this);
  }

  public void add(DeviceInfo x) {
    ACMEJNI.DeviceInfoArray_add(swigCPtr, this, DeviceInfo.getCPtr(x), x);
  }

  public DeviceInfo get(int i) {
    return new DeviceInfo(ACMEJNI.DeviceInfoArray_get(swigCPtr, this, i), false);
  }

  public void set(int i, DeviceInfo val) {
    ACMEJNI.DeviceInfoArray_set(swigCPtr, this, i, DeviceInfo.getCPtr(val), val);
  }

}
