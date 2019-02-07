package com.aconex.excavation;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ApplicationTest {
    private static final String TEST_MAPS_FILE = "src/main/resources/test/TestMap.txt";
    private static final String TEST_INSTRUCTIONS_FILE="src/main/resources/test/TestInstructionsFile.txt";

    @Test
    public void main() {
    }

    @org.junit.Test
    public void main_shouldOutputCorrectValue(){
        Application.main(new String[]{TEST_MAPS_FILE, TEST_INSTRUCTIONS_FILE});
    }
}