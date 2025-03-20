package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristController.class)
@ExtendWith(MockitoExtension.class)  // Brug Mockito's JUnit 5 udvidelse
@Import(TouristService.class)        // Sikrer, at service bean er tilgængelig
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TouristService touristService;

    @InjectMocks
    private TouristController touristController;

    private List<TouristAttraction> mockAttractions;

    @BeforeEach
    void setUp() {
        // Indsæt en tom liste [] for tags
        mockAttractions = Arrays.asList(
                new TouristAttraction("Den Lille Havfrue", "Et ikonisk dansk vartegn", "København", Collections.emptyList()),
                new TouristAttraction("Tivoli", "En af verdens ældste forlystelsesparker", "København", Collections.emptyList())
        );
    }

    @Test
    void testGetAttractions() throws Exception {
        // Arrange
        when(touristService.getAttractions()).thenReturn(mockAttractions);

        // Act & Assert
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractions"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", mockAttractions));
    }
}
