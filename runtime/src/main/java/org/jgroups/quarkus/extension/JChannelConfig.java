package org.jgroups.quarkus.extension;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

/**
 * @author Bela Ban
 * @since x.y
 */
@ConfigRoot(name = "channel", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class JChannelConfig {

    /** If no configuration file is defined in application.properties, this will be used */
    @ConfigItem(defaultValue = "tcp.xml")
    public String config;

    /** If defined, this overrides the value defined in the config above (e.g. TCP.bind_addr) */
    @ConfigItem
    public String bind_addr;

    /** If defined, this overrides the value defined in the config above (TCPPING.initial_hosts) */
    @ConfigItem
    public String initial_hosts;

    /** The name of the cluster to join */
    @ConfigItem(defaultValue = "quarkus-jgroups-chat")
    public String cluster;

}
