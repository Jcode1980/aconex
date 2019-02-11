package com.aconex.excavation.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CostTypeTest {
    private CostType costType;

    @Before
    public void setUp() {
        costType = new CostType(CostType.COMMUNICATION_OVERHEAD_TYPE, 1);
    }

    @After
    public void tearDown()  {
        costType = null;
    }

    @Test
    public void getName() {
        assertThat(costType.getName(), is(CostType.COMMUNICATION_OVERHEAD_TYPE));
    }

    @Test
    public void getPerUnitCost() {
        assertThat(costType.getPerUnitCost(), is(1));
    }


}