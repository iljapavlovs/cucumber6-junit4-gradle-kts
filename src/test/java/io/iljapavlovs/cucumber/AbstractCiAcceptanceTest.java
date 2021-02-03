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

  private static final ServiceEndpoint SERVICE_INTERNAL_ENDPOINT = new ServiceEndpoint("http", "service", 8080);

  @ClassRule
  public static DockerComposeContainer ECOSYSTEM =
      new DockerComposeContainer(locateInClasspath("docker/docker-compose-internal.yml"))
          .withEnv(containerEnvVars())
          .withExposedService(SERVICE_INTERNAL_ENDPOINT.getHost(), 1, SERVICE_INTERNAL_ENDPOINT.getPort(),
              Wait.forHttp("/actuator/health").forStatusCode(200).withStartupTimeout(Duration.ofMinutes(3)))
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
    URL resource = Resources.getResource(pathToFile);
    log.info("{} was found in {}", pathToFile, resource);
    try {
      return new File(resource.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
