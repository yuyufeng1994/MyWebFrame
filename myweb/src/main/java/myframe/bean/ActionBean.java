package myframe.bean;

import java.lang.reflect.Method;

/**
 * Created by yuyufeng on 2017/5/5.
 */
public class ActionBean {
    private Class<?> clazz;
    private Method method;

    public ActionBean(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
