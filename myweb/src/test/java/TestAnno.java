import myframe.annotation.MyAction;
import myframe.commons.ClassUtil;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by yuyufeng on 2017/5/5.
 */
public class TestAnno {

    @Test
    public void test() {
        Set<Class<?>> classes = ClassUtil.getClasses("action");
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
            if (aClass.isAnnotationPresent(MyAction.class)) {
                MyAction mac = aClass.getAnnotation(MyAction.class);
                String classAnnoValue = mac.value().trim();

                for (Method method : aClass.getMethods()) {
                    if (method.isAnnotationPresent(MyAction.class)) {
                        MyAction mam = method.getAnnotation(MyAction.class);
                        String methodAnnoValue = mam.value().trim();
                        System.out.println(classAnnoValue + methodAnnoValue);
                    }
                }
            }

        }
    }


}
