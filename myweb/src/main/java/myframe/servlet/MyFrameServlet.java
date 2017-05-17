package myframe.servlet;

import entity.User;
import myframe.bean.ActionBean;
import myframe.bean.Model;
import myframe.init.MyFrameInit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yuyufeng on 2017/5/5.
 */


public class MyFrameServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            MyFrameInit.init();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取要执行的动作
        String uri = req.getRequestURI();
        String actionName = uri.substring(0, uri.indexOf(".do"));
        System.out.println("访问ActionMethod:" + actionName);

        //do
        String fileName = "";
        ActionBean actionBean = MyFrameInit.getActionBean(actionName);
        Object result = null;
        try {
            Class returnType = actionBean.getMethod().getReturnType();

            //执行方法的参数填充
            Class<?>[] paramTypes = actionBean.getMethod().getParameterTypes();
            Object[] prarms = loadParameter(paramTypes,req);

            result = actionBean.getMethod().invoke(MyFrameInit.getObject(actionBean.getClazz()), prarms);

            //判断返回的类型，然后执行不同的动作
            if (returnType == Model.class) {
                Model modelResult = (Model) result;
                fileName = modelResult.getReturnPath();
                //设置属性
                for (String s : modelResult.getModelMap().keySet()) {
                    req.setAttribute(s, modelResult.getModelMap().get(s));
                }
            } else if (returnType == String.class) {
                fileName = (String) result;
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        System.out.println("执行完毕，跳转路径" + "/WEB-INF/jsp/" + fileName + ".jsp");  ///WEB-INF/jsp/这些暂时写死，到时候可以到配置中获取
        req.getRequestDispatcher("/WEB-INF/jsp/" + fileName + ".jsp").forward(req, resp);

    }

    private Object[] loadParameter(Class<?>[] paramTypes, HttpServletRequest request) {
        Object[] prarms = new Object[paramTypes.length];
//        Object[] prarms = new Object[]{new User(), "aa"};
        for (Class<?> paramType : paramTypes) {
            System.out.println(paramType);
        }


        return prarms;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
