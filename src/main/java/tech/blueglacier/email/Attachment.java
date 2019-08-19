package tech.blueglacier.email;

import tech.blueglacier.storage.AbstractStorageProvider;
import tech.blueglacier.storage.MemoryStorageProvider;
import org.apache.james.mime4j.storage.Storage;
import org.apache.james.mime4j.storage.StorageProvider;
import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.IOException;
import java.io.InputStream;

public abstract class Attachment {

    protected BodyDescriptor bd;
    protected String attachmentId;

    public abstract String getAttachmentName();

    public abstract String getAttachmentId();

    public BodyDescriptor getBd() {
        return bd;
    }

    protected Storage storage;

    public InputStream getIs() {
        InputStream is;
        try {
            is = storage.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return is;
    }

    protected int attachmentSize;

    public int getAttachmentSize() {
        return attachmentSize;
    }

    public void setIs(InputStream is) {
        StorageProvider storageProvider = new MemoryStorageProvider();
        try {
            storage = storageProvider.store(is);
            attachmentSize = ((AbstractStorageProvider) storageProvider).getTotalBytesTransffered();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Attachment(BodyDescriptor bd, InputStream is) {
        this.bd = bd;
        attachmentSize = 0;
        setIs(is);
    }

}