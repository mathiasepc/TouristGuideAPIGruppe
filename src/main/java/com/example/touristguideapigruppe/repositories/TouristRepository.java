package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

public class TouristRepository {
    List<TouristAttraction> attractions;

    public TouristRepository(){
        this.attractions = new ArrayList<>();
        populateAttractions();
    }

    private void populateAttractions(){
        attractions.add(new TouristAttraction("Tivoli","Det er en forlystelses parke på sjælland"));
        attractions.add(new TouristAttraction("Assistent kirkegården","Det er en kirkegård med mange kendte"));
        attractions.add(new TouristAttraction("Bakken", "Det er en forlystelses parke"));
        attractions.add(new TouristAttraction("Krongborg", "Det er et historisk slot"));
    }
}
