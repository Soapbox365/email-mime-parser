package tech.blueglacier.email;

import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.InputStream;

public interface EmailAttachmentFactory {

    EmailAttachment create(BodyDescriptor bd, InputStream is);

}
