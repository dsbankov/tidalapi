package com.hadas.krzysztof.session;

import com.hadas.krzysztof.utils.RestHelper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.owlike.genson.annotation.JsonProperty;
import org.apache.commons.codec.binary.Base64;

public class Session {

    private static final String AUTH_URL = "https://auth.tidal.com/v1/oauth2/token/";
    private static final String API_URL = "https://api.tidal.com/v1/";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_AUTH_PREFIX = "Bearer ";
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final String COUNTRY_CODE_QUERY_PARAM = "countryCode";
    private static final String LIMIT_QUERY_PARAM = "limit";

    private User user;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expires_in")
    private Long expiresIn;

    public static Session login(String clientId, String clientSecret, String refreshToken) {
        RestHelper restHelper = new RestHelper();
        HttpResponse<JsonNode> jsonResponse = restHelper.executeRequest(Unirest.post(AUTH_URL)
                .header(AUTH_HEADER, BASIC_AUTH_PREFIX + Base64.encodeBase64String((clientId + ":" + clientSecret).getBytes()))
                .field("client_id", clientId)
                .field("refresh_token", refreshToken)
                .field("grant_type", "refresh_token")
                .field("scope", "r_usr+w_usr+w_sub"));
        Session session = restHelper.checkAndDeserialize(jsonResponse, Session.class);
        session.setRefreshToken(refreshToken);
        return session;
    }

    public HttpRequest get(String url) {
        return Unirest.get(API_URL + url)
                .header(AUTH_HEADER, BEARER_AUTH_PREFIX + accessToken)
                .queryString(LIMIT_QUERY_PARAM, 100) // TODO should be configurable
                .queryString(COUNTRY_CODE_QUERY_PARAM, getCountryCode());
    }

    public HttpRequest delete(String url) {
        return Unirest.delete(API_URL + url)
                .header(AUTH_HEADER, BEARER_AUTH_PREFIX + accessToken)
                .queryString(COUNTRY_CODE_QUERY_PARAM, getCountryCode());
    }

    public HttpRequestWithBody post(String url) {
        return Unirest.post(API_URL + url)
                .header(AUTH_HEADER, BEARER_AUTH_PREFIX + accessToken)
                .queryString(COUNTRY_CODE_QUERY_PARAM, getCountryCode());
    }

    public String getCountryCode() {
        return user.getCountryCode();
    }

    public void setCountryCode(String countryCode) {
        user.setCountryCode(countryCode);
    }

    public String getUserId() {
        return user.getUserId();
    }

    public void setUserId(String userId) {
        user.setUserId(userId);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public static class User {
        private String userId;
        private String countryCode;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
    }
}
