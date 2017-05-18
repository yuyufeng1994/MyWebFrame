package myframe.commons;

import myframe.annotation.MyParam;
import myframe.convert.ConvertFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/18.
 */
public class LoadUtil {
    /**
     * 参数装载
     * @param reqMap
     * @param method
     * @param prarms
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void loadParams(Map<String, String[]> reqMap, Method method, Object[] prarms) throws InstantiationException, IllegalAccessException {
        Class<?>[] classes = method.getParameterTypes();
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < classes.length; i++) {
            //获取方法上参数的注解
            MyParam myParam = (MyParam) annotations[i][0];
            Class<?> clz = classes[i];
//            System.out.println(clz + " " + myParam.value());
            Object cob = null;
            //如果是基本类型
            if (ConvertFactory.isJavaClass(clz)) {
                for (String s : reqMap.keySet()) {
                    //找到对应的参数
                    if (myParam.value().equals(s)) {
                        try {
                            cob = ConvertFactory.convert(clz, reqMap.get(s)[0]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //填充数据
                    prarms[i] = cob;
                }
            } else if (clz == List.class) {
                //如果是集合类
            } else {
                //如果是自定义实体
                Object paramObject = clz.newInstance();
                getMyObject(reqMap, clz, paramObject);
                prarms[i] = paramObject;
            }


        }
    }
    //自定义对象装载
    public static void getMyObject(Map<String, String[]> reqMap, Class<?> clz, Object paramObject) {
        List<Method> methods = GetterSetterUtil.setter(clz);
        for (Method method1 : methods) {
            Class<?> method1PrarmType = method1.getParameterTypes()[0];
            String pName = GetterSetterUtil.getPropertyName(method1);
            for (String s : reqMap.keySet()) {
                //判断对象里面有getParam方法
                if (pName.equals(s)) {
                    //对象里面的参数也是基本类型。如果自定义对象里面还有自定义对象，则此处需要做递归处理
                    if (ConvertFactory.isJavaClass(method1PrarmType)) {
                        try {
                            method1.invoke(paramObject, ConvertFactory.convert(method1PrarmType, reqMap.get(s)[0]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
