package com.java.test;

import com.java.utils.MD5Util;
import org.junit.Test;

/**
 * @author JlX
 * @create 2022-03-06 10:50
 */
public class Mytest {
    @Test
    public void testMD5(){
        String mi = MD5Util.getMD5("000000");
        System.out.println(mi);
    }
}
