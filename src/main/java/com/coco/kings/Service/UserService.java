package com.coco.kings.Service;

import com.coco.kings.bean.User;

/**
 * @author 康森
 * @date 2020/3/31 20 : 28 : 15
 * @description 用户业务接口
 */
public interface UserService {

    User checkUser(String username, String password);
}
