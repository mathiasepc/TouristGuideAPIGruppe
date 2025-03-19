package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.KeyValuePair;
import com.example.touristguideapigruppe.models.KeyValuePairRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository extends BaseRepository {

    public TagRepository(JdbcTemplate jdbc) {
        super(jdbc);
    }

    public List<KeyValuePair> queryTags() {
        String sql = "SELECT * FROM tag";
        return getJdbc().query(sql,new KeyValuePairRowMapper());
    }
}
