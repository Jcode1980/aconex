package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.ActionType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InstructionTest {
    private IInstruction instruction;


    @Mock
    private TerrainType terrainTypeMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        instruction = new Instruction(ActionType.ADVANCE, 3);
    }

    @After
    public void tearDown() throws Exception {
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