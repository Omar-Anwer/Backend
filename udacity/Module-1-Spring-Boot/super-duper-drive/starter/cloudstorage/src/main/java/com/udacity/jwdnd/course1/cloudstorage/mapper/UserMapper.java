package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE userid = #{id}")
    User findById(Integer id);

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User findByName(String userName);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password},  #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(User user);

    @Delete("DELETE FROM USERS WHERE userid = #{id}")
    void delete(Integer id);
}   