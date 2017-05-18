package myframe.commons;

import java.lang.reflect.Method;

/**
 * Created by yuyufeng on 2017/5/18.
 */
public interface MethodFilter {
    boolean filter(Method method);
}
