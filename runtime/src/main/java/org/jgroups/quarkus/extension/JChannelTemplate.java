package org.jgroups.quarkus.extension;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Template;

/**
 * @author Bela Ban
 * @since x.y
 */
@Template
public class JChannelTemplate {

    public void configure(BeanContainer container, JChannelConfig config) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        channel.create(config);
    }

}
