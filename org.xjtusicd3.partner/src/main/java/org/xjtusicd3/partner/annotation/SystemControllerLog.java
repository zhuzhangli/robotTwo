package org.xjtusicd3.partner.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.METHOD})   //作用域   
@Retention(RetentionPolicy.RUNTIME)     //生命周期
@Documented  //生成java doc
@Inherited  //继承

//自定义注解
public @interface SystemControllerLog {
	public String description() default "";  
}
