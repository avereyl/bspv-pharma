package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

/**
 * Additional details for a location. Allow a user to store some more
 * information for this location.
 * 
 * @author guillaume
 *
 */
public final class AdditionalDetails implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = -4443763633078637155L;

	@Getter
	private String key;
	private String value;
	private LocalDateTime valueDate;

	public static Builder builder() {
		return new AdditionalDetails.Builder();
	}

	public Builder toBuilder() {
		return new AdditionalDetails.Builder().clone(this);
	}

	public static class Builder {

		private final List<Consumer<AdditionalDetails>> operations = new ArrayList<>();

		private Builder() {
		}

		public Builder key(@NonNull final String key) {
			operations.add(ad -> ad.key = key);
			return this;
		}

		public Builder value(final String value) {// can be null
			operations.add(ad -> ad.value = value);
			return this;
		}

		private Builder valueDate(LocalDateTime valueDate) {
			operations.add(ad -> ad.valueDate = valueDate);
			return this;
		}

		public Builder clone(AdditionalDetails additionalDetails) {
			this.key(additionalDetails.key);
			this.value(additionalDetails.value);
			this.valueDate(additionalDetails.valueDate);
			return this;
		}

		public AdditionalDetails build() {
			AdditionalDetails additionalDetails = new AdditionalDetails();
			this.operations.forEach(c -> c.accept(additionalDetails));
			// handle default values
			additionalDetails.valueDate = additionalDetails.valueDate == null ? LocalDateTime.now()
					: additionalDetails.valueDate;
			return additionalDetails;
		}

	}

	private AdditionalDetails() {
		// use builder instead
	}

	public Optional<String> getValue() {
		return Optional.ofNullable(value);
	}

	public Optional<LocalDateTime> getValueDate() {
		return Optional.ofNullable(valueDate);
	}

}
