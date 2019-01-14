package com.mm.movie;

import com.mm.movie.util.StringUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sandeep on 01/13/19.
 */

public class StringUtilsTest {

    @Test
    public void join_isCorrect() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("abc");
        assertEquals(StringUtils.join(list), "abc");
    }

    @Test
    public void join2_isCorrect() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("mno");
        list.add("xyz");
        assertEquals(StringUtils.join(list), "abc, mno, xyz");
    }
}
