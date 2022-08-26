package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import util.Generator;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimulationTest {
    @Mock
    private Generator generator;

    @InjectMocks
    Simulation simulation;

    @Test
    void simulate() {
        when(generator.generateRandomCharacterWithProbability(eq(0.05), anyChar())).thenReturn('C');

        Optional<Map.Entry<String, Integer>> record = simulation.simulate("ZDCABT", "ABCABC", 100);

        assertTrue(record.isPresent());
        assertEquals("CCCCCC", record.get().getKey());
        assertEquals(2, record.get().getValue());
    }
}