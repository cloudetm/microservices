package com.godaddy.pubsub.application.lifecycle;

import com.godaddy.logging.Logger;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.lifecycle.Managed;
import lombok.AllArgsConstructor;

import static com.godaddy.logging.LoggerFactory.getLogger;


@Singleton
@SuppressWarnings("unused")
@AllArgsConstructor(onConstructor = @__(@Inject))
public class ApplicationLifeCycle implements Managed {
    private static final Logger logger = getLogger(ApplicationLifeCycle.class);

    private final MessageRepository messageRepository;

    public void start() {
        messageRepository.init();
    }

    public void stop() throws Exception {
        messageRepository.cleanup();
        logger.dashboard("STOPPED core app logic");
    }
}
