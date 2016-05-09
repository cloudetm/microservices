package com.godaddy.pubsub.componentTests;

import ch.qos.logback.classic.Level;
import com.godaddy.logging.Logger;
import com.godaddy.logging.LoggingConfigs;
import com.godaddy.pubsub.componentTests.server.SelfHostServer;
import com.godaddy.pubsub.pub.client.ServiceClient;
import io.dropwizard.logging.BootstrapLogging;
import io.paradoxical.common.valuetypes.ValueTypeWrapper;
import lombok.AccessLevel;
import lombok.Getter;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.net.URI;
import java.util.*;

import static com.godaddy.logging.LoggerFactory.getLogger;

class TestBase {
    @Getter(AccessLevel.PROTECTED)
    private final SelfHostServer server;

    public TestBase() {
        this.server = new SelfHostServer(Collections.emptyList());
    }

    private static final Logger logger = getLogger(TestBase.class);

    protected static PodamFactory fixture = new PodamFactoryImpl();

    protected final static Random random = new Random();

    static {
        LoggingConfigs.getCurrent()
                      .addOverride(URI.class, URI::toString)
                      .addOverride(ValueTypeWrapper.class, ValueTypeWrapper::toString)
                      .addOverride(DateTime.class, DateTime::toString)
                      .addOverride(Collection.class, c -> c.size() > 20 ? String.valueOf(c.size()) : c.toString())
                      .addOverride(Map.class, c -> c.size() > 20 ? String.valueOf(c.size()) : c.toString())
                      .addOverride(Class.class, Class::toString);


        final String environmentLogLevel = System.getenv("LOG_LEVEL");

        BootstrapLogging.bootstrap(environmentLogLevel != null ? Level.toLevel(environmentLogLevel) : Level.ERROR);

        String[] disableLogging = new String[]{ "uk.co.jemos.podam",
                                                "com.datastax",
                                                "org.cassandraunit",
                                                "io.netty",
                                                "com.netflix.governator",
                                                "com.hazelcast.nio",
                                                "org.glassfish",
                                                "org.apache"
        };

        Arrays.stream(disableLogging).forEach(i -> {
            ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(i)).setLevel(Level.OFF);
        });
    }

    @Before
    public void startServer() {
        server.start();
    }

    @After
    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            logger.error(e, "Error stopping server");
        }
    }

    protected ServiceClient createServiceClient() {
        return ServiceClient.createClient(server.getBaseUri().toString());
    }
}
