package org.jgroups.quarkus.extension;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Template;
import org.jboss.logging.Logger;
import org.jgroups.protocols.TCPPING;
import org.jgroups.protocols.TP;
import org.jgroups.util.Util;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author Bela Ban
 * @since x.y
 */
@Template
public class JChannelTemplate {
    static final Logger log = Logger.getLogger(JChannelTemplate.class);

    public void createChannel(BeanContainer container, JChannelConfig config) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        log.debugf("creating channel based on config %s", config);
        channel.create(config);

        TP transport = channel.channel().getProtocolStack().getTransport();
        if (config.bind_addr != null && !config.bind_addr.isEmpty()) {
            InetAddress new_bind_addr = Util.getAddress(config.bind_addr, Util.getIpStackType());
            log.debugf("changed %s.bind_addr from %s to %s", transport.getClass().getSimpleName(),
                    transport.getBindAddress(), new_bind_addr);
            transport.setBindAddress(new_bind_addr);
        }

        if (config.initial_hosts != null && !config.initial_hosts.isEmpty()) {
            TCPPING tcpping = channel.channel().getProtocolStack().findProtocol(TCPPING.class);
            if (tcpping != null) {
                int port_range = tcpping.getPortRange();
                List<InetSocketAddress> initial_hosts = Util.parseCommaDelimitedHosts2(config.initial_hosts, port_range);
                log.debugf("set TCPPING.initial_hosts to %s", initial_hosts);
                tcpping.setInitialHosts(initial_hosts);
            }
        }

    }

    public void connectChannel(BeanContainer container, JChannelConfig cfg) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        channel.connect(cfg.cluster);
    }
}
