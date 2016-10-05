package com.muxistudio.jobs.setting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by ybao on 16/7/17.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        Assert.assertEquals(6,mCalculator.sum(4,2),0);
    }

    @Test
    public void testSubstract() throws Exception {
        Assert.assertEquals(2,mCalculator.substract(4,2),0);
    }

    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(2,mCalculator.divide(4,2),0);
    }

    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(4,mCalculator.multiply(2,2),0);
    }
}