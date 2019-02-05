package com.aconex.excavation.model.job;

import com.aconex.excavation.enums.ActionType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ExcavationJobTest {
    private IExcavationJob excavationJob;

    @Mock
    private Site siteMock;
    @Mock
    private Excavator excavatorMock;
    @Mock
    private Instruction quitInstructionMock;
    @Mock
    private Instruction advanceInstructionMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp(){
        excavationJob = new ExcavationJob(siteMock, excavatorMock);
        excavationJob.addToInstructions(advanceInstructionMock);
        excavationJob.addToInstructions(quitInstructionMock);

        when(advanceInstructionMock.actionType()).thenReturn(ActionType.ADVANCE);
        when(quitInstructionMock.actionType()).thenReturn(ActionType.QUIT);
    }

    @Test
    public void excavator() {
        assertEquals(excavatorMock, excavationJob.excavator());
    }

    @Test
    public void site() {
        assertEquals(siteMock, excavationJob.site());
    }

    @Test
    public void instructions() {
        List<IInstruction> instructions = excavationJob.instructions();
        assertThat(instructions, hasSize(2));
        assertThat(instructions.get(0), is(advanceInstructionMock));
        assertThat(instructions.get(1), is(quitInstructionMock));
    }

    @Test
    public void billableInstructions() {

    }
}