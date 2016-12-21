/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.6
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package ac.blitz.acme;

import android.content.Context;
import android.media.AudioManager;

public class MediaEngine {
  private static EngineEventListenerWapper wapper;
  private long swigCPtr;
  protected boolean swigCMemOwn;
  private static int oldMode = 0;
  private static Context currentContext = null;

  protected MediaEngine(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(MediaEngine obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        ACMEJNI.delete_MediaEngine(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static int Init(EngineEventHandler handler) {
    wapper = new EngineEventListenerWapper(handler);
    return ACMEJNI.MediaEngine_Init__SWIG_0(EngineEventListener.getCPtr(wapper), wapper);
  }

  public static int Init() {
    return ACMEJNI.MediaEngine_Init__SWIG_1();
  }

  public static void UnInit() {
    ACMEJNI.MediaEngine_UnInit();
  }

  public static void SupportedSDKs(UInt8Array sdks) {
    ACMEJNI.MediaEngine_SupportedSDKs(UInt8Array.getCPtr(sdks), sdks);
  }

  public static int UseSdk(short sdk) {
    return ACMEJNI.MediaEngine_UseSdk(sdk);
  }

  public static short CurrentSDK() {
    return ACMEJNI.MediaEngine_CurrentSDK();
  }

  public static MediaEngine SDK() {
    long cPtr = ACMEJNI.MediaEngine_SDK();
    return (cPtr == 0) ? null : new MediaEngine(cPtr, false);
  }

  public static int InitializeSdk(EngineConfig config) {
    return ACMEJNI.MediaEngine_InitializeSdk__SWIG_0(EngineConfig.getCPtr(config), config);
  }

  public static int InitializeSdk(EngineConfig config, Context context) {

    currentContext = context;
    AudioManager mAudioManager = (AudioManager) context
            .getSystemService(Context.AUDIO_SERVICE);
    oldMode = mAudioManager.getMode();
    mAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
    return ACMEJNI.MediaEngine_InitializeSdk__SWIG_1(EngineConfig.getCPtr(config), config, context);
  }

  public static void TerminateSdk() {
    if(currentContext!=null)
    {
      AudioManager mAudioManager = (AudioManager)currentContext
              .getSystemService(Context.AUDIO_SERVICE);
      mAudioManager.setMode(oldMode);
      currentContext = null;
    }
    ACMEJNI.MediaEngine_TerminateSdk();
  }

  public static int Identify() {
    return ACMEJNI.MediaEngine_Identify();
  }

  public int GetIdentify() {
    return ACMEJNI.MediaEngine_GetIdentify(swigCPtr, this);
  }

  public void SetIdentify(int identify) {
    ACMEJNI.MediaEngine_SetIdentify(swigCPtr, this, identify);
  }

  public void SetVideoRotation(VideoRotation rotate) {
    ACMEJNI.MediaEngine_SetVideoRotation(swigCPtr, this, rotate.swigValue());
  }

  public void GetCameraDevices(DeviceInfoArray info) {
    ACMEJNI.MediaEngine_GetCameraDevices(swigCPtr, this, DeviceInfoArray.getCPtr(info), info);
  }

  public void GetPlayoutDevices(DeviceInfoArray info) {
    ACMEJNI.MediaEngine_GetPlayoutDevices(swigCPtr, this, DeviceInfoArray.getCPtr(info), info);
  }

  public void GetRecordDevices(DeviceInfoArray info) {
    ACMEJNI.MediaEngine_GetRecordDevices(swigCPtr, this, DeviceInfoArray.getCPtr(info), info);
  }

  public int SetCameraDevice(int index) {
    return ACMEJNI.MediaEngine_SetCameraDevice(swigCPtr, this, index);
  }

  public int SetRecordDevice(int index) {
    return ACMEJNI.MediaEngine_SetRecordDevice(swigCPtr, this, index);
  }

  public int SetPlayoutDevice(int index) {
    return ACMEJNI.MediaEngine_SetPlayoutDevice(swigCPtr, this, index);
  }

  public int StartPlayoutTest(String wavFilePath) {
    return ACMEJNI.MediaEngine_StartPlayoutTest(swigCPtr, this, wavFilePath);
  }

  public void StopPlayoutTest() {
    ACMEJNI.MediaEngine_StopPlayoutTest(swigCPtr, this);
  }

  public int StartMicTest() {
    return ACMEJNI.MediaEngine_StartMicTest(swigCPtr, this);
  }

  public void StopMicTest() {
    ACMEJNI.MediaEngine_StopMicTest(swigCPtr, this);
  }

  public void Action(String action) {
    ACMEJNI.MediaEngine_Action(swigCPtr, this, action);
  }

  public int OpenMic() {
    return ACMEJNI.MediaEngine_OpenMic(swigCPtr, this);
  }

  public int CloseMic() {
    return ACMEJNI.MediaEngine_CloseMic(swigCPtr, this);
  }

  public boolean IsMicOpened() {
    return ACMEJNI.MediaEngine_IsMicOpened(swigCPtr, this);
  }

  public void MuteAudio(boolean mute) {
    ACMEJNI.MediaEngine_MuteAudio(swigCPtr, this, mute);
  }

  public boolean IsAudioMuted() {
    return ACMEJNI.MediaEngine_IsAudioMuted(swigCPtr, this);
  }

  public int SetRecordVolume(int volume) {
    return ACMEJNI.MediaEngine_SetRecordVolume(swigCPtr, this, volume);
  }

  public int GetRecordVolume() {
    return ACMEJNI.MediaEngine_GetRecordVolume(swigCPtr, this);
  }

  public int SetPlayoutVolume(int volume) {
    return ACMEJNI.MediaEngine_SetPlayoutVolume(swigCPtr, this, volume);
  }

  public int GetPlayoutVolume() {
    return ACMEJNI.MediaEngine_GetPlayoutVolume(swigCPtr, this);
  }

  public void SetLoudSpeakerStatus(boolean enable) {
    ACMEJNI.MediaEngine_SetLoudSpeakerStatus(swigCPtr, this, enable);
  }

  public int OpenCamera() {
    return ACMEJNI.MediaEngine_OpenCamera(swigCPtr, this);
  }

  public int CloseCamera() {
    return ACMEJNI.MediaEngine_CloseCamera(swigCPtr, this);
  }

  public int StartPreview(android.view.SurfaceView view) {
    return ACMEJNI.MediaEngine_StartPreview(swigCPtr, this, view);
  }

  public int StopPreview() {
    return ACMEJNI.MediaEngine_StopPreview(swigCPtr, this);
  }

  public int StartRender(String userId, android.view.SurfaceView view) {
    return ACMEJNI.MediaEngine_StartRender(swigCPtr, this, userId, view);
  }

  public void StopRender(String userId) {
    ACMEJNI.MediaEngine_StopRender(swigCPtr, this, userId);
  }

  public void PauseVideo(boolean pause) {
    ACMEJNI.MediaEngine_PauseVideo(swigCPtr, this, pause);
  }

  public boolean IsVideoPaused() {
    return ACMEJNI.MediaEngine_IsVideoPaused(swigCPtr, this);
  }

  public SessionState SessionState() {
    return SessionState.swigToEnum(ACMEJNI.MediaEngine_SessionState(swigCPtr, this));
  }

  public int EnterSession(String sessionId) {
    return ACMEJNI.MediaEngine_EnterSession(swigCPtr, this, sessionId);
  }

  public void LeaveSession() {
    ACMEJNI.MediaEngine_LeaveSession(swigCPtr, this);
  }

  public int StartRecordAudio(String pathToSave) {
    return ACMEJNI.MediaEngine_StartRecordAudio(swigCPtr, this, pathToSave);
  }

  public void StopRecordAudio() {
    ACMEJNI.MediaEngine_StopRecordAudio(swigCPtr, this);
  }

  public int GetMediaInfo(MediaInfo info) {
    return ACMEJNI.MediaEngine_GetMediaInfo(swigCPtr, this, MediaInfo.getCPtr(info), info);
  }

  public int GetMediaStatistics(MediaStatistic statistics) {
    return ACMEJNI.MediaEngine_GetMediaStatistics(swigCPtr, this, MediaStatistic.getCPtr(statistics), statistics);
  }

  public int GetReceiveVideoStatistics(ReceiveVideoStatistics statistics) {
    return ACMEJNI.MediaEngine_GetReceiveVideoStatistics(swigCPtr, this, ReceiveVideoStatistics.getCPtr(statistics), statistics);
  }

  public int GetReceiveAudioStatistics(ReceiveAudioStatistics statistics) {
    return ACMEJNI.MediaEngine_GetReceiveAudioStatistics(swigCPtr, this, ReceiveAudioStatistics.getCPtr(statistics), statistics);
  }

}
