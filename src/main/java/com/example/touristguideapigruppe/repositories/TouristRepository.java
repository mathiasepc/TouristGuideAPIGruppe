package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TouristRepository extends BaseRepository {
    public TouristRepository(JdbcTemplate jdbc) {
        super(jdbc);
    }

    public List<TouristAttraction> getAttractions() {
        // Giver dem et alias sådan, at RowMapper kan kende forskel på de attributter,
        // som er ens
        String sql = "SELECT attraction.Id AS attraction_Id, attraction.Name AS attraction_Name, attraction.Description AS attraction_Description," +
                "City.Id AS City_Id, City.Name AS City_Name " +
                "FROM attraction " +
                "JOIN city ON attraction.CityId = city.Id;";
        List<TouristAttraction> attractions = getJdbc().query(sql, new TouristAttractionRowMapper());

        // Indsætter attraction
        for (var attraction : attractions)
            attraction.setTag(queryAttractionWithTags(attraction.getId()));

        return attractions;
    }

    public TouristAttraction getById(int id) {
        TouristAttraction attraction = querySpecificAttraction(id);
        attraction.setTag(queryAttractionWithTags(attraction.getId()));

        return attraction;
    }

    // Hvis én af dine queryDeleteAttractionId_TagId eller queryInsertAttractionTag kalder fejler,
    // rulles alt tilbage automatisk.
    @Transactional
    public TouristAttraction addAttraction(SaveTouristAttraction attraction) {
        TouristAttraction newAttraction = queryInsertAttraction(attraction);

        // Indsæt attractionId og tagId i mange-til-mange tabel
        for (var tag : newAttraction.getTags())
            queryInsertAttractionTag(newAttraction.getId(), tag.getId());

        newAttraction.setTag(queryAttractionWithTags(newAttraction.getId()));

        return newAttraction;
    }
    // Indsætter en attraktion
    private TouristAttraction queryInsertAttraction(SaveTouristAttraction attraction) {
        String sql = "INSERT INTO attraction (Name, Description, CityId) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        getJdbc().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, attraction.getName());
            ps.setString(2, attraction.getDescription());
            ps.setInt(3, attraction.getCityId());
            return ps;
        }, keyHolder);

        // Tjekker om der er en nøgle
        return keyHolder.getKey() != null
                // Hvis der er en nøgle returner Attraction
                ? querySpecificAttraction(keyHolder.getKey().intValue())
                // Hvis ikke
                : null;
    }

    private List<KeyValuePair> queryAttractionWithTags(int attractionId) {
        String sql = "SELECT tag.Id, tag.name FROM attraction_tag " +
                "JOIN tag ON tag.id = attraction_tag.TagId " +
                "WHERE attraction_tag.AttractionId = ?";
        return getJdbc().query(sql, new KeyValuePairRowMapper(), attractionId);
    }

    private TouristAttraction querySpecificAttraction(int id) {
        // Giver dem et alias sådan, at RowMapper kan kende forskel på de attributter,
        // som er ens
        String sql = "SELECT attraction.Id AS attraction_Id, attraction.Name AS attraction_Name, attraction.Description AS attraction_Description, " +
                "City.Id AS City_Id, City.Name AS City_Name " +
                "FROM attraction " +
                "JOIN city ON attraction.CityId = city.Id " +
                "WHERE attraction.CityId = city.Id AND attraction.Id = ?;";
        return getJdbc().queryForObject(sql, new TouristAttractionRowMapper(), id);
    }

    // Hvis én af dine queryDeleteAttractionId_TagId eller queryInsertAttractionTag kalder fejler,
    // rulles alt tilbage automatisk.
    @Transactional
    public boolean updateAttraction(SaveTouristAttraction newAttraction, TouristAttraction old) {
        // Opdater attraction
        String sql = "UPDATE attraction " +
                "SET Name = ?, Description = ?, CityId = ? " +
                "WHERE Id = ?";
        // Den smider det antal tilbage, som bliver opdateret
        super.getJdbc()
                .update(sql, newAttraction.getName(), newAttraction.getDescription(),
                        newAttraction.getCityId(), newAttraction.getId());

        // Slet attractionId og tagId fra mange-til-mange tabel
        for (var tag : old.getTags())
            queryDeleteAttractionTag(old.getId(), tag.getId());

        // Indsæt attractionId og tagId i mange-til-mange tabel
        for (var tagId : newAttraction.getTagIds())
            queryInsertAttractionTag(newAttraction.getId(), tagId);

        return true;
    }

    private void queryInsertAttractionTag(int attractionId, int tagId) {
        String sql = "INSERT INTO attraction_tag (AttractionId,TagId) VALUES (?, ?)";
        // Når du har med mange til mange at gøre, skal man bruge update.
        // Den smider det antal tilbage, som bliver opdateret
        super.getJdbc().update(sql, attractionId, tagId);
    }

    // Hvis én af dine queryDeleteAttractionTag eller queryInsertAttractionTag kalder fejler,
    // rulles alt tilbage automatisk.
    @Transactional
    public boolean deleteAttraction(TouristAttraction attraction) {
        for (var tag : attraction.getTags())
            queryDeleteAttractionTag(attraction.getId(), tag.getId());

        String sql = "DELETE FROM attraction WHERE id = ?";
        getJdbc().update(sql, attraction.getId());

        return true;
    }

    private void queryDeleteAttractionTag(int attractionId, int oldTagId) {
        // Slet den gamle relation
        String sql = "DELETE FROM attraction_tag WHERE AttractionId = ? AND TagId = ?";
        super.getJdbc().update(sql, attractionId, oldTagId);
    }

    public boolean checkName(String name) {
        // Tæller hvor mange resultater jeg får
        String sql = "SELECT Count(*) FROM attraction " +
                "WHERE name = ?";
        // Tjekker om den eksisterer
        return getJdbc().queryForObject(sql, Integer.class, name) > 0;
    }
}