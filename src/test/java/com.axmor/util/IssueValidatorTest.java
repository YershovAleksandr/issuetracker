package com.axmor.util;

import org.junit.Assert;
import org.junit.Test;

public class IssueValidatorTest {
    @Test
    public void testTitleLengthLess3(){
        boolean ret = IssueValidator.isTitleValid("ab");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testTitleLengthEquals3(){
        boolean ret = IssueValidator.isTitleValid("abc");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testTitleLengthMore3(){
        boolean ret = IssueValidator.isTitleValid("abcd");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testDescriptionLengthLess3(){
        boolean ret = IssueValidator.isDescriptionValid("ab");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testDescriptionLengthEquals3(){
        boolean ret = IssueValidator.isDescriptionValid("abc");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testDescriptionLengthMore3(){
        boolean ret = IssueValidator.isDescriptionValid("abcd");
        Assert.assertEquals(ret, true);
    }
}