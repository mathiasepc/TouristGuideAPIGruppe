package com.example.touristguideapigruppe.controllers;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.models.KeyValuePair;
import com.example.touristguideapigruppe.models.SaveTouristAttraction;
import com.example.touristguideapigruppe.services.TouristService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    private List<TouristAttraction> mockAttractions;
    private TouristAttraction mockAttraction;
    private SaveTouristAttraction mockSaveAttraction;
    private int mockId;
    private List<KeyValuePair> cities;
    private List<KeyValuePair> tags;

    // Autowired laver selv en instance af mockMvc således:
    // MockMvc mockMvc = new MockMvc;
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp() {
        // Arrange
        mockAttractions = new ArrayList<>(List.of(
                new TouristAttraction(1,
                        "Tivoli",
                        "Det er en forlystelses parke på Sjælland",
                        new KeyValuePair(1,"København"),
                        List.of(new KeyValuePair(4,"Forlystelses park"),
                                new KeyValuePair(2,"Underholdning"))),

                new TouristAttraction(2,
                        "Assistent kirkegården",
                        "Det er en kirkegård med mange kendte",
                        new KeyValuePair(1,"København"),
                        List.of(new KeyValuePair(1,"Kirkegård"),
                                new KeyValuePair(2,"Underholdning"),
                                new KeyValuePair(3,"Historisk")))
        ));
        mockId = 1;
        mockAttraction = new TouristAttraction(mockId, "Tivoli",
                "Det er en forlystelses parke på Sjælland",
                new KeyValuePair(1,"København"),
                List.of(new KeyValuePair(2,"Underholdning"),
                        new KeyValuePair(3,"Historisk")));
        mockSaveAttraction = new SaveTouristAttraction(1,"Tivoli","Det er en forlystelsespark",
                1,
                List.of(1,2,3));
        cities = List.of(new KeyValuePair(1, "København"),
                new KeyValuePair(2, "Klampenborg"));
        tags = List.of(
                new KeyValuePair(1,"Sjov"),
                new KeyValuePair(2,"Museum"));
    }

    @Test
    void getAttractions() throws Exception {

        // Springer touristService.getAttractions() over og replacer med mockOrders
        when(touristService.getAttractions()).thenReturn(mockAttractions);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractions"))
                .andExpect(model().attributeExists("attractions"))
                .andExpect(model().attribute("attractions", mockAttractions));

        verify(touristService, times(1)).getAttractions();
    }

    @Test
    void getById() throws Exception {
        when(touristService.getById(any(Integer.class))).thenReturn(mockAttraction);

        mockMvc.perform(get("/attractions/{id}", mockId))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction-information"))
                .andExpect(model().attributeExists("specific"))
                .andExpect(model().attribute("specific", mockAttraction));

        verify(touristService, times(1))
                .getById(any(Integer.class));
    }

    @Test
    void getAddAttration() throws Exception {
        when(touristService.getCities()).thenReturn(cities);

        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-attraction"))
                .andExpect(model().attributeExists("attraction", "tags", "cities"))
                .andExpect(model().attribute("cities", cities));

        verify(touristService, times(1)).getCities();
    }

    @Test
    void addAttraction() throws Exception {
        when(touristService.addAttraction(any(SaveTouristAttraction.class)))
                .thenReturn(mockAttraction);

        // Act
        mockMvc.perform(post("/attractions/save")
                        .param("id", String.valueOf(1))
                        .param("name", "Tivoli")
                        .param("description", "Det er en forlystelses parke på Sjælland")
                        .param("city", "København")
                        .param("tags", "forlystelsespark","Underholdning","Arkitektur"))

                // Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));
        verify(touristService, times(1))
                .addAttraction(any(SaveTouristAttraction.class));

    }


    @Test
    void updateAttraction() throws Exception {
        when(touristService.updateAttraction(any(SaveTouristAttraction.class)))
                .thenReturn(true);

        mockMvc.perform(post("/attractions/update")
                        .param("id", String.valueOf(1))
                        .param("name", "Tivoli")
                        .param("description", "Det er en forlystelses parke på Sjælland")
                        .param("city", "København")
                        .param("tags", "forlystelsespark","Underholdning","Arkitektur"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService, times(1))
                .updateAttraction(any(SaveTouristAttraction.class));
    }

    @Test
    void deleteAttraction() throws Exception {
        when(touristService.deleteAttraction(mockId))
                .thenReturn(true);

        mockMvc.perform(post("/attractions/delete/{id}", mockId)
                        .param("id", String.valueOf(mockId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/attractions"));

        verify(touristService,times(1))
                .deleteAttraction(mockId);
    }

}
