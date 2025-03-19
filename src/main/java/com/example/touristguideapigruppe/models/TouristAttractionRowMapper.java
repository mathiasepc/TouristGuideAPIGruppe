package com.example.touristguideapigruppe.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TouristAttractionRowMapper implements RowMapper<TouristAttraction> {
    @Override
    public TouristAttraction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TouristAttraction(
                rs.getInt("attraction_Id"),
                rs.getString("attraction_Name"),
                rs.getString("attraction_Description"),
                new KeyValuePair(rs.getInt("City_Id"), rs.getString("City_Name")),
                new ArrayList<>()
        );
    }
}
