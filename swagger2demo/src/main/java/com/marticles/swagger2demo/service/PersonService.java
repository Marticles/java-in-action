package com.marticles.swagger2demo.service;

import Utils.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marticles.swagger2demo.dao.PersonCustomMapper;
import com.marticles.swagger2demo.dao.PersonMapper;
import com.marticles.swagger2demo.model.Person;
import com.marticles.swagger2demo.model.PersonExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonMapper personMapper;
    @Autowired
    PersonCustomMapper personCustomMapper;

    // 基于XML的查询
    public Person getPersonByName(String name){
        /**
         * Example 类包含一个内部静态类 Criteria，而 Criteria 包含一个用 anded 组合的 where 子句列表
         * Example 类还包含了一个 Criteria 对象的 List，这些 Criteria 对象之间的是用 ored 连接的
         * 通过使用 Criteria 的不同组合，可以构造近乎无限的  where条件子句
         *
         * 文档地址：http://www.mybatis.org/generator/generatedobjects/exampleClassUsage.html
         */
        PersonExample personExample = new PersonExample();
        // 创建 Criteria 对象可以通过 Example 类中的 createCriteria() 或者 or() 方法
        // PersonExample.Criteria criteria = personExample.createCriteria();
        PersonExample.Criteria criteria = personExample.or();
        criteria.andNameEqualTo(name);
        List<Person> persons = personMapper.selectByExample(personExample);
        return persons.get(0);

    }

    // 基于注解的查询
    public Person getCustomPersonByName(String name) {
        return personCustomMapper.selectPersonByName(name);
    }


    // 分页查询Person
    public PageInfo<Person> getPersonsInPage(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Person> persons = personMapper.selectByExample(null);
        return new PageInfo<>(persons);
    }

    public String addPerson(Person person){
        try {
            personMapper.insert(person);
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.StringToJson(500,e.getMessage());
        }
        return JsonUtil.StringToJson(201,"Added person successful");
    }

    public String updatePerson(Person person){
        try {
            personCustomMapper.updatePerson(person.getName(),person.getBirth(),person.getId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.StringToJson(500,e.getMessage());
        }
        return JsonUtil.StringToJson(200,"Updated person successful");
    }

    public String deletePerson(Person person){
        try {
            personMapper.deleteByPrimaryKey(person.getId());
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.StringToJson(500,e.getMessage());
        }
        return JsonUtil.StringToJson(200,"Deleted person successful");
    }

}
