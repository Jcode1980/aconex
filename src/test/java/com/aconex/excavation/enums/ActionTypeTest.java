package com.aconex.excavation.enums;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActionTypeTest {

    @Test
    public void pattern() {
        assertThat(ActionType.LEFT.pattern(), is("(?i)l(?-i)"));
    }

    @Test
    public void actionTypes() {
        List<ActionType> list = ActionType.actionTypes();
        assertThat(list, contains(ActionType.LEFT, ActionType.RIGHT, ActionType.ADVANCE,
        ActionType.QUIT));

    }
}