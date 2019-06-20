package org.jgroups.quarkus.extension;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jgroups.JChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

@SuppressWarnings("WeakerAccess")
public class InjectionTest {

    @SuppressWarnings("unused")
    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(JChannelBean.class)
                    .addAsResource("application.properties"));

    @Inject
    JChannelBean bean;

    @Test
    public void testInjection() {
        bean.verify();
    }

    @Test
    public void testConnect() throws Exception {
        bean.connect().disconnect();
    }

    @ApplicationScoped
    static class JChannelBean {

        @Inject
        JChannel channel;

        void verify() {
            Assertions.assertNotNull(channel);
            Assertions.assertAll(() -> channel.isOpen(), () -> Assertions.assertNull(channel.getView()));
        }

        JChannelBean connect() throws Exception {
            channel.connect("demo");
            Assertions.assertTrue(channel.isConnected());
            Assertions.assertNotNull(channel.getView());
            return this;
        }

        JChannelBean disconnect() {
            channel.disconnect();
            Assertions.assertFalse(channel.isConnected());
            return this;
        }
    }

}
