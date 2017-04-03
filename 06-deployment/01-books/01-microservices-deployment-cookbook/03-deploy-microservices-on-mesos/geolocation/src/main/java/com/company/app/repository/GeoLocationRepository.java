package com.company.app.repository;

import com.company.app.model.GeoLocation;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoLocationRepository {
    private List<GeoLocation> geoLocations = new ArrayList<>();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String DATA_FILES_DIR = System.getenv("GEOLOCATION_DATA_FILES_DIR") != null
            ? System.getenv("GEOLOCATION_DATA_FILES_DIR") : "/opt/packt/geolocation/data";

    public void addGeoLocation(GeoLocation geoLocation){
        geoLocations.add(geoLocation);

        try {
            MAPPER.writeValue(new File(DATA_FILES_DIR + "/user" + geoLocation.getUserId() + "_t" + geoLocation.getTimestamp()), geoLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GeoLocation> geoLocations() {
        return Collections.unmodifiableList(geoLocations);
    }
}
