package tech.blueglacier.email;

import tech.blueglacier.storage.AbstractStorageProvider;
import tech.blueglacier.storage.MemoryStorage;
import tech.blueglacier.storage.MemoryStorageProvider;
import org.apache.james.mime4j.storage.Storage;
import org.apache.james.mime4j.storage.StorageProvider;
import org.apache.james.mime4j.stream.BodyDescriptor;

import java.io.IOException;
import java.io.InputStream;

public abstract class Attachment {

    protected BodyDescriptor bd;
    protected String attachmentId;

    private  boolean inHtmlBody;

    public abstract String getAttachmentName();

    public String getAttachmentId() {
        return getAttachmentName();
    }

    public BodyDescriptor getBd() {
        return bd;
    }

    private Storage storage;

    public InputStream getIs() {
        InputStream is;
        try {
            is = storage.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return is;
    }

    private int attachmentSize;

    public int getAttachmentSize() {
        return attachmentSize;
    }

    public byte[] getData() {
        return ((MemoryStorage) storage).getData();
    }

    public void deleteData() {
        storage.delete();
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

    public boolean isInHtmlBody() {
        return inHtmlBody;
    }

    public void setInHtmlBody(boolean inHtmlBody) {
        this.inHtmlBody = inHtmlBody;
    }

    public Attachment(BodyDescriptor bd) {
        this(bd, null);
    }

    public Attachment(BodyDescriptor bd, InputStream is) {
        this.bd = bd;
        attachmentSize = 0;
        if (is != null) {
            setIs(is);
        }
    }

}