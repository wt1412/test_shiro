package com.longifly.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longifly.api.dao.UserDao;
import com.longifly.api.entity.User;
import com.longifly.api.service.UserService;

/**
 * @author WT
 * @date 2020/08/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll(int page, int pageSize) {
        // TODO Auto-generated method stub
         return userDao.findAll(page, pageSize);
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
         return userDao.count();
    }

}
