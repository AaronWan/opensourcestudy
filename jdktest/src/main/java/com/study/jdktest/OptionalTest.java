package com.study.jdktest;

import lombok.Data;
import org.junit.Assert;

import java.util.Objects;
import java.util.Optional;

/**
 * @author 万松(Aaron)
 * @creat_date: 2020/9/17
 * @creat_time: 11:22
 * @since 7.2.5
 */
public class OptionalTest {
  public static void main(String[] args) {
    /**
     * 不为空判断
     */
    Assert.assertFalse(Optional.ofNullable(null).isPresent());
    /**
     * 取user的name
     */
    /**
     * 为空时默认值
     */
    String userName=null;
    User user=null;
    if(Objects.isNull(user)){
      userName="username is null";
    }else{
      userName=user.getName();
    }
    /**
     * 使用下面这种方式替代
     */
    userName=Optional.ofNullable(user).map((temp)->temp.getName()).orElse("username is null");
    Assert.assertSame("username is null",userName);


    user=new User();
    user.setName("A");
    userName=Optional.ofNullable(user).map((temp)->temp.getName()).orElse("username is null");
    Assert.assertSame("A",userName);

    userName=Optional.ofNullable(user).flatMap(user1 -> Optional.ofNullable(user1.getName())).orElse("happy");
    Assert.assertSame("A",userName);


    user=null;
    userName=Optional.ofNullable(user).flatMap(user1 -> Optional.ofNullable(user1.getName())).orElse("happy");
    Assert.assertSame("happy",userName);



    user=new User();
    userName=Optional.ofNullable(user).flatMap(user1 -> Optional.ofNullable(user1.getName())).orElse("happy");
    Assert.assertSame("happy",userName);

    user=new User();
    userName=Optional.ofNullable(user).map(item->item.getName()).orElse("a");
    Assert.assertSame("a",userName);

    user=new User();
    user.setName("test");
    userName=Optional.ofNullable(user).filter(item->Objects.isNull(item.getName())).map(item->item.getName()).orElse("happy");
    Assert.assertSame("happy",userName);

    user=new User();
    userName=Optional.ofNullable(user).map(item->item.getName()).orElse("happy");
    Assert.assertSame("happy",userName);
  }
  @Data
  static class User{
    String name;
  }
}
