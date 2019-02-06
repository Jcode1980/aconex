package com.aconex.excavation;

import com.aconex.excavation.model.job.IExcavationJob;
import com.aconex.excavation.model.job.IExcavator;
import com.aconex.excavation.service.job.ExcavationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";

    @Mock
    private ExcavationService excavationService;

    @Mock
    private IExcavationJob excavatorJob;



    @Test
    public void startExcavationSimluator() throws IOException {
        InputStream stream = new ByteArrayInputStream("a 1\nr\nq\n".getBytes(StandardCharsets.UTF_8));


        Client client = new Client(TEST_MAPS_FILE, stream);
        client.startExcavationSimluator();

        String _source2 = "321";

        verify(excavationService).startExcavationJob(excavatorJob);
    }


}