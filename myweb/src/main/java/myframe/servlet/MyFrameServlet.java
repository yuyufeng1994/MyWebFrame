package myframe.servlet;

import myframe.bean.ActionBean;
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
        //获取要执行的动作
        String uri = req.getRequestURI();
        String actionName = uri.substring(0, uri.indexOf(".do"));
        System.out.println("actionName:" + actionName);

        //do

        ActionBean actionBean = MyFrameInit.getActionBean(actionName);
        try {
            actionBean.getMethod().invoke(MyFrameInit.getObject(actionBean.getClazz()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //return
        String fileName = "index";
        System.out.println("/WEB-INF/jsp/"+fileName+".jsp");
        req.getRequestDispatcher("/WEB-INF/jsp/"+fileName+".jsp").forward(req,resp);

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
