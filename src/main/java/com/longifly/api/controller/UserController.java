package com.longifly.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.longifly.api.entity.User;
import com.longifly.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author WT
 * @date 2020/08/25
 */

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "展示", notes = "show")
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Cacheable(value = "users", keyGenerator = "keyGenerator")
    public User show(@ApiParam(name = "id", value = "编号") @PathVariable int id) {
        //
        System.out.println("=====================");
        //
        return userService.findById(id);
    }

    @ApiOperation(value = "列表", notes = "list")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Cacheable(value = "users", keyGenerator = "keyGenerator")
    public Map<String, Object> list(@ApiParam(name = "pageNum", value = "页码") @RequestParam int pageNum,
        @ApiParam(name = "pageSize", value = "页长") @RequestParam int pageSize) {
        //
        Map<String, Object> result = new HashMap<String, Object>();
        //
        result.put("items", userService.findAll((pageNum <= 0 ? 0 : pageNum - 1) * pageSize, pageSize));
        result.put("total", userService.count());
        //
        return result;
    }

    @ApiOperation(value = "新增", notes = "create")
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create(@RequestBody @Valid User user, BindingResult bindingResult) {
        //
        if (bindingResult.hasErrors()) {
            //
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                //
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            }
        }
        return "ok";
    }

}
