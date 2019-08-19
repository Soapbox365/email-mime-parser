package tech.blueglacier.storage;

import org.apache.james.mime4j.storage.Storage;

public interface MemoryStorage extends Storage {

    byte[] getData();

}
