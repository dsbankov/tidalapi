package com.hadas.krzysztof.utils;

import com.hadas.krzysztof.session.Session;
import com.owlike.genson.Genson;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RestHelperTest {

    @Test
    public void gensonDeserialize() {
        Genson genson = new Genson();
        String json = "{\n" +
                "    \"user\": {\n" +
                "        \"userId\": 184943748,\n" +
                "        \"email\": null,\n" +
                "        \"countryCode\": \"BG\",\n" +
                "        \"fullName\": null,\n" +
                "        \"firstName\": null,\n" +
                "        \"lastName\": null,\n" +
                "        \"nickname\": null,\n" +
                "        \"username\": \"evgeorgiev9@gmail.com\",\n" +
                "        \"address\": null,\n" +
                "        \"city\": null,\n" +
                "        \"postalcode\": null,\n" +
                "        \"usState\": null,\n" +
                "        \"phoneNumber\": null,\n" +
                "        \"birthday\": null,\n" +
                "        \"gender\": null,\n" +
                "        \"imageId\": null,\n" +
                "        \"channelId\": 135,\n" +
                "        \"parentId\": 0,\n" +
                "        \"acceptedEULA\": true,\n" +
                "        \"created\": 1642954984222,\n" +
                "        \"updated\": 1659280568890,\n" +
                "        \"facebookUid\": 0,\n" +
                "        \"appleUid\": null,\n" +
                "        \"googleUid\": null,\n" +
                "        \"emailVerified\": false,\n" +
                "        \"newUser\": false\n" +
                "    },\n" +
                "    \"token_type\": \"Bearer\",\n" +
                "    \"access_token\": \"ACCESS_TOKEN\",\n" +
                "    \"expires_in\": 604800\n" +
                "}";
        Session deserialize = genson.deserialize(json, Session.class);
        assertEquals("184943748", deserialize.getUserId());
        assertEquals("BG", deserialize.getCountryCode());
        assertEquals("ACCESS_TOKEN", deserialize.getAccessToken());
        assertEquals((Long) 604800L, deserialize.getExpiresIn());
    }

}