package com.godaddy.pubsub;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.godaddy.pubsub.configurations.RabbitMqConfiguration;
import io.dropwizard.Configuration;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ServiceConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("rmq")
    private RabbitMqConfiguration rabbitMqConfiguration = new RabbitMqConfiguration();
}
