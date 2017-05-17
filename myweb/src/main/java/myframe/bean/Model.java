package myframe.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuyufeng on 2017/5/17.
 */
public class Model {
    private String returnType="forward"; //forward redirect
    private String returnPath;

    private Map<String, Object> map = new HashMap<String, Object>();

    public void addAttribute(String var1, Object var2) {
        map.put(var1, var2);
    }

    public Map<String, Object> getModelMap() {
        return map;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnPath() {
        return returnPath;
    }

    public void setReturnPath(String returnPath) {
        this.returnPath = returnPath;
    }
}
