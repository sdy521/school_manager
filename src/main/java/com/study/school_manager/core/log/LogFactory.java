package com.study.school_manager.core.log;

import com.study.school_manager.core.system.LoginType;
import com.study.school_manager.dao.LoginLogDao;
import com.study.school_manager.util.SpringBeanTool;
import com.study.school_manager.dao.OperationLogDao;

/**
 * @author sdy
 * @date 2019/5/9 17:13
 */
public class LogFactory {

    private static OperationLogDao operationLogDao = SpringBeanTool.getBean(OperationLogDao.class);
    private static LoginLogDao loginLogDao = SpringBeanTool.getBean(LoginLogDao.class);

    public static Runnable operationlog(String name,String method,String username){
        return () ->{
            operationLogDao.insert(name,method,username);
        };
    }

    public static Runnable loginlog(String userName, Integer userType, String ip, LoginType loginType){
        return ()->{
            loginLogDao.insert(userName,userType,ip,loginType.getValue());
        };
    }
}
