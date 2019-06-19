package io.quarkus.jgroups.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.jgroups.JChannelConfig;
import io.quarkus.jgroups.JChannelProducer;
import io.quarkus.jgroups.JChannelTemplate;

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
