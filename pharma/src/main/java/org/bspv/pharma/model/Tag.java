package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

	public static final Supplier<Tag.TagCreatedByBuilder> EXPIRY_TAG = () -> Tag.builder().key("expires");
	public static final Supplier<Tag.TagCreatedByBuilder> BATTERY_TAG = () -> Tag.builder().key("battery");

	@Getter
	private UUID createdBy;
	@Getter
	private LocalDateTime createdDate;
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
		TagBuilder value(final String value);//can be null
		TagBuilder createdDate(@NonNull final LocalDateTime createdDate);
		Tag build();
	}

	public static class Builder implements TagKeyBuilder, TagCreatedByBuilder, TagBuilder {
		
		private final List<Consumer<Tag>> operations = new ArrayList<>();
		
		private Builder() {
		}
		
		@Override
		public Builder key(@NonNull final String key) {
			operations.add(t -> t.key = key);
			return this;
		}
		
		@Override
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			operations.add(t -> t.createdDate = createdDate);
			return this;
		}

		@Override
		public Builder createdBy(@NonNull UUID createdBy) {
			operations.add(t -> t.createdBy = createdBy);
			return this;
		}

		@Override
		public Builder value(final String value) {//can be null
			operations.add(t -> t.value = value);
			return this;
		}

		private Builder clone(Tag tag) {
			this.key(tag.key);
			this.value(tag.value);
			this.createdBy(tag.createdBy);
			this.createdDate(tag.createdDate);
			return this;
		}

		@Override
		public Tag build() {
			Tag tag = new Tag();
			this.operations.forEach(c -> c.accept(tag));
			// handle default values
			tag.createdDate = (tag.createdDate == null) ? LocalDateTime.now() : tag.createdDate;
			return tag;
		}

	}

	private Tag() {
		// use builder instead
	}

	public Optional<String> getValue() {
		return Optional.ofNullable(value);
	}

}
