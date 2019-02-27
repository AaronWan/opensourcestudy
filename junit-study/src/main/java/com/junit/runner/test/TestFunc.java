package com.junit.runner.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/1/1
 * @creat_time: 10:58
 * @since 6.4
 */
@Suite.SuiteClasses(value = {AdditionWithTheoriesTest.class,TestParameters.class})
@RunWith(value = Suite.class)
public class TestFunc {
}
