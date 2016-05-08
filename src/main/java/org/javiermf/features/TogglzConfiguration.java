package org.javiermf.features;

import okhttp3.OkHttpClient;
import org.javiermf.features.togglz.MyFeatures;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@Configuration
public class TogglzConfiguration {


    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
