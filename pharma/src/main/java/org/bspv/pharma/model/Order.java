package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

public final class Order implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 7511819088515918000L;

	private Order() {
		// use the builder instead
	}

	public static Builder builder() {
		return new Order.Builder();
	}

	public Builder toBuilder() {
		return new Order.Builder().clone(this);
	}

	public static class Builder {
		
		private final List<Consumer<Order>> operations = new ArrayList<>();
		
		private Builder() {
		}

		public Builder id(@NonNull UUID id) {
			this.operations.add(o -> o.id = id);
			return this;
		}
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(o -> o.createdDate = createdDate);
			return this;
		}
		public Builder sentDate(LocalDateTime sentDate) {
			this.operations.add(o -> o.sentDate = sentDate);
			return this;
		}
		public Builder receivedDate(LocalDateTime receivedDate) {
			this.operations.add(o -> o.receivedDate = receivedDate);
			return this;
		}
		public Builder validatedDate(LocalDateTime validatedDate) {
			this.operations.add(o -> o.validatedDate = validatedDate);
			return this;
		}

		public Builder userResponsibleForCreation(UUID userResponsibleForCreation) {
			this.operations.add(o -> o.userResponsibleForCreation = userResponsibleForCreation);
			return this;
		}
		public Builder userResponsibleForValidation(UUID userResponsibleForValidation) {
			this.operations.add(o -> o.userResponsibleForValidation = userResponsibleForValidation);
			return this;
		}
		public Builder internalComment(@NonNull String internalComment) {
			this.operations.add(o -> o.internalComment = internalComment);
			return this;
		}
		public Builder externalComment(@NonNull String externalComment) {
			this.operations.add(o -> o.externalComment = externalComment);
			return this;
		}
		
		public Builder item(@NonNull final Goods goods, @NonNull final Integer quantity) {
			this.operations.add(o -> o.items.put(goods, quantity));
			return this;
		}
		public Builder items(@NonNull final Map<Goods, Integer> items) {
			this.operations.add(o -> o.items.putAll(items));
			return this;
		}
		public Builder items() {
			this.operations.add(o -> o.items.clear());
			return this;
		}
		public Builder extraItem(@NonNull String extraItem, @NonNull final Integer quantity) {
			this.operations.add(o -> o.extraItems.put(extraItem, quantity));
			return this;
		}
		public Builder extraItems(@NonNull final Map<String, Integer> extraItems) {
			this.operations.add(o -> o.extraItems.putAll(extraItems));
			return this;
		}
		public Builder extraItems() {
			this.operations.add(o -> o.extraItems.clear());
			return this;
		}
		
		
		private Builder clone(Order order) {
			this.id(order.id);
			this.createdDate(order.createdDate);
			this.sentDate(order.sentDate);
			this.receivedDate(order.receivedDate);
			this.validatedDate(order.validatedDate);
			this.userResponsibleForCreation(order.userResponsibleForCreation);
			this.userResponsibleForValidation(order.userResponsibleForValidation);
			this.internalComment(order.internalComment);
			this.externalComment(order.externalComment);
			this.items(order.items);
			this.extraItems(order.extraItems);
			return this;
		}


		public Order build() {
			Order order = new Order();
			this.operations.forEach(c -> c.accept(order));
			// handling default values
			order.id = order.id == null ? UUID.randomUUID() : order.id;
			order.createdDate = order.createdDate == null ? LocalDateTime.now() : order.createdDate;
			order.internalComment = order.internalComment == null ? "" : order.internalComment;
			order.externalComment = order.externalComment == null ? "" : order.externalComment;
			return order;
		}

	}

	@Getter
	private UUID id;

	@Getter
	private LocalDateTime createdDate;

	private LocalDateTime sentDate;
	private LocalDateTime receivedDate;
	private LocalDateTime validatedDate;

	private UUID userResponsibleForCreation;
	private UUID userResponsibleForValidation;

	@Getter
	private String internalComment;
	@Getter
	private String externalComment;

	@Getter
	private final Map<Goods, Integer> items = new LinkedHashMap<>();
	@Getter
	private final Map<String, Integer> extraItems = new LinkedHashMap<>();

	public Optional<LocalDateTime> getReceivedDate() {
		return Optional.ofNullable(this.receivedDate);
	}

	public Optional<LocalDateTime> getSentDate() {
		return Optional.ofNullable(this.sentDate);
	}

	public Optional<LocalDateTime> getValidatedDate() {
		return Optional.ofNullable(this.validatedDate);
	}

	public Optional<UUID> getUserResponsibleForCreation() {
		return Optional.ofNullable(this.userResponsibleForCreation);
	}

	public Optional<UUID> getUserResponsibleForValidation() {
		return Optional.ofNullable(this.userResponsibleForValidation);
	}
}
