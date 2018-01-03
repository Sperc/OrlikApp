package com.example.pawel.orlikapp;

import com.example.pawel.orlikapp.ui.menu.find_playground.DataHelper;
import com.example.pawel.orlikapp.utils.DateHelper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    String date = "2018-01-02";
    @Test
    public void chceckDataMethod(){
        int w = DateHelper.getMonthFromDate(date);
        assertEquals(0,w);
    }
}