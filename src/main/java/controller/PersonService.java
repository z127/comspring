package controller;

import domain.Person;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {  // 模拟内存数据库，准备数据
    // 声明一个容器
    private static Map<Integer, Person> map = new HashMap<Integer, Person>();
    // 初始化 id
    private static Integer id = 1;


    // 利用静态块初始化数据
    static {
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setId(i);
            p.setName("Charie"+i);
            p.setAge(10+i);
            map.put(i,p);
        }
    }

    // 获取人员列表
    public List<Person> findAll(){
        // 将 map 对象转换为 list 结合
        return new ArrayList(map.values());
    }


    // 新增人员信息
    public void insert(Person p) {
        id++;
        p.setId(id);
        map.put(id, p);
    }
}
