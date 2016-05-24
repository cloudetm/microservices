package com.godaddy.pubsub.pub.model.topics;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Value
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class TopicNameDescriptor {
    @ApiModelProperty(example = "ecomm", required = true)
    @Length(min = 2, max = 100)
    private final String team;

    @ApiModelProperty(example = "fulfillment", required = true)
    @Length(min = 2, max = 100)
    private final String project;

    @ApiModelProperty(example = "receipt", required = false)
    @Length(min = 2, max = 100)
    private final String entity;

    @ApiModelProperty(example = "created", required = true)
    @Length(min = 2, max = 100)
    private final String eventType;

    @ApiModelProperty(required = true)
    @Range(min = 0, message = "Version should start at 1")
    private final int majorVersion;
}
