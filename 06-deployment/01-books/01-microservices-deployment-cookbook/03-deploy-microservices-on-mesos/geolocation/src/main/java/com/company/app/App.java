package com.company.app;

import com.company.app.resource.ContactResource;
import com.company.app.resource.GeoLocationResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

import java.io.File;
import java.io.IOException;

class User{
    private String name;
    private Integer age;

    // empty constructor is required for convert json to object
    public User(){}

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

public class App extends Application<Configuration> {

    private static ObjectMapper MAPPER = new ObjectMapper();
    private static Integer id = 8;

    public static void main(String... args) throws Exception {
        User user = new User("Tom", 28);
        try {
            MAPPER.writeValue(new File("user_" + id), user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new App().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactResource());
        e.jersey().register(new GeoLocationResource());
    }

}
