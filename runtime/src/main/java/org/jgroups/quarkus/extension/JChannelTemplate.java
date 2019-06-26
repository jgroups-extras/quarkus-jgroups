package org.jgroups.quarkus.extension;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

import org.jgroups.protocols.TCPPING;
import org.jgroups.protocols.TP;
import org.jgroups.util.Util;

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

        TP transport = channel.channel().getProtocolStack().getTransport();
        if (config.bind_addr != null && !config.bind_addr.isEmpty()) {
            InetAddress new_bind_addr = Util.getAddress(config.bind_addr, Util.getIpStackType());
            transport.setBindAddress(new_bind_addr);
        }

        if (config.initial_hosts != null && !config.initial_hosts.isEmpty()) {
            TCPPING tcpping = channel.channel().getProtocolStack().findProtocol(TCPPING.class);
            if (tcpping != null) {
                int port_range = tcpping.getPortRange();
                List<InetSocketAddress> initial_hosts = Util.parseCommaDelimitedHosts2(config.initial_hosts, port_range);
                tcpping.setInitialHosts(initial_hosts);
            }
        }

    }

    public void connectChannel(BeanContainer container, JChannelConfig cfg) throws Exception {
        JChannelProducer channel = container.instance(JChannelProducer.class);
        channel.connect(cfg.cluster);
    }
}
