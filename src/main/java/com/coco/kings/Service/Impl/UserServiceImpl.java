package com.coco.kings.Service.Impl;

import com.coco.kings.Dao.UserRepository;
import com.coco.kings.Service.UserService;
import com.coco.kings.Util.MD5Util;
import com.coco.kings.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 康森
 * @date 2020/3/31 20 : 30 : 03
 * @description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Util.code(password));
        return user;
    }
}
