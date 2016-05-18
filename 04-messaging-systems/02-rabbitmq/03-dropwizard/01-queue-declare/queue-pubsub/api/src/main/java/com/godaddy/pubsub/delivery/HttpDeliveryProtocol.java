package com.godaddy.pubsub.delivery;

import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.client.ServiceClient;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;

public class HttpDeliveryProtocol {

    // we only use methods that accept the full URI, so we can pass anything here
    private static final ServiceClient client = ServiceClient.createClient("http://localhost/");

    public boolean deliver(Subscription sub, Message msg) {
        Call call = client.deliver(
                sub.getMessageDeliveryUri().toString(),
                sub.getTopicId(),
                sub.getSubscriptionId(),
                msg.getTimestamp(),
                msg.getMessageId(),
                msg.getRequestId(),
                RequestBody.create(MediaType.parse(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM), msg.getData()));

        try {
            Response response = call.execute();
            if ( 200 <= response.code() && response.code() <= 299 ) {
                return true;
            }
        } catch (IOException ex) {
            ; // default false
        }

        return false ;
    }
}

