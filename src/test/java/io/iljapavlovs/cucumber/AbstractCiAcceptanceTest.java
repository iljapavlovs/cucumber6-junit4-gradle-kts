package io.iljapavlovs.cucumber;


import static com.google.common.collect.Maps.newHashMap;

import com.google.common.io.Resources;
import io.iljapavlovs.cucumber.config.EnvironmentConfig;
import io.iljapavlovs.cucumber.core.config.TestConfigurationProvider;
import io.iljapavlovs.cucumber.utils.DockerSupport;
import io.iljapavlovs.cucumber.utils.ServiceEndpoint;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;

@Slf4j
public abstract class AbstractCiAcceptanceTest {

  private static final ServiceEndpoint SERVICE_INTERNAL_ENDPOINT = new ServiceEndpoint("http", "country-phone", 8080);

  @SuppressWarnings("unchecked")
  @ClassRule
  public static DockerComposeContainer ECOSYSTEM =
          //todo - in order to use docker-compose-internal.yml, we need to get real port from TC,
          // which we get in populateExposedPorts(). However,
          // when environmentConfig.setServicePort(realServicePort(SERVICE_INTERNAL_ENDPOINT))
          // - port is not set with new value!
      new DockerComposeContainer<>(locateInClasspath("docker/docker-compose.yml"))
          .withEnv(containerEnvVars())
          .withExposedService(SERVICE_INTERNAL_ENDPOINT.getHost(), 1, SERVICE_INTERNAL_ENDPOINT.getPort())
          .withLocalCompose(true)
          .withPull(true)
          .withTailChildContainers(true)
          .withLogConsumer(SERVICE_INTERNAL_ENDPOINT.getHost(), new Slf4jLogConsumer(log));


  private static Map<String, String> containerEnvVars() {

    // Configuration is already initiated in Guice at this stage, so it will be replaced if we reset the config here
    Map<String, String> envVars = newHashMap();
    envVars.put("globalHost", DockerSupport.currentHostIpInDocker());
    return envVars;
  }

  @BeforeClass
  public static void populateExposedPorts() {
    EnvironmentConfig environmentConfig = TestConfigurationProvider.fromDefaults(EnvironmentConfig.class, false);

    environmentConfig.setServicePort(realServicePort(SERVICE_INTERNAL_ENDPOINT));

  }

  private static Integer realServicePort(ServiceEndpoint serviceEndpoint) {
    String containerHost = serviceEndpoint.getHost();
    Integer containerPort = serviceEndpoint.getPort();

    Integer servicePort = ECOSYSTEM.getServicePort(containerHost + "_1", containerPort);

    log.info("{} host port is {}", containerHost, servicePort);

    return servicePort;
  }

  private static File locateInClasspath(String pathToFile) {
    URL resource = AbstractCiAcceptanceTest.class.getClassLoader().getResource(pathToFile);
    log.info("{} was found in {}", pathToFile, resource);
    try {
      return new File(resource.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
