package org.javiermf.features.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeatures implements Feature {

    @Label("First Feature")
    ONLINE_FORUM,

    @Label("Mailing List")
    MAILING_LIST,

    @Label("Second Feature")
    IN_TRIAL_PERIOD;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}

