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

    /** bla */
    @ConfigItem(defaultValue = "udp.xml")
    public String config;

}
