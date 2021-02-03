package io.iljapavlovs.cucumber.utils;

import static java.lang.String.format;

public class ServiceEndpoint {

    private final String host;

    private final int port;

    private final String scheme;

    public ServiceEndpoint(String scheme, String host, int port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getScheme() {
        return scheme;
    }


    public String getBaseUrl() {
        return format("%s://%s:%s/", getScheme(), getHost(), getPort());
    }

}
