package myframe.init;

import myframe.annotation.MyAction;
import myframe.bean.ActionBean;
import myframe.commons.ClassUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/5/5.
 */
public class MyFrameInit {
    private static Map<String, ActionBean> actionMap;
    private static Map<Class<?>, Object> objectMap;
    public static Object getObject(Class<?> clazz) {
        return objectMap.get(clazz);
    }
    public static ActionBean getActionBean(String key) {
        return actionMap.get(key);
    }

    public static void init() throws IllegalAccessException, InstantiationException {
        actionMap = new HashMap<String, ActionBean>();
        objectMap = new HashMap<Class<?>, Object>();
        Set<Class<?>> classes = ClassUtil.getClasses("action");
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
            if (aClass.isAnnotationPresent(MyAction.class)) {
                MyAction mac = aClass.getAnnotation(MyAction.class);
                String classAnnoValue = mac.value().trim();
                objectMap.put(aClass, aClass.newInstance());

                for (Method method : aClass.getMethods()) {
                    if (method.isAnnotationPresent(MyAction.class)) {
                        MyAction mam = method.getAnnotation(MyAction.class);
                        String methodAnnoValue = mam.value().trim();
                        String actionName = classAnnoValue + methodAnnoValue;
                        actionMap.put(actionName, new ActionBean(aClass, method));
                    }
                }
            }
        }

        System.out.println("MyFrameInit.init==================初始化完毕");
    }


}
