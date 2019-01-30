package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

	public static final Supplier<Tag.TagBuilder> EXPIRY_TAG = () -> Tag.builder().key("expires");
	public static final Supplier<Tag.TagBuilder> BATTERY_TAG = () -> Tag.builder().key("battery");

	@Getter
	private String key;
	private String value;

	public static TagKeyBuilder builder() {
		return new Tag.Builder();
	}

	public Builder toBuilder() {
		return new Tag.Builder().clone(this);
	}
	
	public static interface TagKeyBuilder {
		 TagBuilder key(@NonNull final String key);
	}
	public static interface TagBuilder {
		TagBuilder value(final String value);//can be null
		Tag build();
	}

	public static class Builder implements TagKeyBuilder, TagBuilder {
		
		private final List<Consumer<Tag>> operations = new ArrayList<>();
		
		private Builder() {
		}
		
		public Builder key(@NonNull final String key) {
			operations.add(t -> t.key = key);
			return this;
		}
		public Builder value(final String value) {//can be null
			operations.add(t -> t.value = value);
			return this;
		}

		private Builder clone(Tag tag) {
			this.key(tag.key);
			this.value(tag.value);
			return this;
		}

		public Tag build() {
			Tag tag = new Tag();
			this.operations.forEach(c -> c.accept(tag));
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
