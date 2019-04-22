package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.ActionType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class InstructionTest {
    private IInstruction instruction;


    @Before
    public void setUp() {
        instruction = new Instruction(ActionType.ADVANCE, 3);
    }

    @After
    public void tearDown() {
        instruction = null;
    }

    @Test
    public void actionType() {
        assertThat(instruction.actionType(), is(ActionType.ADVANCE));
    }

    @Test
    public void units() {
        assertThat(instruction.units(), is(Optional.of(3)));
    }

    @Test
    public void toStringTest() {
        String expectedString = ActionType.ADVANCE + " " + 3;
        assertThat(instruction.toString(), is(expectedString));

    }
}