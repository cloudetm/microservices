package com.company.app;

import com.company.app.resource.ContactResource;
import com.company.app.resource.GeoLocationResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {

    public static void main(String... args) throws Exception {
        new App().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactResource());
        e.jersey().register(new GeoLocationResource());
    }

}
