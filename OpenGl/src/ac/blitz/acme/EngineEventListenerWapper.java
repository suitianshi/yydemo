package ac.blitz.acme;

import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Created by liubao on 16/11/12.
 *
 */

public class EngineEventListenerWapper extends EngineEventListener implements Handler.Callback {
    private EngineEventHandler eventHandler;
    private Handler msgHandler;
    private HashMap<String,Constructor> constructors = new HashMap<>();
    public EngineEventListenerWapper(EngineEventHandler handler) {
        this.eventHandler = handler;
        this.msgHandler = new Handler(this);
    }

    @Override
    public void OnEvent(EngineEvent ev) {
        try {
            String typeName = "ac.blitz.acme."+ev.EventName();
            Constructor constructor = null;
            constructor = constructors.get(typeName);
            if(constructor == null){
                Class<?> eventClass = Class.forName(typeName);
                constructor = eventClass.getDeclaredConstructor(long.class,boolean.class);
                constructors.put(typeName,constructor);
            }
            Object realEvent = constructor.newInstance(EngineEvent.getCPtr(ev),false);
            Message message = new Message();
            message.obj = realEvent;
            ev.swigCMemOwn = false;
            msgHandler.sendMessage(message);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public boolean handleMessage(Message message) {
        if(eventHandler!=null) {
            eventHandler.onMediaEngineEvent((EngineEvent)message.obj);
        }
        return true;
    }
}
