package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.Tags;
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

    public List<Tags> getTagsByName(String name){

        for(TouristAttraction touristAttraction : attractions){
            if(touristAttraction.getName().equalsIgnoreCase(name)){
           return touristAttraction.getTags();
            }
        }

        return null;
    }

    private void populateAttractions(){
        attractions.add(new TouristAttraction("Tivoli","Det er en forlystelsespark på sjælland", "København", List.of(Tags.BØRNEVENLIG, Tags.UNDERHOLDNING, Tags.RESTAURANT)));
        attractions.add(new TouristAttraction("Assistent kirkegården","Det er en kirkegård med mange kendte", "Nørrebro", List.of(Tags.NATUR, Tags.GRATIS)));
        attractions.add(new TouristAttraction("Bakken", "unik forlystelsespark med en helt særlig historie - midt ude i naturen", "Klampenborg", List.of(Tags.NATUR, Tags.UNDERHOLDNING, Tags.BØRNEVENLIG, Tags.RESTAURANT)));
        attractions.add(new TouristAttraction("Kronborg", "Det er et historisk slot", "Helsingør", List.of(Tags.NATUR, Tags.KUNST, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Zoologisk have", "Familievenlig park med en masse dyr", "Frederiksberg", List.of(Tags.NATUR, Tags.BØRNEVENLIG, Tags.RESTAURANT)));
        attractions.add(new TouristAttraction("Amalienborg", "Historisk slotsbygning, kongefamiliens bosted, vagtskifte hverdag kl. 12", "København", List.of(Tags.KUNST, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Den lille havfrue", "Verdenskendt havfrue beliggende ved Langelinie", "København", List.of(Tags.GRATIS, Tags.KUNST, Tags.NATUR)));
        attractions.add(new TouristAttraction("Rundetårn", "Kom op i Europas ældste fungerende observationsbygning", "København", List.of(Tags.KUNST)));
        attractions.add(new TouristAttraction("SMK", "Statens museum for kunst rummer mere end 700 års kunst og kunsthistorie", "København", List.of(Tags.KUNST, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Glyptoteket", "Hop forbi og se en imponerende samling af antik kunst og skulpturer i smukke omgivelser", "København", List.of(Tags.KUNST, Tags.MUSEUM)));
        attractions.add(new TouristAttraction("Nyhavn", "Hyggelig lille gade, ved vandet med masser af cafe muligheder", "København",List.of(Tags.RESTAURANT)));
    }
}
