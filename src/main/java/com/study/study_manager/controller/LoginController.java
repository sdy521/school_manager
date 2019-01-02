package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.dto.UpdatePasswordParam;
import com.study.study_manager.entity.User;
import com.study.study_manager.service.InitPasswordService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/leftmodal")
    @ResponseBody
    public Result leftmodal(@RequestParam Integer id){
        User user = new User();
        user.setId(id);
        user = initPasswordService.selectOne(user);
        return new JSONResult(user);
    }
    @RequestMapping("/leftupdate")
    @ResponseBody
    public Result leftupdate(@RequestParam Integer id,@RequestParam String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        initPasswordService.updateSelective(user);
        return new Result(0,"更新成功");
    }
}
