package com.figli.metronom;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Metronom Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Metronom
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}