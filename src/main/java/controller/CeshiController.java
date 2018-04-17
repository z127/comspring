package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by 维C果糖 on 2017/1/24.
 */
@Controller
public class CeshiController  {

    @RequestMapping("/ceshi")
    protected String goCeshi(HttpServletRequest request) {
        System.out.println(request.getRequestURI());  // 获取Controller的名称，即地址
        System.out.println("我调用了");
        return "index";  // 逻辑名
    }
}