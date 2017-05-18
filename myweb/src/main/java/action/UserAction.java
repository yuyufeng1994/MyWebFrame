package action;

import entity.User;
import myframe.annotation.MyAction;
import myframe.annotation.MyParam;
import myframe.bean.Model;
import myframe.commons.GetterSetterUtil;
import myframe.commons.LoadUtil;
import myframe.convert.ConvertFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/17.
 */
@MyAction("/user")
public class UserAction {
    private static List<User> users = new ArrayList<User>();

    static {
        for (long i = 0; i < 8; i++) {
            users.add(new User(i, "用户" + i));
        }
    }

    @MyAction("/list")
    public Model userList() {
        Model model = new Model();
        model.addAttribute("users", users);
        model.setReturnPath("user/list");
        return model;
    }

    @MyAction("/add")
    public Model userAdd(@MyParam("user") User user, @MyParam("note") String note) {
        Model model = new Model();
        model.addAttribute("message", "增加用户成功！");
        users.add(user);
        System.out.println("note:"+note);
        model.addAttribute("users", users);
        model.setReturnPath("user/list");
        return model;
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = UserAction.class;
        Object object = clazz.newInstance();

        Method method = clazz.getMethod("userAdd", User.class, String.class);

        Annotation[][] annotations = method.getParameterAnnotations();
        Class<?>[] classes = method.getParameterTypes();

        Map<String, String[]> reqMap = new HashMap<String, String[]>();
        reqMap.put("userId", new String[]{"123"});
        reqMap.put("userName", new String[]{"用户1"});
        reqMap.put("note", new String[]{"这是备注信息"});


        Object[] prarms = new Object[classes.length];

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
                LoadUtil.getMyObject(reqMap, clz, paramObject);
                prarms[i] = paramObject;
            }


        }


        method.invoke(object, prarms);
    }


}
