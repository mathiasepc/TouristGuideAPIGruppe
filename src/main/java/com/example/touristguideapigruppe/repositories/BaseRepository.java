package com.example.touristguideapigruppe.repositories;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseRepository {

    private final JdbcTemplate jdbc;

    public BaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
}
