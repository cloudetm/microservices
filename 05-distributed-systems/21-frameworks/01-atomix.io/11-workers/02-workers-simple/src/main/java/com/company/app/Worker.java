package com.company.app;

import io.atomix.group.LocalMember;

public class Worker{
    public void doWork(Object message, LocalMember member){
        System.out.println("Worker.doWork: '" + message + "' is from member-" + member.id());
    }
}
