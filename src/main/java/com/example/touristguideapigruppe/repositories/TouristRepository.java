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

    private void populateAttractions(){
        attractions.add(new TouristAttraction("Tivoli","Det er en forlystelses parke på sjælland"));
        attractions.add(new TouristAttraction("Assistent kirkegården","Det er en kirkegård med mange kendte"));
        attractions.add(new TouristAttraction("Bakken", "unik forlystelsespark med en helt særlig historie - midt ude i naturen"));
        attractions.add(new TouristAttraction("Kronborg", "Det er et historisk slot"));
        attractions.add(new TouristAttraction("Zoologisk have", "Familievenlig park med en masse dyr"));
        attractions.add(new TouristAttraction("Amalienborg", "Historisk slotsbygning, kongefamiliens bosted, vagtskifte hverdag kl. 12"));
        attractions.add(new TouristAttraction("Den lille havfrue", "Verdenskendt havfrue beliggende ved Langelinie"));
        attractions.add(new TouristAttraction("Rundetårn", "Kom op i Europas ældste fungerende observationsbygning"));
        attractions.add(new TouristAttraction("SMK", "Statens museum for kunst rummer mere end 700 års kunst og kunsthistorie"));
        attractions.add(new TouristAttraction("Glyptoteket", "Hop forbi og se en imponerende samling af antik kunst og skulpturer i smukke omgivelser"));
        attractions.add(new TouristAttraction("Nyhavn", "Hyggelig lille gade, ved vandet med masser af cafe muligheder"));
    }
}
