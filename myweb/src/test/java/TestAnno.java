import myframe.annotation.MyAction;
import myframe.bean.ActionBean;
import myframe.commons.ClassUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/5/5.
 */
public class TestAnno {

    private static Map<String, ActionBean> map = new HashMap<String, ActionBean>();
    private static Map<Class<?>, Object> objectMap = new HashMap<Class<?>, Object>();

    @Test
    public void test() throws IllegalAccessException, InstantiationException {

        Set<Class<?>> classes = ClassUtil.getClasses("action");
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
            if (aClass.isAnnotationPresent(MyAction.class)) {
                MyAction mac = aClass.getAnnotation(MyAction.class);
                String classAnnoValue = mac.value().trim();
                objectMap.put(aClass,aClass.newInstance());

                for (Method method : aClass.getMethods()) {
                    if (method.isAnnotationPresent(MyAction.class)) {
                        MyAction mam = method.getAnnotation(MyAction.class);
                        String methodAnnoValue = mam.value().trim();
                        String actionName = classAnnoValue + methodAnnoValue;

                        map.put(actionName,new ActionBean(aClass,method));
                    }
                }
            }
        }


        ActionBean ab = map.get("/rot/test2");
        System.out.println(ab);
        try {

            String result= (String) ab.getMethod().invoke(objectMap.get(ab.getClazz()));
            System.out.println(result);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
