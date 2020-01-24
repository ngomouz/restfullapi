package com.grentechs.cogigroup.configurations;

import io.micrometer.appoptics.AppOpticsConfig;
import io.micrometer.appoptics.AppOpticsMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.lang.Nullable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfiguration {

    AppOpticsConfig config = new AppOpticsConfig() {
        @Override
        @Nullable
        public String get(String token) {
            return null;
        }

        @Override
        public String apiToken() {
            return "_Oq-cOe6qZNqgXnb8SyMcg12Ut7FZ8vS1Xrh65OwMiSHZnOFsm-8IfHwQ_-eLX_POg-sV_s";
        }
    };
    MeterRegistry registry = new AppOpticsMeterRegistry(config, Clock.SYSTEM);
}
