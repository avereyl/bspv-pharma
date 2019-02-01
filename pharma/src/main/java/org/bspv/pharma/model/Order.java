package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = { "id" })
public final class Order implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 7511819088515918000L;

	private Order() {
		// use the builder instead
	}
	
	@Getter
	private UUID id;
	/**
	 * Version number for optimistic locking.
	 */
	@Getter
	private Long version;
	@Getter
	private UUID createdBy;
	@Getter
	private LocalDateTime createdDate;
	
	private UUID lastModifiedBy;// might be null
	private LocalDateTime lastModifiedDate;// might be null
	
	@Getter
	private String internalComment;
	@Getter
	private String externalComment;

	private final Set<OrderEvent> events = new LinkedHashSet<>();
	private final Map<Goods, Integer> items = new LinkedHashMap<>();
	private final Map<String, Integer> extraItems = new LinkedHashMap<>();

	
	public static OrderCreatedByBuilder builder() {
		return new Order.Builder();
	}

	public Builder toBuilder() {
		return new Order.Builder().clone(this);
	}
	
	public static interface OrderCreatedByBuilder {
		OrderBuilder createdBy(@NonNull UUID createdBy);
	}
	public static interface OrderBuilder {
		OrderBuilder id(@NonNull UUID id);
		OrderBuilder version(@NonNull Long version);
		OrderBuilder createdDate(@NonNull LocalDateTime createdDate);
		OrderBuilder lastModifiedBy(UUID lastModifiedBy);
		OrderBuilder lastModifiedDate(LocalDateTime lastModifiedDate);
		OrderBuilder internalComment(@NonNull String internalComment);
		OrderBuilder externalComment(@NonNull String externalComment);
		OrderBuilder item(@NonNull Goods goods, @NonNull Integer quantity);
		OrderBuilder items(@NonNull Map<Goods, Integer> items);
		OrderBuilder items();
		OrderBuilder extraItem(@NonNull String extraItem, @NonNull Integer quantity);
		OrderBuilder extraItems(@NonNull Map<String, Integer> extraItems);
		OrderBuilder extraItems();
		OrderBuilder event(@NonNull OrderEvent event);
		OrderBuilder events(@NonNull Set<OrderEvent> events);
		OrderBuilder events();
		Order build();
	}

	public static class Builder implements OrderCreatedByBuilder, OrderBuilder {
		
		private final List<Consumer<Order>> operations = new ArrayList<>();
		
		private Builder() {
		}

		@Override
		public Builder id(@NonNull UUID id) {
			this.operations.add(o -> o.id = id);
			return this;
		}
		@Override
		public Builder version(@NonNull Long version) {
			this.operations.add(o -> o.version = version);
			return this;
		}
		@Override
		public Builder createdBy(@NonNull UUID createdBy) {
			this.operations.add(o -> o.createdBy = createdBy);
			return this;
		}
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(o -> o.createdDate = createdDate);
			return this;
		}
		@Override
		public Builder lastModifiedBy(UUID lastModifiedBy) {
			this.operations.add(o -> o.lastModifiedBy = lastModifiedBy);
			return this;
		}

		@Override
		public Builder lastModifiedDate(LocalDateTime lastModifiedDate) {
			this.operations.add(o -> o.lastModifiedDate = lastModifiedDate);
			return this;
		}
		@Override
		public Builder internalComment(@NonNull String internalComment) {
			this.operations.add(o -> o.internalComment = internalComment);
			return this;
		}
		@Override
		public Builder externalComment(@NonNull String externalComment) {
			this.operations.add(o -> o.externalComment = externalComment);
			return this;
		}
		
		@Override
		public Builder item(@NonNull final Goods goods, @NonNull final Integer quantity) {
			this.operations.add(o -> o.items.put(goods, quantity));
			return this;
		}
		@Override
		public Builder items(@NonNull final Map<Goods, Integer> items) {
			this.operations.add(o -> o.items.putAll(items));
			return this;
		}
		@Override
		public Builder items() {
			this.operations.add(o -> o.items.clear());
			return this;
		}
		@Override
		public Builder extraItem(@NonNull String extraItem, @NonNull final Integer quantity) {
			this.operations.add(o -> o.extraItems.put(extraItem, quantity));
			return this;
		}
		@Override
		public Builder extraItems(@NonNull final Map<String, Integer> extraItems) {
			this.operations.add(o -> o.extraItems.putAll(extraItems));
			return this;
		}
		@Override
		public Builder extraItems() {
			this.operations.add(o -> o.extraItems.clear());
			return this;
		}
		
		@Override
		public Builder event(@NonNull OrderEvent event) {
			this.operations.add(o -> o.events.add(event));
			return this;
		}

		@Override
		public Builder events(@NonNull Set<OrderEvent> events) {
			this.operations.add(o -> o.events.addAll(events));
			return this;
		}

		@Override
		public Builder events() {
			this.operations.add(o -> o.events.clear());
			return this;
		}

		private Builder clone(Order order) {
			this.id(order.id);
			this.version(order.version);
			this.createdBy(order.createdBy);
			this.createdDate(order.createdDate);
			this.lastModifiedBy(order.lastModifiedBy);
			this.lastModifiedDate(order.lastModifiedDate);
			this.internalComment(order.internalComment);
			this.externalComment(order.externalComment);
			this.items(order.items);
			this.extraItems(order.extraItems);
			this.events(order.events);
			return this;
		}

		@Override
		public Order build() {
			Order order = new Order();
			this.operations.forEach(c -> c.accept(order));
			// handling default values
			order.id = order.id == null ? UUID.randomUUID() : order.id;
			order.version = order.version == null ? 0 : order.version;
			order.createdDate = order.createdDate == null ? LocalDateTime.now() : order.createdDate;
			order.internalComment = order.internalComment == null ? "" : order.internalComment;
			order.externalComment = order.externalComment == null ? "" : order.externalComment;
			return order;
		}

	}

	

	public Optional<LocalDateTime> getLastModifiedDate() {
		return Optional.ofNullable(this.lastModifiedDate);
	}

	public Optional<UUID> getLastModifiedBy() {
		return Optional.ofNullable(this.lastModifiedBy);
	}

	public Map<Goods, Integer> getItems() {
		return Collections.unmodifiableMap(this.items);
	}

	public Map<String, Integer> getExtraItems() {
		return Collections.unmodifiableMap(this.extraItems);
	}

	public Set<OrderEvent> getEvents() {
		return Collections.unmodifiableSet(this.events);
	}
	
	
}
