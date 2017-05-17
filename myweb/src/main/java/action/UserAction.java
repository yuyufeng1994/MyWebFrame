package action;

import entity.User;
import myframe.annotation.MyAction;
import myframe.bean.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("users",users);
        model.setReturnPath("user/list");
        return model;
    }

    @MyAction("/add")
    public Model userAdd(User user,String note) {
        Model model = new Model();
        model.addAttribute("message","增加用户成功！");
        model.addAttribute("users",users);
        model.setReturnPath("user/list");
        System.out.println("UserAction.userAdd");
        return model;
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = UserAction.class;
        Object object = clazz.newInstance();

        Method method = clazz.getMethod("userAdd",User.class,String.class);
        for (Class<?> param : method.getParameterTypes()) {
            System.out.println(param+" "+param.getName());

        }
//        Object[] prarms = new Object[]{new User(),"aa"};
//        method.invoke(object,prarms);
    }
}
