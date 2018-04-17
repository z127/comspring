package controller;

import domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {
    @Resource
    PersonService ps;

    @RequestMapping(value = "/person/all")
    public String findAll(Map<String,Object> model){     // 声明 model 用来传递数据
        List<Person> personList = ps.findAll();
        model.put("personList",personList);              // 通过这一步，JSP 页面就可以访问 personList
        return "/person/jPersonList";                    // 跳转到 jPersonList 页面
    }

    @RequestMapping("/person/toCreatePersonInfo")
    public String toCteatePersonInfo(){  // 跳转新增页面
        return "/person/jPersonCreate";
    }

    @RequestMapping("/person/updatePersonList")
    public String updatePersonList(Person p){
        ps.insert(p);                                // 调用 Service 层方法，插入数据
        return "redirect:/person/all.action";        // 转向人员列表 action
    }

    @RequestMapping("/person/myupdatePersonList")
    public String myupdatePersonList(String name,Integer age){
        Person p = new Person();
        p.setName(name);
        p.setAge(age);
        ps.insert(p);                                // 调用 Service 层方法，插入数据
        return "redirect:/person/all.action";        // 转向人员列表 action
    }

}
