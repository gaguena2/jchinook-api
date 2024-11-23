package com.gaguena.jchinookapi.repository;

import com.gaguena.jchinookapi.data.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AlbumRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<Album> findBy(Integer code) {
        try {
            var result = jdbcTemplate.queryForObject("SELECT * FROM \"Album\" WHERE \"AlbumId\" = ?", ((rs, rowNum) -> {
                System.out.println(rs.wasNull());
                var id = rs.getInt("AlbumId");
                var title = rs.getString("Title");
                return new Album(id, title, null);
            }), code);
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
