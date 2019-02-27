package com.junit.runner.test.hamcrest;

import com.google.common.collect.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import static org.junit.Assert.assertThat;

/**
 * @author 万松(Aaron)
 * @creat_date: 2019/1/1
 * @creat_time: 11:35
 * @since 6.4
 */
public class HamcrestTest {

    @Test
    public void testMatch(){
        assertThat(Lists.newArrayList("a","b"),CoreMatchers.anyOf(CoreMatchers.hasItem("a"),CoreMatchers.hasItem("b")));
    }

}
