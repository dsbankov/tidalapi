package com.hadas.krzysztof;

import com.hadas.krzysztof.exceptions.HttpBadResponseException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class TidalApiImplTest {

    private static final String BAD_CLIENT_ID = "badclientid";
    private static final String BAD_CLIENT_SECRET = "badclientsecret";
    private static final String BAD_REFRESH_TOKEN = "badrefreshtoken";

    private TidalApiImpl tidalApiImpl = new TidalApiImpl();

    @Test(expected = HttpBadResponseException.class)
    public void shouldThrowExcOnUnsuccessfulLogin() throws UnirestException {
        tidalApiImpl.login(BAD_CLIENT_ID, BAD_CLIENT_SECRET, BAD_REFRESH_TOKEN);
    }
}