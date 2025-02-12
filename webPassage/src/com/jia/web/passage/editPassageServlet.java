package com.jia.web.passage;

import com.jia.bean.passage;
import com.jia.service.Service;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
public class editPassageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
          String cid=request.getParameter("cid");
          passage passage=new passage();
          Map<String,String[]> map=request.getParameterMap();
        try {
            BeanUtils.populate(passage,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        passage.setState(1);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String data=dateFormat.format(new Date());
        passage.setData(data);
        String type=request.getParameter("type");
        passage.setType(type);
        Service service=new Service();
        Boolean flg=service.eitPassage(cid,passage);
        System.out.print(flg);
        if(flg){
            //修改成功
            String ty= URLEncoder.encode(type,"utf-8");
            response.sendRedirect(request.getContextPath()+"/getPassageList?type="+ty);
        }else{
            //修改失败
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
