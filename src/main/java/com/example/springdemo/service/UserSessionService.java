package com.example.springdemo.service;

import com.example.springdemo.model.UserSession;
import com.example.springdemo.repository.userSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class UserSessionService {

    @Autowired
    private userSessionRepository UserSessionRepository;

    @Transactional
    public List<UserSession> findAll() {
        return UserSessionRepository.findAll();
    }

    @Transactional
    public UserSession findByUserId(int id) {
        return UserSessionRepository.findByUserId(id);
    }

    @Transactional
    public List<UserSession> findByUsername(String username) {
        return UserSessionRepository.findByUsername(username);
    }

    @Transactional
    public UserSession findByUserIdAndSessionId(String user_id, String session_id) {
        return UserSessionRepository.findByUserIdAndSessionId(user_id,session_id);
    }

    @Transactional
    public List<UserSession> findAllWithLoginTimeBefore(Timestamp login_time) {
        return UserSessionRepository.findAllWithLoginTimeBefore(login_time);
    }

    @Transactional
    public int deleteByUserId(int id) {
        return UserSessionRepository.deleteByUserId(id);
    }

    @Transactional
    public int insert(UserSession user_session) {
        return UserSessionRepository.insert(user_session);
    }


    @Transactional
    public int updateLogoutTime(String id, Timestamp logout_time) {
        return UserSessionRepository.updateLogoutTime(id,logout_time);
    }
}
