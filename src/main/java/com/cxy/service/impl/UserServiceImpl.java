package com.cxy.service.impl;

import com.cxy.controller.vo.UserVO;
import com.cxy.dao.UserMapper;
import com.cxy.dao.UserPasswordMapper;
import com.cxy.pojo.User;
import com.cxy.pojo.UserPassword;
import com.cxy.service.UserService;
import com.cxy.service.model.UserModel;
import com.cxy.utils.BusinessException;
import com.cxy.utils.EmBussinessError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/3/8 10:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Override
    public UserModel getUserById(Integer id) {
        User user  = userMapper.selectByPrimaryKey(id);

        //通过用户id获取对应的用户加密的密码信息
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        return convertFromDateObject(user,userPassword);

    }

    //传来两个对象 User  UserPassword
    private UserModel convertFromDateObject(User user, UserPassword userPassword){
        if (user==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user,userModel);
        if(userPassword != null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }

    @Override
    public void register(UserModel userModel) throws BusinessException {
        if(userModel==null){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(StringUtils.isEmpty(userModel.getName())
                ||userModel.getGender()==null
                ||userModel.getAge()==null
                ||StringUtils.isEmpty(userModel.getTelphone())){
            throw new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        User user = convertFromModel(userModel);
        userMapper.insertSelective(user);

        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
        return;

        //实现Model转  dataOBject的方法

    }
    //专门转密码
    private UserPassword convertPasswordFromModel(UserModel userModel){
        if (userModel==null){
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncrptPassword());
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }
    private User convertFromModel(UserModel userModel){
        if (userModel==null){
            return null;
        }
        User user = new User();
        //表示吧userModel转为user,省略了密码
        BeanUtils.copyProperties(userModel,user);
        return user;
    }


}