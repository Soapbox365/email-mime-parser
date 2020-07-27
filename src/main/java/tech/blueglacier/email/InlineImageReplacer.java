package tech.blueglacier.email;

public interface InlineImageReplacer {

    String getSrcURL(Attachment attachment);

    boolean removeFromAttachmentList(Attachment attachment);
}
