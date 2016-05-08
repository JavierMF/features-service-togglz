package org.javiermf.features.togglz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.repository.StateRepository;

import java.util.List;

@Component
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FeaturesServiceStateRepository implements StateRepository {

    @Value("${features.baseurl}")
    String servicesBaseURL;

    @Value("${features.product}")
    String product;

    @Value("${features.configurations.default}")
    String configuration;

    String activeFeaturesPath = "/products/%s/configurations/%s/features";

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    @Autowired
    private OkHttpClient client;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FeatureState getFeatureState(Feature feature) {
        try {
            String url = buildActiveFeaturesURL();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            List<String> activeFeatures = objectMapper.readValue(response.body().string(), new TypeReference<List<String>>() {
            });

            boolean featureActive = activeFeatures.contains(feature.name());
            return new FeatureState(feature, featureActive);
        } catch (Exception e) {
            return new FeatureState(feature, false);
        }
    }

    private String buildActiveFeaturesURL() {
        return String.format(servicesBaseURL + activeFeaturesPath, product, configuration);
    }

    @Override
    public void setFeatureState(FeatureState featureState) {
        throw new UnsupportedOperationException();
    }
}
