package com.coco.kings.web.admin;

import com.coco.kings.Service.UserService;
import com.coco.kings.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.Attribute;
import javax.servlet.http.HttpSession;

/**
 * @author 康森
 * @date 2020/3/31 20 : 37 : 23
 * @description 登录页请求
 */
@Controller
@RequestMapping("/kings")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username , @RequestParam String password ,
                        HttpSession session, RedirectAttributes attributes
                        ) throws InterruptedException {
        User user = userService.checkUser(username, password);
        if (user == null){
            Thread.sleep(2000);
            attributes.addFlashAttribute("message","用户名密码错误哦");
            return "redirect:/kings";
        }
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            Thread.sleep(3500);
            return "admin/admin2";
        }else {
            return "redirect:/kings";
        }

    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/kings";
    }
}
