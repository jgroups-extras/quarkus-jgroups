package io.quarkus.jgroups;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.jgroups.JChannel;

/**
 * @author Bela Ban
 * @since x.y
 */
@ApplicationScoped
public class JChannelProducer {
    JChannel channel;

    void create(String config) throws Exception {
        channel = new JChannel(config);
    }

    @Singleton
    @Produces
    public JChannel channel() {
        return channel;
    }

}
