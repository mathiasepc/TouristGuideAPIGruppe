package com.example.touristguideapigruppe.repositories;

import com.example.touristguideapigruppe.models.KeyValuePair;
import com.example.touristguideapigruppe.models.KeyValuePairRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository extends BaseRepository {

    public CityRepository(JdbcTemplate jdbc) {
        super(jdbc);
    }

    public List<KeyValuePair> queryCities(){
        String sql = "select * from city";
        return getJdbc().query(sql,new KeyValuePairRowMapper());
    }

}

