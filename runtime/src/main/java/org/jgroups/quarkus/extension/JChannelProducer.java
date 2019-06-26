package org.jgroups.quarkus.extension;

import org.jgroups.JChannel;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 * @author Bela Ban
 * @since x.y
 */
@ApplicationScoped
public class JChannelProducer {
    JChannel channel;

    void create(JChannelConfig cfg) throws Exception {
        channel = new JChannel(cfg.config);
    }

    @Singleton
    @Produces
    public JChannel channel() {
        return channel;
    }

}
