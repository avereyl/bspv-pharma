package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

/**
 * This class indicates for a {@link Location} and {@link Goods} the number of
 * elements at a given time. This position could have been checked by a user or
 * is computed.
 * 
 * @author guillaume
 *
 */
@Getter
public final class StockPosition implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 7935404557509004867L;

	/**
	 * Type of stock position.
	 * <ul>
	 * <li>CHECKED: checked stocked position
	 * <li>COMPUTED: computed stocked position - default type (indicates live status
	 * when movements have been made after latest CHECKED position) - might be used
	 * for forecast
	 * <li>PENDING: stocked position being created / not validated yet (might be
	 * used for inventory)
	 * </ul>
	 *
	 */
	public enum StockPostitionType {
		CHECKED, COMPUTED, PENDING
	}
	
	/**
	 * Identifier of this position.
	 */
	private UUID id;
	
	/**
	 * Minimal amount of goods expected for this location. Can be use as a threshold
	 * to fire a new order.
	 */
	private Integer minimum;
	
	/**
	 * Maximal amount of goods expected for this location.
	 */
	private Integer maximum;
	
	/**
	 * Optimal amount of goods expected for this location.
	 */
	private Integer optimum;
	
	/**
	 * Current (at the value date) amount of goods computed or checked for this
	 * location.
	 */
	private Integer current;
	
	/**
	 * Type of {@link StockPosition}.
	 * 
	 * @see StockPostitionType
	 */
	private StockPostitionType type;
	
	/**
	 * Date of creation of this stock position.
	 */
	private LocalDateTime createdDate;
	
	/**
	 * Date of value of this stock position. May be equals to the
	 * this{@link #createdDate}, or before/after createdDate if trying to compute
	 * status in the past or in the future
	 */
	private LocalDateTime valueDate;
	
	/**
	 * Goods concerned by this position.
	 */
	private Goods goods;
	
	/**
	 * Location concerned by this position.
	 */
	private Location location;

	/**
	 * Some {@link ExtraInformation} on this stock position.
	 */
	private final Set<ExtraInformation> information = new HashSet<>();

	/**
	 * 
	 * @author guillaume
	 *
	 */
	public static interface StockPositionLocationBuilder{
		StockPositionGoodsBuilder location(@NonNull Location location);
	}
	public static interface StockPositionGoodsBuilder{
		StockPositionBuilder goods(@NonNull Goods goods);
	}
	public static interface StockPositionBuilder{
		StockPositionBuilder id(@NonNull UUID id);
		StockPositionBuilder minimum(@NonNull Integer minimum);
		StockPositionBuilder maximum(@NonNull Integer maximum);
		StockPositionBuilder optimum(@NonNull Integer optimum);
		StockPositionBuilder current(@NonNull Integer current);
		StockPositionBuilder type(@NonNull StockPostitionType type);
		StockPositionBuilder checked();
		StockPositionBuilder computed();
		StockPositionBuilder pending();
		StockPositionBuilder createdDate(@NonNull LocalDateTime createdDate);
		StockPositionBuilder valueDate(@NonNull LocalDateTime valueDate);
		StockPositionBuilder information();
		StockPositionBuilder information(@NonNull ExtraInformation info);
		StockPositionBuilder information(@NonNull Set<ExtraInformation> information);
	}
	
	public static final class Builder implements StockPositionLocationBuilder, StockPositionGoodsBuilder, StockPositionBuilder {

		private Builder() {
		}

		private final List<Consumer<StockPosition>> operations = new ArrayList<>();

		public StockPosition build() {
			StockPosition stockPosition = new StockPosition();
			this.operations.forEach(c -> c.accept(stockPosition));
			// handling default values
			stockPosition.id = stockPosition.id == null ? UUID.randomUUID() : stockPosition.id;
			stockPosition.minimum = stockPosition.minimum == null ? 0 : stockPosition.minimum;
			stockPosition.maximum = stockPosition.maximum == null ? 0 : stockPosition.maximum;
			stockPosition.optimum = stockPosition.optimum == null ? 0 : stockPosition.optimum;
			stockPosition.current = stockPosition.current == null ? 0 : stockPosition.current;
			stockPosition.type = stockPosition.type == null ? StockPostitionType.COMPUTED : stockPosition.type;
			LocalDateTime now = LocalDateTime.now();
			stockPosition.createdDate = stockPosition.createdDate == null ? now : stockPosition.createdDate;
			stockPosition.valueDate = stockPosition.valueDate == null ? now : stockPosition.valueDate;
			return stockPosition;
		}
		@Override
		public Builder id(@NonNull UUID id) {
			this.operations.add(sp -> sp.id = id);
			return this;
		}
		@Override
		public Builder minimum(@NonNull Integer minimum) {
			this.operations.add(sp -> sp.minimum = minimum);
			return this;
		}
		@Override
		public Builder maximum(@NonNull Integer maximum) {
			this.operations.add(sp -> sp.maximum = maximum);
			return this;
		}
		@Override
		public Builder optimum(@NonNull Integer optimum) {
			this.operations.add(sp -> sp.optimum = optimum);
			return this;
		}
		@Override
		public Builder current(@NonNull Integer current) {
			this.operations.add(sp -> sp.current = current);
			return this;
		}
		
		@Override
		public Builder type(@NonNull StockPostitionType type) {
			this.operations.add(sp -> sp.type = type);
			return this;
		}
		@Override
		public Builder checked() {
			this.operations.add(sp -> sp.type = StockPostitionType.CHECKED);
			return this;
		}
		@Override
		public Builder computed() {
			this.operations.add(sp -> sp.type = StockPostitionType.COMPUTED);
			return this;
		}
		@Override
		public Builder pending() {
			this.operations.add(sp -> sp.type = StockPostitionType.PENDING);
			return this;
		}
		@Override
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(sp -> sp.createdDate = createdDate);
			return this;
		}
		@Override
		public Builder valueDate(@NonNull LocalDateTime valueDate) {
			this.operations.add(sp -> sp.valueDate = valueDate);
			return this;
		}
		@Override
		public Builder location(@NonNull Location location) {
			this.operations.add(sp -> sp.location = location);
			return this;
		}
		@Override
		public Builder goods(@NonNull Goods goods) {
			this.operations.add(sp -> sp.goods = goods);
			return this;
		}
		@Override
		public Builder information() {
			this.operations.add(sp -> sp.information.clear());
			return this;
		}
		public Builder information(@NonNull ExtraInformation info) {
			this.operations.add(sp -> sp.information.add(info));
			return this;
		}
		@Override
		public Builder information(@NonNull Set<ExtraInformation> information) {
			this.operations.add(sp -> sp.information.addAll(information));
			return this;
		}
		
		/**
		 * Building a stockPosition from a given one.
		 * @param stockPosition The source stock position.
		 * @return The builder filled with appropriate operation to reproduce given stock position.
		 */
		public Builder clone(StockPosition stockPosition) {
			this.id(stockPosition.id);
			this.minimum(stockPosition.minimum);
			this.maximum(stockPosition.maximum);
			this.optimum(stockPosition.optimum);
			this.current(stockPosition.current);
			this.type(stockPosition.type);
			this.createdDate(stockPosition.createdDate);
			this.valueDate(stockPosition.valueDate);
			this.goods(stockPosition.goods);
			this.location(stockPosition.location);
			this.information(stockPosition.information);
			return this;
		}

	}

	public static final StockPosition.Builder builder() {
		return new StockPosition.Builder();
	}
	public final Builder toBuilder() {
		return new StockPosition.Builder().clone(this);
	}

	/**
	 * Should use the builder instead.
	 */
	private StockPosition() {
	}
	
	private boolean isMovementValid(final Movement movement) {
		boolean valid = true;
		valid &= !this.getValueDate().isAfter(movement.getValueDate());
		valid &= this.getGoods().equals(movement.getGoods());
		valid &= this.getLocation().equals(movement.getLocation());
		valid &= (this.current + movement.getQuantity()) >= 0;
		return valid;
	}

	public StockPosition computeNewPosition(final Movement movement) {
		if (!isMovementValid(movement)) {
			throw new IllegalArgumentException("Cannot apply movement to stockPosition, please check, goods, location, value date and quantity.");
		}
		StockPosition.Builder builder = StockPosition.builder().clone(this);
		builder.current(this.current + movement.getQuantity());
		return builder.build();
	}
	
	public StockPosition computeNewPositionSilently(final Movement movement) {
		if (isMovementValid(movement)) {
			StockPosition.Builder builder = StockPosition.builder().clone(this);
			builder.current(this.current + movement.getQuantity());
			return builder.build();
		}
		return this;
	}
	
	public StockPosition computeNewPosition(final List<Movement> movements) {
		StockPosition.Builder builder = StockPosition.builder().clone(this);
		// TODO sort, check and reduce quantity
		return builder.build();
	}
	
	public StockPosition computeNewPositionSilently(final List<Movement> movements) {
		StockPosition.Builder builder = StockPosition.builder().clone(this);
		// TODO sort, filter and reduce quantity
		return builder.build();
	}

}
