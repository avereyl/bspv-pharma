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

@ToString
@EqualsAndHashCode(of = { "id" })
public final class OrderEvent implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 7511819088515918000L;
	
	public enum OrderEventType {
		PRINTING,
		DISPATCH,
		RECEPTION,
		VALIDATION
	}
	
	@Getter
	private UUID id;
	@Getter
	private UUID createdBy;
	@Getter
	private LocalDateTime createdDate;
	@Getter
	private OrderEventType type;

	private OrderEvent() {
		// use the builder instead
	}

	public static OrderEventTypeBuilder builder() {
		return new OrderEvent.Builder();
	}

	public Builder toBuilder() {
		return new OrderEvent.Builder().clone(this);
	}
	
	public static interface OrderEventTypeBuilder {
		OrderEventCreatedByBuilder type(@NonNull OrderEventType type);
	}
	public static interface OrderEventCreatedByBuilder {
		OrderEventBuilder createdBy(@NonNull UUID id);
	}
	public static interface OrderEventBuilder {
		OrderEventBuilder id(@NonNull UUID id);
		OrderEventBuilder createdDate(@NonNull LocalDateTime createdDate);
		OrderEventBuilder createdBy(@NonNull UUID id);
		OrderEvent build();
	}

	public static class Builder implements OrderEventTypeBuilder, OrderEventCreatedByBuilder, OrderEventBuilder {
		
		private final List<Consumer<OrderEvent>> operations = new ArrayList<>();
		
		private Builder() {
		}

		@Override
		public Builder id(@NonNull UUID id) {
			this.operations.add(o -> o.id = id);
			return this;
		}
		@Override
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(o -> o.createdDate = createdDate);
			return this;
		}
				
		
		@Override
		public Builder createdBy(@NonNull UUID createdBy) {
			this.operations.add(o -> o.createdBy = createdBy);
			return this;
		}

		@Override
		public Builder	 type(@NonNull OrderEventType type) {
			this.operations.add(o -> o.type = type);
			return this;
		}

		private Builder clone(OrderEvent order) {
			this.id(order.id);
			this.type(order.type);
			this.createdBy(order.createdBy);
			this.createdDate(order.createdDate);
			return this;
		}

		public OrderEvent build() {
			OrderEvent order = new OrderEvent();
			this.operations.forEach(c -> c.accept(order));
			// handling default values
			order.id = order.id == null ? UUID.randomUUID() : order.id;
			order.createdDate = order.createdDate == null ? LocalDateTime.now() : order.createdDate;
			return order;
		}

	}

	
}
