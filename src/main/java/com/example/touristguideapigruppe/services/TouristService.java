package com.example.touristguideapigruppe.services;

import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAttractions(){
        return touristRepository.getAttractions();
    }

    public TouristAttraction getByName(String name){
        return touristRepository.getByName(name);
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction exist = touristRepository.getByName(attraction.getName());

        return exist != null
                ? null
                : touristRepository.addAttraction(attraction);
    }


    public TouristAttraction updateAttraction(TouristAttraction newAttraction) {
        TouristAttraction old = touristRepository.getByName(newAttraction.getName());

        return old != null
                ? touristRepository.updateAttraction(old, newAttraction)
                : null;
    }

    public TouristAttraction deleteAttraction(String name) {
        TouristAttraction exist = touristRepository.getByName(name);
        return exist != null
                ? touristRepository.deleteAttraction(exist)
                : null;
    }
}
