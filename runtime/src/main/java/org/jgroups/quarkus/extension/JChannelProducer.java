package org.jgroups.quarkus.extension;

import org.jgroups.JChannel;
import org.jgroups.protocols.TP;
import org.jgroups.stack.DiagnosticsHandler;
import org.jgroups.stack.NonReflectiveProbeHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Bela Ban
 * @since x.y
 */
@ApplicationScoped
public class JChannelProducer {
    JChannel channel;
    DiagnosticsHandler.ProbeHandler handler;

    void create(JChannelConfig cfg) throws Exception {
        channel = new JChannel(cfg.config);

        // Register a ProbeHandler that doesn't use reflection for key="jmx"
        handler = new NonReflectiveProbeHandler(channel).initialize(channel.getProtocolStack().getProtocols());
    }

    @Singleton
    @Produces
    public JChannel channel() {
        return channel;
    }

    @Singleton
    public void connect(String cluster) throws Exception {
        channel.connect(cluster);

        // Remove the default ProbeHandler for "jmx" which uses reflection and replace it with one
        // (NonReflectiveProbeHandler) that doesn't use reflection
        TP transport = channel.getProtocolStack().getTransport();
        DiagnosticsHandler diag_handler = transport.getDiagnosticsHandler();
        if (diag_handler != null) {
            Set<DiagnosticsHandler.ProbeHandler> probe_handlers = diag_handler.getProbeHandlers();
            probe_handlers.removeIf(probe_handler -> {
                String[] keys = probe_handler.supportedKeys();
                return keys != null && Stream.of(keys).anyMatch(s -> s.startsWith("jmx"));
            });
        }
        channel.getProtocolStack().getTransport().registerProbeHandler(handler);
    }

}
