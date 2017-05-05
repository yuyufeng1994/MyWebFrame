package myframe.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuyufeng on 2017/5/5.
 */
public class MyFrameServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("MainServlet.initConfig");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要执行的动作
        String uri = req.getRequestURI();
        String actionName = uri.substring(1, uri.indexOf(".do"));
        System.out.println("actionName:" + actionName);

        //do



        //return
        String fileName = "";
        req.getRequestDispatcher("/WEB-INF/jsp/"+fileName).forward(req,resp);

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
