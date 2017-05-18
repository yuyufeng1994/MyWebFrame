package myframe.commons;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过反射方法处理GetterSetter
 * Created by yuyufeng on 2017/5/18.
 */
public class GetterSetterUtil {
    public static List<Method> getMethod(Class<?> cls, MethodFilter filter) {
        List<Method> list = new ArrayList<Method>();
        for (Method method : cls.getMethods()) {
            if (filter.filter(method)) {
                list.add(method);
            }
        }
        return list;
    }

    public static List<Method> getter(Class<?> cls) {
        return getMethod(cls, new MethodFilter() {
            @Override
            public boolean filter(Method method) {

                return method.getName().matches("^get[A-Z]\\w*$") && method.getParameterTypes().length == 0;
            }
        });
    }

    public static List<Method> getter(Object obj) {
        return getter(obj.getClass());
    }

    public static List<Method> setter(Class<?> cls) {

        return getMethod(cls, new MethodFilter() {

            @Override
            public boolean filter(Method method) {

                return method.getName().matches("^set[A-Z]\\w*$") && method.getParameterTypes().length == 1;
            }
        });
    }

    public static List<Method> setter(Object obj) {
        return setter(obj.getClass());
    }

    public static String getPropertyName(Method getterOrSetterMethod) {
        return toFirstLowerCase(getterOrSetterMethod.getName().replaceAll("^[gs]et([A-Z]\\w*)$", "$1"));
    }

    public static String toFirstUpperCase(String target) {
        StringBuilder sb = new StringBuilder(target);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String toFirstLowerCase(String target) {
        StringBuilder sb = new StringBuilder(target);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }
}
