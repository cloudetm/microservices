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

    private static final int MaxDescriptionLength = 255;

    @ApiModelProperty(example = "occurs each time on an order is placed")
    @Length(min = 2, max = MaxDescriptionLength)
    private final String description;
}
