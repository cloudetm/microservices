package com.company.app.dataAccess;

import com.company.app.dataAccess.interfaces.DbRepo;

public class EchoRepo implements DbRepo {
    @Override public String echo(final String data) {
        return data;
    }
}
