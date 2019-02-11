package com.aconex.excavation.service.job;

import com.aconex.excavation.enums.RotationDirection;
import com.aconex.excavation.model.job.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExcavationServiceTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";
    private ExcavationService excavationService;
    @Mock
    private IExcavationJob jobMock;
    @Mock
    private IExcavator excavatorMock;
    @Mock
    private ISite siteMock;
    @Mock
    private ITerrain terrainMock;
    @Mock
    private Point pointMock;


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Before
    public void setUp()  {
        excavationService = new ExcavationService();
        when(jobMock.excavator()).thenReturn(excavatorMock);
        when(jobMock.site()).thenReturn(siteMock);
        when(excavatorMock.nextMoveCoordinates()).thenReturn(pointMock);
        when(siteMock.terrainForCoordinate(pointMock)).thenReturn(terrainMock);
        when(siteMock.coordinatesAreValid(pointMock)).thenReturn(true);

        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        excavationService = null;
    }

    @Test
    public void createExcavationJob() throws IOException {
        IExcavationJob job = excavationService.createExcavationJob(TEST_MAPS_FILE);
        assertNotNull(job.excavator());
        assertNotNull(job.site());
    }

    @Test
    public void processCommandForJob() {
        excavationService.processCommandForJob("l", jobMock);
        verify(excavatorMock).rotate(RotationDirection.LEFT);

        excavationService.processCommandForJob("r", jobMock);
        verify(excavatorMock).rotate(RotationDirection.RIGHT);

        excavationService.processCommandForJob("a 4", jobMock);
        verify(excavatorMock, times(4)).moveAndExcavate(terrainMock);
    }

    @Test
    public void createTerrainsMap() throws IOException {
        ArrayList<ArrayList<ITerrain>> terrainsMap= excavationService.createTerrainsMap(TEST_MAPS_FILE);
        assertThat(terrainsMap.size(), is(5));
        assertThat(terrainsMap.get(0).size(), is(10));
    }

    @Test
    public void procesCommandForJob_shouldPrintOutUnknownCommandString(){
        excavationService.processCommandForJob("x 4", jobMock);
        assertThat(outContent.toString(), containsString("Unknown command given. Please enter valid command"));
    }

    @Test(expected = NullPointerException.class)
    public void procesCommandForJob_shouldReturnNullPointExceptionWhenGivenNullValueForJob(){
        excavationService.processCommandForJob("x 4", null);
    }

    @Test(expected = NullPointerException.class)
    public void procesCommandForJob_shouldReturnNullPointExceptionWhenGivenNullCommand(){
        excavationService.processCommandForJob(null, jobMock);
    }

    @Test
    public void terrainTypeForChar() {
        TerrainType terrainType = excavationService.terrainTypeForChar(TerrainType.REMOVABLE_TREE_CODE);
        assertThat(terrainType.getName(), is(TerrainType.REMOVABLE_TREE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void terrainTypeForChar_shouldReturnIllegalArgumentExceptionWhenSentUnknownChar() {
        excavationService.terrainTypeForChar('z');
    }



}