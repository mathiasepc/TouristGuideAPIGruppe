package com.example.touristguideapigruppe.services;

import com.example.touristguideapigruppe.exceptions.NotFoundException;
import com.example.touristguideapigruppe.exceptions.ValueExistException;
import com.example.touristguideapigruppe.models.KeyValuePair;
import com.example.touristguideapigruppe.models.SaveTouristAttraction;
import com.example.touristguideapigruppe.models.TouristAttraction;
import com.example.touristguideapigruppe.repositories.CityRepository;
import com.example.touristguideapigruppe.repositories.TagRepository;
import com.example.touristguideapigruppe.repositories.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristService {
    private final TouristRepository touristRepository;
    private final CityRepository cityRepository;
    private final TagRepository tagRepository;

    public TouristService(TouristRepository touristRepository, CityRepository cityRepository, TagRepository tagRepository) {
        this.touristRepository = touristRepository;
        this.cityRepository = cityRepository;
        this.tagRepository = tagRepository;
    }

    public List<KeyValuePair> getTags() {
        List<KeyValuePair> tags = new ArrayList<>(
                tagRepository.queryTags());
        if(tags.isEmpty()) throw new NotFoundException("Tags");

        return tags;
    }

    public List<KeyValuePair> getCities() {
        List<KeyValuePair> cities = new ArrayList<>(
                cityRepository.queryCities());
        if (cities.isEmpty()) throw new NotFoundException("Byer");

        return cities;
    }

    public List<TouristAttraction> getAttractions() {
        List<TouristAttraction> attractions = touristRepository.getAttractions();
        if (attractions.isEmpty()) throw new NotFoundException("Der findes ikke noget data");

        return attractions;
    }

    public TouristAttraction getById(int id) throws NotFoundException {
        TouristAttraction result = touristRepository.getById(id);
        if(result == null) throw new NotFoundException(String.valueOf(id));

        return result;
    }

    public TouristAttraction addAttraction(SaveTouristAttraction attraction) {
        // Tjekker om den eksisterer
        boolean exist = touristRepository.checkName(attraction.getName());
        if(exist) throw new ValueExistException(attraction.getName());

        return touristRepository.addAttraction(attraction);
    }

    public boolean updateAttraction(SaveTouristAttraction newAttraction) throws NotFoundException {
        // tjekker om den eksistere
        TouristAttraction old = touristRepository.getById(newAttraction.getId());
        if(old == null) throw new NotFoundException(String.valueOf(newAttraction.getId()));

        return touristRepository.updateAttraction(newAttraction, old);
    }

    public boolean deleteAttraction(int id) throws NotFoundException {
        TouristAttraction exist = touristRepository.getById(id);
        if(exist == null) throw new NotFoundException(String.valueOf(id));

        return touristRepository.deleteAttraction(exist);
    }
}
