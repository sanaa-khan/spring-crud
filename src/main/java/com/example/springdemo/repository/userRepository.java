package com.example.springdemo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.example.springdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userRepository {
    @Select("SELECT * FROM USER")
    public List <User> findAll();

    @Select("SELECT * FROM user WHERE user_id = #{id}")
    public User findById(int id);

    @Select("SELECT * FROM user WHERE username = #{username}")
    public User findByUsername(String username);

    @Select("SELECT password FROM user WHERE username = #{username}")
    public String getPasswordByUsername(String username);

    @Delete("DELETE FROM user WHERE user_id = #{id}")
    public int deleteById(int id);

    @Insert("INSERT INTO user(username, password) " +
            " VALUES (#{username}, #{password})")
    public int insert(User user);

    @Update("Update user set username=#{username}, " +
            " password=#{password} where user_id=#{user_id}")
    public int update(User user);
}
