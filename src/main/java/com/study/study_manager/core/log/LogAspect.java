package com.study.study_manager.core.log;

import com.study.study_manager.annotation.OperationLog;
import com.study.study_manager.dao.OperationLogDao;
import com.study.study_manager.util.SpringSecurity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author sdy
 * @date 2019/5/9 15:12
 * 操作日志
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.study.study_manager.annotation.OperationLog)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //先执行业务
        Object result = point.proceed();
        //保存日志
        saveLog(point);
        return result;
    }

    public void saveLog(ProceedingJoinPoint point) throws NoSuchMethodException {
        //获取当前方法
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //操作方法名
        String method = currentMethod.getName();
        //操作用户名
        String username = SpringSecurity.getSysUser().getUsername();
        //操作名
        String name = currentMethod.getAnnotation(OperationLog.class).value();
        LogManager.execute(LogFactory.operationlog(name,method,username));
    }
}
