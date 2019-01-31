package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Additional details for a stock position. Allow a user to store some more
 * information for a stock position like next out-of-date goods or just some
 * additional comment.
 * 
 * @author guillaume
 *
 */
@ToString
@EqualsAndHashCode(of = { "id" })
public final class AdditionalDetails implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = -4443763633078637155L;

	public enum DetailsType {
		EXPIRY_DATE, COMMENT, MISC
	}

	@Getter
	private UUID id;
	@Getter
	private UUID createdBy;
	@Getter
	private LocalDateTime createdDate;
	@Getter
	private LocalDateTime validityStartDate;
	@Getter
	private LocalDateTime validityEndDate;
	@Getter
	private LocalDateTime valueDate;
	@Getter
	private DetailsType type;
	@Getter
	private String value;

	public static AdditionalDetailsTypeBuilder builder() {
		return new AdditionalDetails.Builder();
	}

	public Builder toBuilder() {
		return new AdditionalDetails.Builder().clone(this);
	}

	public static interface AdditionalDetailsTypeBuilder {
		AdditionalDetailsCreatedByBuilder type(@NonNull final DetailsType type);
	}

	public static interface AdditionalDetailsCreatedByBuilder {
		AdditionalDetailsBuilder createdBy(@NonNull final UUID createdBy);
	}

	public static interface AdditionalDetailsBuilder {
		AdditionalDetailsBuilder id(@NonNull final UUID id);

		AdditionalDetailsBuilder createdDate(@NonNull final LocalDateTime createdDate);

		AdditionalDetailsBuilder value(@NonNull final String value);

		AdditionalDetailsBuilder valueDate(@NonNull final LocalDateTime valueDate);

		AdditionalDetailsBuilder validFrom(@NonNull final LocalDateTime validityStartDate);

		AdditionalDetailsBuilder validUntil(@NonNull final LocalDateTime validityEndDate);

		AdditionalDetails build();
	}

	public static class Builder
			implements AdditionalDetailsTypeBuilder, AdditionalDetailsCreatedByBuilder, AdditionalDetailsBuilder {

		private final List<Consumer<AdditionalDetails>> operations = new ArrayList<>();

		private Builder() {
		}

		@Override
		public Builder type(@NonNull final DetailsType type) {
			operations.add(ad -> ad.type = type);
			return this;
		}

		@Override
		public Builder id(@NonNull UUID id) {
			operations.add(ad -> ad.id = id);
			return this;
		}

		@Override
		public Builder validFrom(@NonNull LocalDateTime validityStartDate) {
			operations.add(ad -> ad.validityStartDate = validityStartDate);
			return this;
		}

		@Override
		public Builder validUntil(@NonNull LocalDateTime validityEndDate) {
			operations.add(ad -> ad.validityEndDate = validityEndDate);
			return this;
		}

		@Override
		public Builder value(@NonNull final String value) {
			operations.add(ad -> ad.value = value);
			return this;
		}

		@Override
		public Builder valueDate(@NonNull final LocalDateTime valueDate) {
			operations.add(ad -> ad.valueDate = valueDate);
			return this;
		}

		@Override
		public AdditionalDetailsBuilder createdDate(@NonNull LocalDateTime createdDate) {
			operations.add(ad -> ad.createdDate = createdDate);
			return this;
		}

		@Override
		public AdditionalDetailsBuilder createdBy(@NonNull UUID createdBy) {
			operations.add(ad -> ad.createdBy = createdBy);
			return this;
		}

		private Builder clone(AdditionalDetails additionalDetails) {
			this.id(additionalDetails.id);
			this.type(additionalDetails.type);
			this.createdBy(additionalDetails.createdBy);
			this.createdDate(additionalDetails.createdDate);
			this.value(additionalDetails.value);
			this.valueDate(additionalDetails.valueDate);
			this.validFrom(additionalDetails.validityStartDate);
			this.validUntil(additionalDetails.validityEndDate);
			return this;
		}

		@Override
		public AdditionalDetails build() {
			AdditionalDetails additionalDetails = new AdditionalDetails();
			this.operations.forEach(c -> c.accept(additionalDetails));
			// handle default values
			additionalDetails.id = additionalDetails.id == null ? UUID.randomUUID() : additionalDetails.id;
			additionalDetails.value = additionalDetails.value == null ? "" : additionalDetails.value;
			LocalDateTime now = LocalDateTime.now();

			additionalDetails.createdDate = additionalDetails.createdDate == null ? now : additionalDetails.createdDate;
			additionalDetails.validityStartDate = additionalDetails.validityStartDate == null ? now
					: additionalDetails.validityStartDate;
			additionalDetails.validityEndDate = additionalDetails.validityEndDate == null ? LocalDateTime.MAX
					: additionalDetails.validityEndDate;
			additionalDetails.valueDate = additionalDetails.valueDate == null ? additionalDetails.validityEndDate
					: additionalDetails.valueDate;
			return additionalDetails;
		}

	}

	private AdditionalDetails() {
		// use builder instead
	}

}
