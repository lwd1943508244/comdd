package com.example.dong.service.Impl;

import com.example.dong.dao.UserDao;
import com.example.dong.message.LoginRespondMessage;
import com.example.dong.message.RegisterRespondMessage;
import com.example.dong.net.NetConnection;
import com.example.dong.pojo.User;
import com.example.dong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(NetConnection netCon, String username, String password) {
        //先从表中查询数据看是存在相同用户名
        List<User> users = userDao.selectList(null);
        RegisterRespondMessage msg = new RegisterRespondMessage();
        for (User user: users) {
            if(user.getUsername().equals(username)){
                msg.flag = false;
                netCon.sendMsg(msg);
                return;
            }
        }
        System.out.println("------------------------22222222222");
        userDao.insert(new User(username,password));
        msg.flag = true;
        netCon.sendMsg(msg);
    }

    @Override
    public void login(NetConnection netCon,String username, String password) {
        //判断用户名密码是否正确
        List<User> users = userDao.selectList(null);
        LoginRespondMessage msg = new LoginRespondMessage();
        for (User user:users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                msg.flag = true;
                netCon.sendMsg(msg);
            }
        }
    }
}
