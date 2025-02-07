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
}
