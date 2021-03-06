package io.iljapavlovs.cucumber.core.guice;

import com.google.inject.AbstractModule;
import io.iljapavlovs.cucumber.core.config.GlobalTestSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractConfigGuiceModule<ENVCONFIG> extends AbstractModule {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfigGuiceModule.class);

    protected final Class<ENVCONFIG> configClass;

    public AbstractConfigGuiceModule(Class<ENVCONFIG> configClass) {
        this.configClass = configClass;
    }

    @Override
    public void configure() {
        bind(configClass).toProvider(() -> {
            String environment = GlobalTestSettings.getEnvironment();

            LOGGER.trace("Configuration environment is set to : {}", environment);
            return createConfigInstance();
        }).asEagerSingleton();
    }

    abstract ENVCONFIG createConfigInstance();

}
