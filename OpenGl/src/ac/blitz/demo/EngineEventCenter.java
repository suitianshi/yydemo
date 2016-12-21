package ac.blitz.demo;

import ac.blitz.acme.EngineEvent;
import ac.blitz.acme.EngineEventHandler;

import java.util.ArrayList;

/**
 * Created by liubao on 16/11/12.
 *
 */

public class EngineEventCenter implements EngineEventHandler {
    private ArrayList<EngineEventHandler> handlers = new ArrayList<EngineEventHandler>();
    @Override
    public void onMediaEngineEvent(EngineEvent engineEvent) {
       for (EngineEventHandler handler:handlers){
           handler.onMediaEngineEvent(engineEvent);
       }
    }
    public void registerHandler(EngineEventHandler handler) {
        if(handlers.contains(handler))return;
        handlers.add(handler);
    }
    public void unRegisterHandler(EngineEventHandler handler){
        handlers.remove(handler);
    }

    private EngineEventCenter(){}

    private static EngineEventCenter instance = new EngineEventCenter();
    public static EngineEventCenter getInstance(){
        return instance;
    }
}
