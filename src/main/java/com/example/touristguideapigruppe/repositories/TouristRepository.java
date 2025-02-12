package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    List<TouristAttraction> attractions;

    public TouristRepository() {
        this.attractions = new ArrayList<>();
        populateAttractions();
    }

    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    public TouristAttraction getByName(String name) {
        for (var item : attractions) {
            if (item.getName().equals(name))
                return item;
        }

        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(TouristAttraction oldAttraction, TouristAttraction newAttraction) {
        attractions.remove(oldAttraction);
        attractions.add(newAttraction);

        return newAttraction;
    }

    public TouristAttraction deleteAttraction(TouristAttraction attraction) {
        attractions.remove(attraction);
        return attraction;
    }

    private void populateAttractions() {
        attractions.add(new TouristAttraction("Tivoli", "Det er en forlystelses parke på sjælland"));
        attractions.add(new TouristAttraction("Assistent kirkegården", "Det er en kirkegård med mange kendte"));
        attractions.add(new TouristAttraction("Bakken", "Det er en forlystelses parke"));
        attractions.add(new TouristAttraction("Krongborg", "Det er et historisk slot"));
    }
}
