package com.marticles.swagger2demo.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.marticles.swagger2demo.model.Person;
import com.marticles.swagger2demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/persons")
@Api(value = "/persons", tags = "用户模块")
public class PersonController {

    @Autowired
    PersonService personService;

    @ApiOperation(value = "根据person信息查询person", notes = "用生成xml与mapper通过方法名映射，查询数据")
    @GetMapping("/gen")
    public Person getPersonByName(@RequestParam("name") String name) {
        Person person = new Person();
        BeanUtils.copyProperties(personService.getPersonByName(name), person);
        return person;
    }

    @ApiOperation(value = "根据person信息查询person", notes = "直接用注解的方式写sql，查询数据")
    @GetMapping("/custom")
    public ResponseEntity<String> getCustomPersonByName(@RequestParam("name") String name) {
        Person person = new Person();
        BeanUtils.copyProperties(personService.getCustomPersonByName(name),
                person);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/persons/gen?name={name}", String.class, map);

        return responseEntity;
    }

    @ApiOperation(value = "分页查询persons", notes = "pageHelper使用分页")
    @GetMapping("")
    public PageInfo<Person> getPersonsInPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<Person> personPageInfo = personService.getPersonsInPage(pageNum, pageSize);
        return personPageInfo;
    }

    @ApiOperation(value = "新增Person", notes = "用生成xml与mapper通过方法名映射，新增数据")
    @PostMapping(value = "/gen", produces = "application/json;charset=UTF-8")
    public String addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @ApiOperation(value = "修改Person", notes = "直接用注解的方式写sql，修改数据")
    @PutMapping(value = "/custom", produces = "application/json;charset=UTF-8")
    public String updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @ApiOperation(value = "删除Person", notes = "用生成xml与mapper通过方法名映射，删除数据")
    @DeleteMapping(value = "/gen", produces = "application/json;charset=UTF-8")
    public String deletePerson(@RequestParam("name") String name) {
        Person person = personService.getCustomPersonByName(name);
        return personService.deletePerson(person);
    }


}
