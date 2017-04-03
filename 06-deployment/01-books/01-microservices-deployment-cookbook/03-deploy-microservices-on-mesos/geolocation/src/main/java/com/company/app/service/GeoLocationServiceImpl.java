package com.company.app.service;

import com.company.app.model.GeoLocation;
import com.company.app.repository.GeoLocationRepository;

import java.util.List;

public class GeoLocationServiceImpl implements GeoLocationService {

    private GeoLocationRepository repository = new GeoLocationRepository();

    @Override
    public GeoLocation create(GeoLocation geolocation) {
        repository.addGeoLocation(geolocation);
        return geolocation;
    }

    @Override
    public List<GeoLocation> findAll() {
        return repository.geoLocations();
    }
}
