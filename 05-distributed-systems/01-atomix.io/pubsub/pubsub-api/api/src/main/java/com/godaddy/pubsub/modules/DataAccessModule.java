package com.godaddy.pubsub.modules;

import com.google.inject.AbstractModule;
import com.godaddy.pubsub.dataAccess.EchoRepo;
import com.godaddy.pubsub.dataAccess.interfaces.DbRepo;

public class DataAccessModule extends AbstractModule {

    @Override protected void configure() {
        bind(DbRepo.class).to(EchoRepo.class);
    }
}
