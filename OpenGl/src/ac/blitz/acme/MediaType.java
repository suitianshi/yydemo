/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

public final class MediaType {
  public final static MediaType MediaType_Audio = new MediaType("MediaType_Audio");
  public final static MediaType MediaType_Video = new MediaType("MediaType_Video");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static MediaType swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + MediaType.class + " with value " + swigValue);
  }

  private MediaType(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private MediaType(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private MediaType(String swigName, MediaType swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static MediaType[] swigValues = { MediaType_Audio, MediaType_Video };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

