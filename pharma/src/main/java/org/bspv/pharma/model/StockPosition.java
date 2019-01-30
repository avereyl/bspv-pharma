package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * This class indicates for a {@link Location} and {@link Goods} the number of
 * elements at a given time. This position could have been checked by a user or
 * is computed.
 * 
 * @author guillaume
 *
 */
@Slf4j
@ToString
@EqualsAndHashCode(of = { "id" })
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
	@Getter
	private UUID id;
	
	/**
	 * Version number for optimistic locking.
	 */
	@Getter
	private Long version;
	/**
	 * Minimal amount of goods expected for this location. Can be use as a threshold
	 * to fire a new order.
	 */
	@Getter
	private Integer minimum;
	
	/**
	 * Maximal amount of goods expected for this location.
	 */
	@Getter
	private Integer maximum;
	
	/**
	 * Optimal amount of goods expected for this location.
	 */
	@Getter
	private Integer optimum;
	
	/**
	 * Current (at the value date) amount of goods computed or checked for this
	 * location.
	 */
	@Getter
	private Integer current;
	
	/**
	 * Type of {@link StockPosition}.
	 * 
	 * @see StockPostitionType
	 */
	@Getter
	private StockPostitionType type;
	
	/**
	 * Date of creation of this stock position.
	 */
	@Getter
	private LocalDateTime createdDate;
	
	/**
	 * Date of value of this stock position. May be equals to the
	 * this{@link #createdDate}, or before/after createdDate if trying to compute
	 * status in the past or in the future
	 */
	@Getter
	private LocalDateTime valueDate;
	
	/**
	 * Goods concerned by this position.
	 */
	@Getter
	private Goods goods;
	
	/**
	 * Location concerned by this position.
	 */
	@Getter
	private Location location;

	/**
	 * Some {@link AdditionalDetails} on this stock position.
	 */
	private final Set<AdditionalDetails> additionalDetails = new HashSet<>();
	
	private UUID responsibleUser;
	
	/**
	 * Reference to an optional inventory
	 */
	private Inventory linkedInventory;

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
		StockPositionBuilder version(@NonNull Long version);
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
		StockPositionBuilder additionalDetails();
		StockPositionBuilder additionalDetail(@NonNull AdditionalDetails detail);
		StockPositionBuilder additionalDetails(@NonNull Set<AdditionalDetails> details);
		StockPositionBuilder responsibleUser(UUID responsibleUser);
		StockPositionBuilder linkedInventory(Inventory linkedInventory);
		StockPosition build();
	}
	
	public static final class Builder implements StockPositionLocationBuilder, StockPositionGoodsBuilder, StockPositionBuilder {

		private Builder() {
		}

		private final List<Consumer<StockPosition>> operations = new ArrayList<>();

		@Override
		public StockPosition build() {
			StockPosition stockPosition = new StockPosition();
			this.operations.forEach(c -> c.accept(stockPosition));
			// handling default values
			stockPosition.id = stockPosition.id == null ? UUID.randomUUID() : stockPosition.id;
			stockPosition.version = stockPosition.version == null ? 0 : stockPosition.version;
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
		public Builder version(@NonNull Long version) {
			this.operations.add(o -> o.version = version);
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
		public Builder additionalDetails() {
			this.operations.add(sp -> sp.additionalDetails.clear());
			return this;
		}
		public Builder additionalDetail(@NonNull AdditionalDetails detail) {
			this.operations.add(sp -> sp.additionalDetails.add(detail));
			return this;
		}
		@Override
		public Builder additionalDetails(@NonNull Set<AdditionalDetails> details) {
			this.operations.add(sp -> sp.additionalDetails.addAll(details));
			return this;
		}
		
		@Override
		public Builder responsibleUser(UUID responsibleUser) {
			this.operations.add(sp -> sp.responsibleUser = responsibleUser);
			return this;
		}

		@Override
		public StockPositionBuilder linkedInventory(Inventory linkedInventory) {
			this.operations.add(sp -> sp.linkedInventory = linkedInventory);
			return this;
		}

		/**
		 * Building a stockPosition from a given one.
		 * @param stockPosition The source stock position.
		 * @return The builder filled with appropriate operation to reproduce given stock position.
		 */
		private Builder clone(StockPosition stockPosition) {
			this.id(stockPosition.id);
			this.version(stockPosition.version);
			this.minimum(stockPosition.minimum);
			this.maximum(stockPosition.maximum);
			this.optimum(stockPosition.optimum);
			this.current(stockPosition.current);
			this.type(stockPosition.type);
			this.createdDate(stockPosition.createdDate);
			this.valueDate(stockPosition.valueDate);
			this.goods(stockPosition.goods);
			this.location(stockPosition.location);
			this.additionalDetails(stockPosition.additionalDetails);
			this.responsibleUser(stockPosition.responsibleUser);
			this.linkedInventory(stockPosition.linkedInventory);
			return this;
		}

	}

	public static final StockPositionLocationBuilder builder() {
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
	
	public Optional<UUID> getResponsibleUser() {
		return Optional.ofNullable(this.responsibleUser);
	}
	
	public Optional<Inventory> getLinkedInventory() {
		return Optional.ofNullable(this.linkedInventory);
	}
	
	public Set<AdditionalDetails> getAdditionalDetails() {
		return Collections.unmodifiableSet(this.additionalDetails);
	}
	
	/**
	 * Check if the given movement can be applied to the given stock position.
	 * <ul>Check:
	 * <li>value date
	 * <li>goods
	 * <li>location
	 * <li>available quantity
	 * </ul>
	 * @param position Stock position
	 * @param movement Movement to apply
	 * @return true if movement can be applied to stock position false otherwise
	 */
	public static boolean canMovementBeAppliedImmediately(final StockPosition position, final Movement movement) {
		boolean valid = true;
		valid &= mayMovementBeApplied(position, movement);
		valid &= (position.getCurrent() + movement.getQuantity()) >= 0;
		return valid;
	}
	
	public boolean canMovementBeAppliedImmediately(final Movement movement) {
		return StockPosition.canMovementBeAppliedImmediately(this, movement);
	}
	/**
	 * Check if the given movement may be applied to the given stock position.
	 * <ul>Check:
	 * <li>value date
	 * <li>goods
	 * <li>location
	 * </ul>
	 * @param position Stock position
	 * @param movement Movement to apply
	 * @return true if movement can be applied to stock position false otherwise
	 */
	public static boolean mayMovementBeApplied(final StockPosition position, final Movement movement) {
		boolean valid = true;
		valid &= !position.getValueDate().isAfter(movement.getValueDate());
		valid &= position.getGoods().equals(movement.getGoods());
		valid &= position.getLocation().equals(movement.getLocation());
		return valid;
	}
	
	public boolean mayMovementBeApplied(final Movement movement) {
		return StockPosition.mayMovementBeApplied(this, movement);
	}

	public StockPosition computeNewPosition(final Movement movement) {
		if (!canMovementBeAppliedImmediately(this, movement)) {
			throw new IllegalArgumentException("Cannot apply movement to stockPosition, please check, goods, location, value date and quantity.");
		}
		StockPosition.Builder builder = this.toBuilder();
		builder.current(this.current + movement.getQuantity());
		return builder.build();
	}
	
	public StockPosition computeNewPositionSilently(final Movement movement) {
		if (canMovementBeAppliedImmediately(this, movement)) {
			StockPosition.Builder builder = this.toBuilder();
			builder.current(this.current + movement.getQuantity());
			return builder.build();
		}
		log.warn("Ignoring movement {}. Returning this stock position.", movement);
		return this;
	}
	
	public StockPosition computeNewPosition(final List<Movement> movements) {
		Comparator<Movement> comparator = (m1, m2) -> m1.getValueDate().compareTo(m2.getValueDate());
		List<Movement> sortedMovements = new ArrayList<>(movements);
		Collections.sort(sortedMovements, comparator);
		Integer valueAfterMovements = this.getCurrent();
		for (Movement movement : sortedMovements) {
			if (mayMovementBeApplied(this, movement)) {
				valueAfterMovements += movement.getQuantity();
				if (valueAfterMovements < 0) {
					log.error("Failed to apply movement {}, resulting quantity would be negative.", movement);
					throw new IllegalArgumentException("Trying to remove more goods than available !");
				}
				log.debug("Applying movement {} to stock position {} (current:{})", movement, this.getId(), valueAfterMovements);
			} else {
				log.error("Cannot apply movement {} to this position, please check value date, goods and location.", movement);
				throw new IllegalArgumentException("Movement cannot be applied to this stock position.");
			}
		}
		return this.toBuilder().current(valueAfterMovements).build();
	}
	
	
	public StockPosition computeNewPositionSilently(final List<Movement> movements) {
		Comparator<Movement> comparator = (m1, m2) -> m1.getValueDate().compareTo(m2.getValueDate());
		List<Movement> sortedMovements = new ArrayList<>(movements);
		Collections.sort(sortedMovements, comparator);
		Integer valueAfterMovements = this.getCurrent();
		for (Movement movement : sortedMovements) {
			if (mayMovementBeApplied(this, movement)) {
				valueAfterMovements += movement.getQuantity();
				if (valueAfterMovements < 0) {
					log.error("Failed to apply movement {}, resulting quantity would be negative.", movement);
					throw new IllegalArgumentException("Trying to remove more goods than available !");
				} else {
					log.debug("Applying movement {} to stock position {} (current:{})", movement, this.getId(), valueAfterMovements);
				}
			} else {
				log.warn("Ignoring movement {} to this position, please check value date, goods and location.", movement);
			}
		}
		return this.toBuilder().current(valueAfterMovements).build();
	}


}
