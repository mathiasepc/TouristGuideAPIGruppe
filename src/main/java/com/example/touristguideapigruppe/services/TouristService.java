package com.example.touristguideapigruppe.services;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.ValueExistException;
import com.example.touristguideapigruppe.models.Tags;
import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAttractions() {
        return touristRepository.getAttractions();
    }

    public TouristAttraction getByName(String name) throws NotFoundException {
        TouristAttraction result = touristRepository.getByName(name);
        if (result == null) throw new NotFoundException(name);

        return result;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        TouristAttraction exist = touristRepository.getByName(attraction.getName());
        if (exist != null) throw new ValueExistException(exist.getName());

        return touristRepository.addAttraction(attraction);
    }

    public TouristAttraction updateAttraction(TouristAttraction newAttraction) throws NotFoundException {
        TouristAttraction old = touristRepository.getByName(newAttraction.getName());
        if (old == null) throw new NotFoundException(newAttraction.getName());

        return touristRepository.updateAttraction(old, newAttraction);
    }

    public TouristAttraction deleteAttraction(String name) throws NotFoundException {
        TouristAttraction exist = touristRepository.getByName(name);
        if (exist == null) throw new NotFoundException(name);

        return touristRepository.deleteAttraction(exist);
    }


}
