package com.longifly.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.longifly.api.entity.User;

/**
 * @author WT
 * @date 2020/08/25
 */
@Mapper
public interface UserDao {

    /**
     * findById
     * 
     * @param id
     * @return
     */
    public User findById(@Param("id") int id);

    /**
     * findAll
     * 
     * @return
     */
    public List<User> findAll(@Param("page") int page, @Param("pageSize") int pageSize);

    /**
     * count
     * 
     * @return
     */
    public int count();

}
