package com.company.app.resource;

import com.company.app.model.GeoLocation;
import com.company.app.service.GeoLocationService;
import com.company.app.service.GeoLocationServiceImpl;

import javax.ws.rs.*;
import java.util.List;

@Path("/geolocation")
public class GeoLocationResource {

    private GeoLocationService service = new GeoLocationServiceImpl();

    @GET
    @Produces("application/json")
    public List<GeoLocation> findAll() {
        return service.findAll();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public GeoLocation create(GeoLocation geolocation) {
        return service.create(geolocation);
    }

}
