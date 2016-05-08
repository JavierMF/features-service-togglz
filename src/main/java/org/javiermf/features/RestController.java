package org.javiermf.features;

import org.javiermf.features.togglz.MyFeatures;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierm on 8/5/16.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {


    @RequestMapping("/")
    public List<String> test() {

        List<String> activedFeatures = new ArrayList<String>();

        for (MyFeatures theFeature : MyFeatures.values()) {
            if (theFeature.isActive()) {
                activedFeatures.add(theFeature.name());
            }
        }

        return activedFeatures;
    }
}
