package io.quarkus.jgroups;

import io.quarkus.test.QuarkusUnitTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jgroups.JChannel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@SuppressWarnings("WeakerAccess")
public class InjectionTest {

    @SuppressWarnings("unused")
    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(BeanUsingJChannel.class)
                    .addAsResource("application.properties"));

    @Inject
    BeanUsingJChannel beanUsingBare;

    @Test
    public void testInjection() {
        beanUsingBare.verify();
    }

    @ApplicationScoped
    static class BeanUsingJChannel {

        @Inject
        JChannel channel;

        void verify() {
            Assertions.assertNotNull(channel);
            assert channel.getView() == null;
        }
    }

}
