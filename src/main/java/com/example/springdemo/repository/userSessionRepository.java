package com.example.springdemo.repository;

import com.example.springdemo.model.UserSession;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Repository
public interface userSessionRepository {
    @Select("SELECT * FROM USER_SESSION")
    public List<UserSession> findAll();

    @Select("SELECT * FROM user_session WHERE user_id = #{id}")
    public UserSession findByUserId(int id);

    @Select("SELECT * FROM user_session WHERE username = #{username}")
    public List<UserSession> findByUsername(String username);

    @Select("SELECT * FROM user_session WHERE user_id = #{user_id} AND session_id = #{session_id}")
    public UserSession findByUserIdAndSessionId(String user_id, String session_id);

    @Select("SELECT * FROM user_session WHERE login_time <= #{login_time}")
    public List<UserSession> findAllWithLoginTimeBefore(Timestamp login_time);

    @Delete("DELETE FROM user_session WHERE user_id = #{id}")
    public int deleteByUserId(int id);

    @Insert("INSERT INTO user_session(id, username, login_time, login_ip, session_id, logout_time, user_id) " +
            " VALUES (#{id}, #{username}, #{login_time}, #{login_ip}, #{session_id}, #{logout_time}, #{user_id})")
    public int insert(UserSession user_session);

    @Update("UPDATE user_session set logout_time=#{logout_time} " +
            " where id=#{id}")
    public int updateLogoutTime(String id, Timestamp logout_time);

}
