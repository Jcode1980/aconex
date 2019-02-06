package com.aconex.excavation.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CostTypeTest {
    private CostType costType;
    private static final String INVALID_COST_TYPE_STRING = "aoaoaoaoaoaoa";
    @Before
    public void setUp() throws Exception {
        costType = new CostType(CostType.COMMUNICATION_OVERHEAD_TYPE, 1);
    }

    @After
    public void tearDown() throws Exception {
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

    @Test
    public void costTypeForString() {
        CostType localCostType = CostType.costTypeForString(CostType.COMMUNICATION_OVERHEAD_TYPE);
        assertThat(localCostType.getName(), is(CostType.COMMUNICATION_OVERHEAD_TYPE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void costTypeForString_shouldReturnIllegalArgumentExceptionWhenSentUnknownString() {
        CostType.costTypeForString(INVALID_COST_TYPE_STRING);
    }

}