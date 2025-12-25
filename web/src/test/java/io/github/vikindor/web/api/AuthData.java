package io.github.vikindor.web.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthData {

    private final String email;
    private final String password;

    @JsonProperty("permanent_login")
    private final boolean permanentLogin = true;

    @JsonProperty("web_session")
    private final boolean webSession = true;

    @JsonProperty("pkce_oauth")
    private final String pkceOauth = null;

    @JsonProperty("device_id")
    private final String deviceId;

    public AuthData(String email, String password, String deviceId) {
        this.email = email;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPermanentLogin() {
        return permanentLogin;
    }

    public boolean isWebSession() {
        return webSession;
    }

    public String getPkceOauth() {
        return pkceOauth;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
