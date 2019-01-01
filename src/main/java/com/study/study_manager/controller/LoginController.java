package com.study.study_manager.controller;

import com.study.study_manager.core.Result;
import com.study.study_manager.dto.UpdatePasswordParam;
import com.study.study_manager.entity.User;
import com.study.study_manager.service.InitPasswordService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class LoginController {

    @Resource
    private InitPasswordService initPasswordService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout (HttpServletRequest request, HttpServletResponse response) {
   /*     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //设置为离线状态
            userDetailsService.updateOnline(((User)auth.getPrincipal()).getId(), 4 ,null);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }*/
        return "ok";
    }

    @RequestMapping("/initPassword")
    public String initPassword(){
        return "/password";
    }

    @RequestMapping("/updateInitPassword")
    @ResponseBody
    public Result updateInitPassword(@RequestBody UpdatePasswordParam param){
        User user = new User();
        user.setName(param.getUsername());
        user = initPasswordService.selectOne(user);
        if(user!=null){
            BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
            boolean password = bcry.matches(param.getOldPassword(),user.getPassword());
            if(password){
                if(param.getNewPassword().equals(param.getRepeatNewPassword())){
                    user.setPassword(BCrypt.hashpw(param.getNewPassword(),BCrypt.gensalt()));
                    initPasswordService.updateSelective(user);
                    return new Result(0,"修改成功");
                }else {
                    return new Result(405,"两次密码输入不一致");
                }
            }else{
                return new Result(406,"旧密码输入错误");
            }
        }else {
            return new Result(407,"用户不存在");
        }
    }
}
