package com.longifly.api.service;

import java.util.List;

import com.longifly.api.entity.User;

/**
 * @author WT
 * @date 2020/08/25
 */
public interface UserService {

    /**
     * findById
     * 
     * @param id
     * @return
     */
    public User findById(int id);

    /**
     * findAll
     * 
     * @return
     */
    public List<User> findAll(int page, int pageSize);

    /**
     * count
     * 
     * @return
     */
    public int count();

}
