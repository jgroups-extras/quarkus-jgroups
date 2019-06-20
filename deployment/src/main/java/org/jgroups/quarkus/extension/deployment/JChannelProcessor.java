package org.jgroups.quarkus.extension.deployment;

import org.jgroups.quarkus.extension.JChannelConfig;
import org.jgroups.quarkus.extension.JChannelProducer;
import org.jgroups.quarkus.extension.JChannelTemplate;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

/**
 * @author Bela Ban
 */
public class JChannelProcessor {

    @BuildStep
    AdditionalBeanBuildItem registerClients() {
        return AdditionalBeanBuildItem.unremovableOf(JChannelProducer.class);
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    void build(BuildProducer<FeatureBuildItem> feature, JChannelTemplate template,
            BeanContainerBuildItem beanContainer, JChannelConfig config) throws Exception {

        feature.produce(new FeatureBuildItem("jgroups-channel"));

        template.configure(beanContainer.getValue(), config);
    }
}
