package com.coco.kings.Dao;

import com.coco.kings.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 康森
 * @date 2020/3/31 20 : 31 : 23
 * @description 用户查询接口
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
