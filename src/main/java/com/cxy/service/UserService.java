package com.cxy.service;

import com.cxy.pojo.User;
import com.cxy.service.model.UserModel;
import com.cxy.utils.BusinessException;

public interface UserService {
    //通过用户id获取用户
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
}
