package com.company.app.service;

import com.company.app.model.GeoLocation;

import java.util.List;

public interface GeoLocationService {
    public GeoLocation create(GeoLocation geolocation);
    public List<GeoLocation> findAll();
}
