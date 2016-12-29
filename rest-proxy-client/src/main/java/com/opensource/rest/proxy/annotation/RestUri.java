package com.opensource.rest.proxy.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Aaron on 26/12/2016.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RestUri {
    String value() default "";

    MethodType method() default MethodType.POST;

    String contentType() default "application/json";

    String desc() default "";

}
