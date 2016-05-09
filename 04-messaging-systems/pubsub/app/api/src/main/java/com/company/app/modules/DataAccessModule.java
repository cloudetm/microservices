package com.company.app.modules;

import com.google.inject.AbstractModule;
import com.company.app.dataAccess.EchoRepo;
import com.company.app.dataAccess.interfaces.DbRepo;

public class DataAccessModule extends AbstractModule {

    @Override protected void configure() {
        bind(DbRepo.class).to(EchoRepo.class);
    }
}
