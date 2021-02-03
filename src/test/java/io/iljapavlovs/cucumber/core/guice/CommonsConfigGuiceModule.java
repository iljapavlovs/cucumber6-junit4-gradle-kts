package io.iljapavlovs.cucumber.core.guice;


import io.iljapavlovs.cucumber.core.config.TestConfiguration;
import io.iljapavlovs.cucumber.core.config.TestConfigurationProvider;

public final class CommonsConfigGuiceModule<ENVCONFIG extends TestConfiguration> extends
    AbstractConfigGuiceModule<ENVCONFIG> {

  public CommonsConfigGuiceModule(Class<ENVCONFIG> configClass) {
    super(configClass);
  }

  @Override
  ENVCONFIG createConfigInstance() {
    return TestConfigurationProvider.fromDefaults(configClass, true);
  }

}
