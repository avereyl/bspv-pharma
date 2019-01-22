package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

/**
 * 
 * @author guillaume
 *
 */
public final class Movement implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = 3804255031635188617L;

	/**
	 * Type of movement.
	 * 
	 * <ul>
	 * <li>CONSUMPTION: Amount of goods is consumed
	 * <li>DESTRUCTION: Amount of goods is destroyed (out of date, out or order...)
	 * <li>FIX: Movement for stock position update
	 * <li>MOVE: Movement between locations (should be paired with another movement
	 * : IN & OUT)
	 * <li>ORDER: Amount of goods received by an order
	 * <li>FORECAST: Amount of goods planned to be received or consumed
	 * <li>UNKNOWN: As it states: unknown reason !
	 * </ul>
	 *
	 */
	public enum MovementReason {
		CONSUMPTION, DESTRUCTION, FIX, MOVE, ORDER, FORECAST, UNKNOWN;
	}

	/**
	 * Unique id for this movement.
	 */
	@Getter
	private UUID id;

	/**
	 * Goods concerned.
	 */
	@Getter
	private Goods goods;

	/**
	 * Location concerned by the movement.
	 */
	@Getter
	private Location location;

	/**
	 * Quantity of goods moved
	 */
	@Getter
	private Integer quantity;

	/**
	 * Reason for the movement. (category)
	 */
	@Getter
	private MovementReason reason;

	/**
	 * Creation date of this movement.
	 */
	@Getter
	private LocalDateTime createdDate;

	/**
	 * Date of effect for this movement.
	 */
	@Getter
	private LocalDateTime valueDate;

	/**
	 * Id of the user responsible for this movement. Might be null.
	 */
	private UUID responsibleUser;

	/**
	 * Reference to a optional linked movement.
	 */
	private Movement linkedMovement;

	/**
	 * Reference to an optional order
	 */
	private Order linkedOrder;

	public Optional<Movement> getLinkedMovement() {
		return Optional.ofNullable(linkedMovement);
	}

	public Optional<Order> getLinkedOrder() {
		return Optional.ofNullable(linkedOrder);
	}

	public Optional<UUID> getResponsibleUser() {
		return Optional.ofNullable(responsibleUser);
	}

	private Movement() {
		// use builder instead
	}

	public static final MovementGoodsBuilder builder() {
		return new Movement.Builder();
	}

	public final Builder toBuilder() {
		return new Movement.Builder().clone(this);
	}

	public static interface MovementGoodsBuilder {
		MovementQuantityBuilder of(@NonNull Goods goods);
	}

	public static interface MovementQuantityBuilder {
		MovementLocationBuilder quantity(Integer quantity);
	}

	public static interface MovementLocationBuilder {
		MovementBuilder to(@NonNull Location location);

		MovementBuilder from(@NonNull Location location);
	}

	public static interface MovementBuilder {
		MovementBuilder id(@NonNull UUID id);

		MovementBuilder reason(@NonNull MovementReason reason);

		MovementBuilder createdDate(@NonNull LocalDateTime createdDate);

		MovementBuilder valueDate(@NonNull LocalDateTime valueDate);

		MovementBuilder linkedMovement(@NonNull Movement linkedMovement);

		MovementBuilder linkedOrder(@NonNull Order linkedOrder);

		MovementBuilder responsibleUser(@NonNull UUID responsibleUser);

		Movement build();
	}

	public static final class Builder
			implements MovementGoodsBuilder, MovementLocationBuilder, MovementQuantityBuilder, MovementBuilder {

		private Builder() {
		}

		private final List<Consumer<Movement>> operations = new ArrayList<>();

		public Builder id(@NonNull UUID id) {
			this.operations.add(sp -> sp.id = id);
			return this;
		}

		@Override
		public MovementQuantityBuilder of(@NonNull Goods goods) {
			return goods(goods);
		}

		private MovementQuantityBuilder goods(@NonNull Goods goods) {
			this.operations.add(sp -> sp.goods = goods);
			return this;
		}

		@Override
		public MovementLocationBuilder quantity(@NonNull Integer quantity) {
			this.operations.add(sp -> sp.quantity = Math.abs(quantity));
			return this;
		}

		@Override
		public MovementBuilder from(@NonNull Location location) {
			this.operations.add(sp -> sp.location = location);
			this.operations.add(sp -> sp.quantity = sp.quantity * -1);
			return this;
		}

		@Override
		public MovementBuilder to(@NonNull Location location) {
			return location(location);
		}

		private MovementBuilder location(@NonNull Location location) {
			this.operations.add(sp -> sp.location = location);
			return this;
		}

		@Override
		public MovementBuilder reason(@NonNull MovementReason reason) {
			this.operations.add(sp -> sp.reason = reason);
			return this;
		}

		@Override
		public MovementBuilder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(sp -> sp.createdDate = createdDate);
			return this;
		}

		@Override
		public MovementBuilder valueDate(@NonNull LocalDateTime valueDate) {
			this.operations.add(sp -> sp.valueDate = valueDate);
			return this;
		}

		@Override
		public MovementBuilder linkedMovement(@NonNull Movement linkedMovement) {
			this.operations.add(sp -> sp.linkedMovement = linkedMovement);
			return this;
		}

		@Override
		public MovementBuilder linkedOrder(@NonNull Order linkedOrder) {
			this.operations.add(sp -> sp.linkedOrder = linkedOrder);
			return this;
		}

		@Override
		public MovementBuilder responsibleUser(UUID responsibleUser) {
			this.operations.add(sp -> sp.responsibleUser = responsibleUser);
			return this;
		}

		@Override
		public Movement build() {
			Movement movement = new Movement();
			this.operations.forEach(c -> c.accept(movement));
			// handling default values
			movement.id = movement.id == null ? UUID.randomUUID() : movement.id;
			movement.reason = movement.reason == null ? MovementReason.UNKNOWN : movement.reason;
			LocalDateTime now = LocalDateTime.now();
			movement.createdDate = movement.createdDate == null ? now : movement.createdDate;
			movement.valueDate = movement.valueDate == null ? now : movement.valueDate;
			return movement;
		}

		public Builder clone(Movement movement) {
			this.id(movement.id);
			this.goods(movement.goods);
			this.location(movement.location);
			this.quantity(movement.quantity);
			this.reason(movement.reason);
			this.createdDate(movement.createdDate);
			this.valueDate(movement.valueDate);
			this.linkedMovement(movement.linkedMovement);
			this.linkedOrder(movement.linkedOrder);
			this.responsibleUser(movement.responsibleUser);
			return this;
		}

	}

}
