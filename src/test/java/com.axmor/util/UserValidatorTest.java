package com.axmor.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UserValidatorTest {

    //TODO
    @Ignore
    @Test
    public void testNameLengthLess3(){
        boolean ret = UserValidator.isNameValid("ab");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testNameLengthEquals3(){
        boolean ret = UserValidator.isNameValid("abc");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testNameLengthMore3(){
        boolean ret = UserValidator.isNameValid("abcd");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testPasswordLengthEquals0(){
        boolean ret = UserValidator.isPasswordValid("");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testPasswordLengthMore0(){
        boolean ret = UserValidator.isPasswordValid("a");
        Assert.assertEquals(ret, true);
    }

}
