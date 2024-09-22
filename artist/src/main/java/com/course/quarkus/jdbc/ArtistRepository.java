package com.course.quarkus.jdbc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

    @Inject
    DataSource dataSource;

    public void persist(Artist artist) throws SQLException {

        //start Connection
        Connection connection = dataSource.getConnection();
        //Query
        String sqlCreateQuery = "INSERT INTO t_artists (id, name, bio, created_date) VALUES (?, ?, ?, ?)";

        artist.setId(Math.abs(new Random().nextLong()));

        //Prepare statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateQuery)) {

            preparedStatement.setLong(1, artist.getId());
            preparedStatement.setString(2, artist.getName());
            preparedStatement.setString(3, artist.getBio());
            preparedStatement.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
            preparedStatement.executeUpdate();
        }

        //close connection
        connection.close();
    }

    public Artist findById(Long id) throws SQLException {

        //start Connection
        Connection connection = dataSource.getConnection();
        //Query
        String sqlFindQuery = "SELECT id, name, bio, created_date FROM t_artists WHERE id = ?";
        Artist artist = new Artist();

        //prepare the statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlFindQuery)) {
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                artist.setId(result.getLong(1));
                artist.setName(result.getString(2));
                artist.setBio(result.getString(3));
                artist.setCreatedDate(result.getTimestamp(4).toInstant());
            }
        }

        //close connection
        connection.close();

        return artist;
    }

}
