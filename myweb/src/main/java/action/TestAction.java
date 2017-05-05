package action;

import myframe.annotation.MyAction;
import myframe.annotation.MyMethod;

/**
 * Created by yuyufeng on 2017/5/5.
 */
@MyAction(value="/rot")
public class TestAction {
    @MyAction(value = "/test")
    public String testMyMethod() {
        System.out.println("TestAction.testMyMethod");
        return "test";
    }

    @MyAction(value = "/test2")
    public String testMyMethod2() {
        System.out.println("TestAction.testMyMethod2");
        return "test";
    }
}
