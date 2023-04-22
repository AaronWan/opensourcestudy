package com.study.exception;

import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @author wansong
 * @date 2023/3/31
 * @apiNote
 **/
public class ExceptionTest {
    @Test
    public void testException(){
        try {
            invoke();
        }catch(Throwable e){
            System.out.println(e.getMessage());
        }
    }

    @SneakyThrows
    public void invoke(){
        throw new BusinessException("test");
    }

    public class BusinessException extends RuntimeException{
        public BusinessException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return "business:"+super.getMessage();
        }
    }

}