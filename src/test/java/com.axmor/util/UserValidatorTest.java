package com.axmor.util;

import org.junit.Assert;
import org.junit.Test;

public class UserValidatorTest {
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
    public void testPasswordLengthLess3(){
        boolean ret = UserValidator.isPasswordValid("ab");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testPasswordLengthEquals3(){
        boolean ret = UserValidator.isPasswordValid("abc");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testPasswordLengthMore3(){
        boolean ret = UserValidator.isPasswordValid("abcd");
        Assert.assertEquals(ret, true);
    }
}