package io.quarkus.jgroups.deployment;

import io.quarkus.builder.item.SimpleBuildItem;
import io.quarkus.runtime.RuntimeValue;
import org.jgroups.JChannel;

/**
 * @author Bela Ban
 */
public final class JChannelBuildItem extends SimpleBuildItem {
    private final RuntimeValue<JChannel> channel;

    public JChannelBuildItem(RuntimeValue<JChannel> channel) {
        this.channel=channel;
    }

    public RuntimeValue<JChannel> getChannel() {
        return channel;
    }
}
