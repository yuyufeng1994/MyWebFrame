package action;

import entity.User;
import myframe.annotation.MyAction;
import myframe.bean.Model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyufeng on 2017/5/17.
 */
@MyAction("/user")
public class UserAction {
    @MyAction("/list")
    public Model userList() {
        Model model = new Model();

        List<User> users = new ArrayList<User>();
        for (long i = 0; i < 8; i++) {
            users.add(new User(i, "用户" + i));
        }
        model.addAttribute("users",users);
        model.setReturnPath("user/list");
        return model;
    }


    public static void main(String[] args) {
        Class clazz = UserAction.class;
        for (Method method : clazz.getMethods()) {
            Class returnType = method.getReturnType();
            System.out.println(returnType == Model.class);

        }
    }
}
