package myframe.annotation;

import java.lang.annotation.*;

/**
 * Created by yuyufeng on 2017/5/5.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAction {
    String value() default  "";
}
