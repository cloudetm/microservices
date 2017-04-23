package com.godaddy.pubsub.pub.model.topics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Value
@ApiModel
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Topic {

    @ApiModelProperty(required = true)
    @NotNull
    private final TopicNameDescriptor nameDescriptor;

    @ApiModelProperty(example = "ecomm.fulfillment.receipt.created.v1", readOnly = true)
    private final TopicId topicId;

    @ApiModelProperty(example = "occurs each time an order is placed")
    @Length(min=2, max=255)
    private final String description;
}
