package com.yjj.eventbusex.exeutor;

import com.yjj.eventbusex.common.ELog;
import com.yjj.eventbusex.meta.IEventData;
import com.yjj.eventbusex.meta.IEventEntry;
import com.yjj.eventbusex.meta.ThreadType;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by jianjunyjj on 16/5/28.
 */
public class JopScheduler implements IJopScheduler {

    private String TAG = "JopScheduler";


    private final Map<Class, IEventEntry> mSubscriberMethods;
    private ExecutorFactory mExecutorFactory;

    public JopScheduler(Map<Class, IEventEntry> subscriberMethods) {
        mExecutorFactory= new DefaultExecutorFactory();
        this.mSubscriberMethods = subscriberMethods;
    }

    public void execute(final Object event) {
        if (event == null) {
            return;
        }

        if (mSubscriberMethods.containsKey(event.getClass())) {
            IEventEntry tmpEntry = mSubscriberMethods.get(event.getClass());
            int ii;
            int len;
            List<IEventData> list = tmpEntry.getEventDatas();
            if (list == null) {
                return;
            }
            len = list.size();
            IEventData eventData = null;
            for (ii = 0; ii < len; ii++) {
                eventData = list.get(ii);
                ELog.v(TAG, eventData.toString());
                Executor tmpExecutor = mExecutorFactory.get(eventData.getThreadType());
                final IEventData finalEventData = eventData;
                tmpExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            finalEventData.getMethod().invoke(finalEventData.getObject(), event);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

        }

    }

}
