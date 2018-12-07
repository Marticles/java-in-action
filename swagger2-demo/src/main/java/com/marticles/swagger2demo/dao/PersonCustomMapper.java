package com.marticles.swagger2demo.dao;


import com.marticles.swagger2demo.model.Person;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;
import java.util.Date;

public interface PersonCustomMapper {

    @Select("select id, name, birth from person where name = #{name}")
    Person selectPersonByName(String name);

    @Update(("update person set name = #{name}, birth = #{birth} where id= #{id}" ))
    int updatePerson(@Param("name") String name,@Param("birth") Date birth,@Param("id") int id);
}
