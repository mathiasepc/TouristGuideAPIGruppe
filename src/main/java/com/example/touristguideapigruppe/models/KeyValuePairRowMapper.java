package com.example.touristguideapigruppe.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KeyValuePairRowMapper implements RowMapper<KeyValuePair> {
    @Override
    public KeyValuePair mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new KeyValuePair(
                rs.getInt("Id"),
                rs.getString("Name")
        );
    }
}