package com.example.touristguide.repository;

import com.example.touristguide.model.Attraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
    private Attraction buildAttraction(ResultSet rs) throws SQLException {
        String name = rs.getString("attr_name");
        String description = rs.getString("attr_description");
        String city = rs.getString("city_name");
        List<String> tagList = buildTagList(name);
        return new Attraction(name, description, city, tagList);
    }


    // build tag list -------------------------
    private List<String> buildTagList(String attractionName) throws SQLException {
        List<String> tagList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("""
                        select tag_name from attraction
                        natural join attraction_tags
                        natural join tag
                        where attraction.attr_name = ?;""");
        ) {
            ps.setString(1, attractionName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tagList.add(rs.getString("tag_name"));
            }
        }
        return tagList;
    }

    // get attraction list  -------------------------

    public List<Attraction> getAttractionList() {
        List<Attraction> attractionList = new ArrayList<>();;
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("""
                        select * from attraction
                        natural join city;""")
        ) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                attractionList.add(buildAttraction(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attractionList;
    }

    // add attraction -------------------------

    public void addAttraction(Attraction attraction) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps1 = connection.prepareStatement("""
                        INSERT ignore INTO attraction (city_id, attr_name, attr_description)
                        values ((select city_id from city where city_name = ?), ?, ?);""");
        ) {
            ps1.setString(1, attraction.getCity());
            ps1.setString(2, attraction.getName());
            ps1.setString(3, attraction.getDescription());
            int rowsAffected = ps1.executeUpdate();
            if (rowsAffected != 0) {
                try (
                        // insert attr tags in all tags list
                        PreparedStatement ps2 = connection.prepareStatement("""
                                INSERT ignore INTO tag (tag_name)
                                values (?);""");
                ) {
                    for (String tag : attraction.getTags()) {
                        ps2.setString(1, tag);
                        ps2.executeUpdate();
                    }
                }
                try (
                        // insert attr_id and tag_id in attraction_tags
                        PreparedStatement ps3 = connection.prepareStatement("""
                                INSERT INTO attraction_tags
                                VALUES (
                                (select attr_id from attraction
                                where attr_name = ?),
                                (select tag_id from tag
                                where tag_name = ?)
                                );""");
                ) {
                    for (String tag : attraction.getTags()) {
                        ps3.setString(1, attraction.getName());
                        ps3.setString(2, tag);
                        ps3.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // update attraction -------------------------

    public void updateAttraction(Attraction attraction
    ) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps1 = connection.prepareStatement("""
                        UPDATE attraction
                        SET city_id =
                        (select city_id from city where city_name = ?),
                        attr_name = ?,
                        attr_description = ?
                        WHERE attr_id =
                        (select attr_id from attraction where attr_name = ?);""")
        ) {
            ps1.setString(1, attraction.getCity());
            ps1.setString(2, attraction.getName());
            ps1.setString(3, attraction.getDescription());
            ps1.setString(4, attraction.getName());
            int rowsAffected = ps1.executeUpdate();
            if (rowsAffected != 0) {
                try (
                        PreparedStatement ps2 = connection.prepareStatement("""
                                INSERT ignore INTO tag (tag_name)
                                values (?);""")
                ) {
                    for (String tag : attraction.getTags()) {
                        ps2.setString(1, tag);
                        ps2.executeUpdate();
                    }
                }

                try (
                        PreparedStatement ps3 = connection.prepareStatement("""
                                delete from attraction_tags
                                where attr_id =
                                (select attr_id from attraction
                                where attr_name = ?);""")
                ) {
                    ps3.setString(1, attraction.getName());
                    ps3.executeUpdate();
                }
                try (
                        PreparedStatement ps4 = connection.prepareStatement("""
                                INSERT INTO attraction_tags
                                VALUES (
                                (select attr_id from attraction
                                where attr_name = ?),
                                (select tag_id from tag
                                where tag_name = ?)
                                );""")
                ) {
                    for (String tag : attraction.getTags()) {
                        ps4.setString(1, attraction.getName());
                        ps4.setString(2, tag);
                        ps4.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // delete attraction -------------------------

    public void deleteAttraction(String attractionName) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps1 = connection.prepareStatement("""
                        delete from attraction_tags
                        where attr_id =
                        (select attr_id from attraction
                        where attr_name = ?);""");
        ) {
            ps1.setString(1, attractionName);
            ps1.executeUpdate();
            try (
                    PreparedStatement ps2 = connection.prepareStatement("""
                            delete from attraction
                            where attr_id =
                            (select attr_id from attraction
                            where attr_name = ?);""");
            ) {
                ps2.setString(1, attractionName);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // find attraction by name -------------------------

    public Attraction findByName(String name) {
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("""
                        select * from attraction
                        natural join city
                        where attr_name = ?;""")
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            //rs.next();
            if (rs.next()) {
                return buildAttraction(rs);
            }
            else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get all tags -------------------------


    public List<String> getAllTags() {
        List<String> tagList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("""
                        SELECT tag_name FROM tag;""")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tagList.add(rs.getString("tag_name"));
            }
            return tagList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // get all tags -------------------------


    public List<String> getAllCities() {
        List<String> cityList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement("""
                    SELECT city_name FROM city;""")
                ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cityList.add(rs.getString("city_name"));
            }
            return cityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}


