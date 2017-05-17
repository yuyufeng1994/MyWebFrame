package action;

import myframe.annotation.MyAction;

/**
 * Created by yuyufeng on 2017/5/5.
 */
@MyAction(value="/root")
public class TestAction {
    @MyAction(value = "/test")
    public String testMyMethod() {
        System.out.println("TestAction.testMyMethod");
        return "index";
    }

    @MyAction(value = "/test2")
    public String testMyMethod2() {
        System.out.println("TestAction.testMyMethod2");
        return "test";
    }

    public static void main(String[] args) {
        TestAction ta = new TestAction();

    }
}
