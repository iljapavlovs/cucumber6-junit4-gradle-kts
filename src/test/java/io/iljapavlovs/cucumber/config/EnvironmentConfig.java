package io.iljapavlovs.cucumber.config;


import io.iljapavlovs.cucumber.core.config.TestConfiguration;
import io.iljapavlovs.cucumber.utils.DockerSupport;
import javax.inject.Singleton;

@Singleton
public class EnvironmentConfig extends TestConfiguration {
  private static final String SERVICE_HOST = "service.host";
  private static final String SERVICE_PORT = "service.port";

  public String getServiceHost() {
    return getString(SERVICE_HOST );
  }

  public void setServiceHost(Integer host) {
    setProperty(SERVICE_HOST, host);
  }

  public Integer getServicePort() {
    return getInteger(SERVICE_PORT, null );
  }

  public void setServicePort(Integer port) {
    setProperty(SERVICE_PORT, port);
  }

}


