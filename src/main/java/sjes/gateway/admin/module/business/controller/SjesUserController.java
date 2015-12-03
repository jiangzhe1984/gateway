package sjes.gateway.admin.module.business.controller;

import client.api.user.UserApiClient;
import client.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sjes.gateway.admin.utils.DoubleEditor;
import sjes.gateway.admin.utils.FloatEditor;
import sjes.gateway.admin.utils.IntegerEditor;
import sjes.gateway.admin.utils.LongEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangzhe on 15-12-1.
 */
@RestController
@RequestMapping("/sjes_users")
public class SjesUserController {
    @Autowired
    UserApiClient userApiClient;

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public Object list(int page, int limit,String username,String mobile,String email,String cardNum){
        return userApiClient.pageGetUserList(username, mobile, email, cardNum, new PageRequest(page, limit));
    }

    /**
     * 　根据登录名取得用户信息
     *
     * @param username 　用户名
     * @return 用户对象
     */
    @RequestMapping(value = "/viewUser",method = RequestMethod.POST)
    public User viewUser(String username){
        return userApiClient.findByUsername(username);
    }

    /**
     * 更新用户信息
     *
     * @return　用户对象
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Object updateUser(Long id,String username,String mobile,String email,String cardNum){
        return userApiClient.updateMobileAndEmailAndCardNum(id, username, mobile, email, cardNum);
    }

}
