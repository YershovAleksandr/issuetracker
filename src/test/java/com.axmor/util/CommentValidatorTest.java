package com.axmor.util;

import org.junit.Assert;
import org.junit.Test;

public class CommentValidatorTest {
    @Test
    public void testStatusEqualsCreated(){
        boolean ret = CommentValidator.isStatusValid("created");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testStatusEqualsResolved(){
        boolean ret = CommentValidator.isStatusValid("resolved");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testStatusEqualsClosed(){
        boolean ret = CommentValidator.isStatusValid("closed");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testStatusEqualsDuplicated(){
        boolean ret = CommentValidator.isStatusValid("duplicated");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testStatusEqualsReopened(){
        boolean ret = CommentValidator.isStatusValid("reopened");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testStatusEqualsFucked(){
        boolean ret = CommentValidator.isStatusValid("fucked");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testTextLengthLess3(){
        boolean ret = CommentValidator.isTextValid("ab");
        Assert.assertEquals(ret, false);
    }

    @Test
    public void testTextLengthEquals3(){
        boolean ret = CommentValidator.isTextValid("abc");
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testTextLengthMore3(){
        boolean ret = CommentValidator.isTextValid("abcd");
        Assert.assertEquals(ret, true);
    }

}
