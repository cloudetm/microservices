package com.godaddy.pubsub.delivery;

import com.godaddy.pubsub.delivery.interfaces.DeliveryProtocol;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.client.ServiceClient;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;

public class HttpDeliveryProtocol implements DeliveryProtocol {

    private static final int ACKED_STATUS_CODE_MIN = 200;
    private static final int ACKED_STATUS_CODE_MAX = 299;

    private static final ServiceClient ClientInstance = ServiceClient.createClient("http://localhost/");

    @Override
    public boolean deliver(Subscription sub, Message msg) {
        final Call call = ClientInstance.deliver(
                sub.getMessageDeliveryUri().toString(),
                sub.getTopicId(),
                sub.getSubscriptionId(),
                msg.getTimestamp(),
                msg.getMessageId(),
                msg.getRequestId(),
                RequestBody.create(MediaType.parse(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM), msg.getData()));

        try {
            final Response response = call.execute();
            if(ACKED_STATUS_CODE_MIN <= response.code() && response.code() <= ACKED_STATUS_CODE_MAX){
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
