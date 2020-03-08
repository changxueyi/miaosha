package com.cxy.controller;

import com.alibaba.druid.util.StringUtils;
import com.cxy.controller.vo.UserVO;
import com.cxy.pojo.User;
import com.cxy.service.UserService;
import com.cxy.service.model.UserModel;
import com.cxy.utils.BusinessException;
import com.cxy.utils.CommonReturnType;
import com.cxy.utils.EmBussinessError;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Random;

import static com.cxy.controller.BaseController.CONTENT_TYPE_FORMED;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author changxueyi
 * @Date 2020/3/8 9:57
 */
@Controller("user")
@RequestMapping("/user")
// 允许ajax跨域及session共享
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;
    //单例模式，作为springBean包装 ，本质是procy 代理
    @Autowired
    private HttpServletRequest httpServletRequest;

    @ResponseBody
    @RequestMapping("/getUser/{id}")
    public CommonReturnType getUserById(@PathVariable("id") Integer id) throws Exception {
        /*
        一般用户的信息不能全部显示给前端页面，所以在建义ModelVo来显示要传给前端的数据
         */
        UserModel userModel = userService.getUserById(id);
        UserVO userVo = convertFromUserModel(userModel);

        if (userModel == null) {
            //抛出一个自定义的异常
            throw new BusinessException(EmBussinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(userVo);
    }

    public UserVO convertFromUserModel(UserModel userModel) {
        UserVO userVo = new UserVO();
        if (userModel == null) {
            return null;
        } else {
            BeanUtils.copyProperties(userModel, userVo);
        }
        return userVo;
    }


    //用户获取OTP短信接口
    @RequestMapping(value = "/getotp", method ={RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        //按照一定的规格则生成OTP验证码,本项目使用随机数生成
        Random random = new Random();
        //此时随机数取值 [0，99999）
        int randomInt = random.nextInt(99999);
        //这个随机数再加10000，生成一个OTP 的code
        randomInt += 10000;
        //String.valueOf(int i) : 将 int 变量 i 转换成字符串
        //　用法如下:
        //　　int i = 10;
        //　　String str = String.valueOf(i);
        //　　这时候 str 就会是 "10"
        String otpCode = String.valueOf(randomInt);

        //将OTP短信验证码同对应用户的手机号进行绑定,使用httpSession的方式绑定他的手机号与OTP code
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        //将OTP短信验证码通过短信发送给用户，省略

        System.out.println("telphone:"+telphone+""+"&otpCode="+otpCode);


        return CommonReturnType.create(null);

    }

    //注册
    @RequestMapping(value = "/register", method ={RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public  CommonReturnType register(@RequestParam("telephone") String telephone,
                                  @RequestParam("otpCode") String otpCode,
                                  @RequestParam("name") String name,
                                  @RequestParam("originalPassword") String originalPassword,
                                  @RequestParam("gender")Integer gender,
                                  @RequestParam("age") Integer age) throws BusinessException {

        // 比较手机号与验证码是否匹配,定义一个验证码
       // String randomCodeFromRedis = "";
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telephone);
        if (StringUtils.equals(otpCode,inSessionOtpCode)){
            throw  new BusinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不合理");

        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setTelphone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(MD5Encoder.encode(originalPassword.getBytes()));

        userService.register(userModel);
        return CommonReturnType.create(null);


    }



}