package io.quarkus.jgroups.deployment;

/**
 * @author Bela Ban
 */
public class JGroupsProcessor {
    /*@BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    JChannelBuildItem build(BuildProducer<FeatureBuildItem> feature, JChannelConfigTemplate template, VertxBuildItem vertx,
                            BeanContainerBuildItem beanContainer, LaunchModeBuildItem launchMode, ShutdownContextBuildItem shutdown,
                            MailConfig config) {

        feature.produce(new FeatureBuildItem(FeatureBuildItem.MAILER));

        RuntimeValue<MailClient> client=template.configureTheClient(vertx.getVertx(), beanContainer.getValue(), config,
                                                                    launchMode.getLaunchMode(), shutdown);

        template.configureTheMailer(beanContainer.getValue(), config);

        return new MailerBuildItem(client);
    }*/
}
