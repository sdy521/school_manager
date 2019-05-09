package com.study.study_manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sdy
 * 操作日志注解
 * @date 2019/5/9 14:47
 */
@Target(ElementType.METHOD)//定义此注解定义在方法上的
@Retention(RetentionPolicy.RUNTIME)//Annotations（注解）将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。
public @interface OperationLog {
    String value() default "操作日志未表明";
}
