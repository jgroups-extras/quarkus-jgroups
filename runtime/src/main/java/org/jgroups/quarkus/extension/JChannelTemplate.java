package org.jgroups.quarkus.extension;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Template;

/**
 * @author Bela Ban
 * @since x.y
 */
@Template
public class JChannelTemplate {

    public void createChannel(BeanContainer container, JChannelConfig config) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        channel.create(config);
    }

    public void connectChannel(BeanContainer container, JChannelConfig cfg) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        channel.connect(cfg.cluster);
    }
}
