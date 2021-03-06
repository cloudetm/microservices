package com.godaddy.pubsub.modules;

import com.google.inject.Module;

import java.util.Arrays;
import java.util.List;

public class DefaultApplicationModules {
    public static List<Module> getModules() {
        return Arrays.asList(
                new DataAccessModule(),
                new DeliveryModule(),
                new JsonMapperModule());
    }
}
