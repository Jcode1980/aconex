package com.aconex.excavation.model.job;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
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


    @Before
    public void setUp(){
        excavationJob = new ExcavationJob(siteMock, excavatorMock);
        excavationJob.addToInstructions(advanceInstructionMock);
        excavationJob.addToInstructions(quitInstructionMock);

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