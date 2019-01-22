package org.bspv.pharma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.NonNull;

public final class Tag implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 5588678864664858346L;

	public static final Supplier<Tag.Builder> EXPIRY_TAG = () -> Tag.builder().key("expire");
	public static final Supplier<Tag.Builder> BATTERY_TAG = () -> Tag.builder().key("battery");

	@Getter
	private String key;
	private String value;

	public static Builder builder() {
		return new Tag.Builder();
	}

	public Builder toBuilder() {
		return new Tag.Builder().clone(this);
	}

	public static class Builder {
		
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

		public Builder clone(Tag tag) {
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
