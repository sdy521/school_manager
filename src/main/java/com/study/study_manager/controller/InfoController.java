package com.study.study_manager.controller;

import com.study.study_manager.service.InfoService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class InfoController extends BaseController{

    @Resource
    private InfoService infoService;
}
