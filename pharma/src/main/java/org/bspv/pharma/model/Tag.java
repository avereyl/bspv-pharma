package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = { "key", "value" })
public final class Tag implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 5588678864664858346L;

    public static final Supplier<Tag.TagCreatedByBuilder> EXPIRY_TAG_BUILDER_FACTORY = () -> Tag.builder()
            .key("expires");
    public static final Supplier<Tag.TagCreatedByBuilder> BATTERY_TAG_BUILDER_FACTORY = () -> Tag.builder()
            .key("battery");

    @Getter
    private UUID createdBy;
    @Getter
    private OffsetDateTime createdDate;
    @Getter
    private String key;
    private String value;// might be null

    public static TagKeyBuilder builder() {
        return new Tag.Builder();
    }

    public Builder toBuilder() {
        return new Tag.Builder().clone(this);
    }

    public static interface TagKeyBuilder {
        TagCreatedByBuilder key(@NonNull final String key);
    }

    public static interface TagCreatedByBuilder {
        TagBuilder createdBy(@NonNull final UUID createdBy);
    }

    public static interface TagBuilder {
        TagBuilder value(final String value);// can be null

        TagBuilder createdDate(@NonNull final OffsetDateTime createdDate);

        Tag build();
    }

    public static class Builder implements TagKeyBuilder, TagCreatedByBuilder, TagBuilder {

        private final List<Consumer<Tag>> operations = new ArrayList<>();

        private Builder() {
        }

        @Override
        public Builder key(@NonNull final String key) {
            this.operations.add(t -> t.key = key);
            return this;
        }

        @Override
        public Builder createdDate(@NonNull final OffsetDateTime createdDate) {
            this.operations.add(t -> t.createdDate = createdDate);
            return this;
        }

        @Override
        public Builder createdBy(@NonNull final UUID createdBy) {
            this.operations.add(t -> t.createdBy = createdBy);
            return this;
        }

        @Override
        public Builder value(final String value) {// can be null
            this.operations.add(t -> t.value = value);
            return this;
        }

        private Builder clone(final Tag tag) {
            this.key(tag.key);
            this.value(tag.value);
            this.createdBy(tag.createdBy);
            this.createdDate(tag.createdDate);
            return this;
        }

        @Override
        public Tag build() {
            final Tag tag = new Tag();
            this.operations.forEach(c -> c.accept(tag));
            // handle default values
            tag.createdDate = (tag.createdDate == null) ? OffsetDateTime.now() : tag.createdDate;
            return tag;
        }

    }

    private Tag() {
        // use builder instead
    }

    public Optional<String> getValue() {
        return Optional.ofNullable(this.value);
    }

}
