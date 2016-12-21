/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public class StringArray {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected StringArray(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(StringArray obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_StringArray(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public StringArray() {
    this(ACMEJNI.new_StringArray__SWIG_0(), true);
  }

  public StringArray(long n) {
    this(ACMEJNI.new_StringArray__SWIG_1(n), true);
  }

  public long size() {
    return ACMEJNI.StringArray_size(swigCPtr, this);
  }

  public long capacity() {
    return ACMEJNI.StringArray_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    ACMEJNI.StringArray_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return ACMEJNI.StringArray_isEmpty(swigCPtr, this);
  }

  public void clear() {
    ACMEJNI.StringArray_clear(swigCPtr, this);
  }

  public void add(String x) {
    ACMEJNI.StringArray_add(swigCPtr, this, x);
  }

  public String get(int i) {
    return ACMEJNI.StringArray_get(swigCPtr, this, i);
  }

  public void set(int i, String val) {
    ACMEJNI.StringArray_set(swigCPtr, this, i, val);
  }

}