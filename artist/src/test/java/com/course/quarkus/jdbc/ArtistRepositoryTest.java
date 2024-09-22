package com.course.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    public void shouldCreateAndFindAnArtist() throws SQLException {
        Artist artist = new Artist("name", "bio");

        artistRepository.persist(artist);
        Assertions.assertNotNull(artist.getId());

        artist = artistRepository.findById(artist.getId());
        Assertions.assertEquals("name", artist.getName());
    }
}
