package com.example.dong.service;

import com.example.dong.net.NetConnection;


public interface UserService {
    public void register(NetConnection netCon, String username, String password);
    public void login(NetConnection netCon,String username,String password);
}
