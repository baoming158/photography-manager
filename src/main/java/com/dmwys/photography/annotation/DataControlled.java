package com.dmwys.photography.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
/**
 * 如果数据实体类需要做访问控制，该注解能够满足你的要求
 * 控制需要配合admin_id 字段
 */
public @interface DataControlled {
}
