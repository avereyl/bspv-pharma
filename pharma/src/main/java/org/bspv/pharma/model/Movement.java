package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 *
 * @author guillaume
 *
 */
@ToString
@EqualsAndHashCode(of = { "id" })
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
     * <li>MOVE: Movement between locations (should be paired with another movement : IN & OUT)
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
     * Version number for optimistic locking.
     */
    @Getter
    private Long version;

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
    private Integer quantity = 0;

    /**
     * Reason for the movement. (category)
     */
    @Getter
    private MovementReason reason;

    /**
     * Id of the user/process responsible for the creation.
     */
    @Getter
    private UUID createdBy;
    /**
     * Creation date of this movement.
     */
    @Getter
    private OffsetDateTime createdDate;

    /**
     * Date of effect for this movement.
     */
    @Getter
    private OffsetDateTime valueDate;

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
        return Optional.ofNullable(this.linkedMovement);
    }

    public Optional<Order> getLinkedOrder() {
        return Optional.ofNullable(this.linkedOrder);
    }

    public Optional<UUID> getResponsibleUser() {
        return Optional.ofNullable(this.responsibleUser);
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
        MovementCreatedByBuilder to(@NonNull Location location);

        MovementCreatedByBuilder from(@NonNull Location location);
    }

    public static interface MovementCreatedByBuilder {
        MovementBuilder createdBy(@NonNull UUID createdBy);
    }

    public static interface MovementBuilder {
        MovementBuilder id(@NonNull UUID id);

        MovementBuilder version(@NonNull Long version);

        MovementBuilder reason(@NonNull MovementReason reason);

        MovementBuilder createdDate(@NonNull OffsetDateTime createdDate);

        MovementBuilder valueDate(@NonNull OffsetDateTime valueDate);

        MovementBuilder linkedMovement(Movement linkedMovement);

        MovementBuilder linkedOrder(Order linkedOrder);

        MovementBuilder responsibleUser(UUID responsibleUser);

        Movement build();
    }

    public static final class Builder implements MovementGoodsBuilder, MovementLocationBuilder, MovementQuantityBuilder,
            MovementCreatedByBuilder, MovementBuilder {

        private Builder() {
        }

        private Function<Integer, Integer> sign = null;
        private final List<Consumer<Movement>> operations = new ArrayList<>();

        @Override
        public Builder id(@NonNull final UUID id) {
            this.operations.add(m -> m.id = id);
            return this;
        }

        @Override
        public Builder version(@NonNull final Long version) {
            this.operations.add(m -> m.version = version);
            return this;
        }

        @Override
        public Builder of(@NonNull final Goods goods) {
            return this.goods(goods);
        }

        private Builder goods(final Goods goods) {
            this.operations.add(m -> m.goods = goods);
            return this;
        }

        @Override
        public Builder quantity(@NonNull final Integer quantity) {
            // if sign is unknown -> positive by default
            final Integer signedQuantity = this.sign == null ? Math.abs(quantity) : this.sign.apply(quantity);
            this.operations.add(m -> m.quantity = signedQuantity);
            return this;
        }

        @Override
        public Builder from(@NonNull final Location location) {
            // ensure quantity is negative (quantity method could be called before or after this method)
            this.sign = i -> Math.abs(i) * -1;
            this.operations.add(m -> m.quantity = Math.abs(m.quantity) * -1);
            return this.location(location);
        }

        @Override
        public Builder to(@NonNull final Location location) {
            // ensure quantity is positive (quantity method could be called before or after this method)
            this.sign = Math::abs;
            this.operations.add(m -> m.quantity = Math.abs(m.quantity));
            return this.location(location);
        }

        @Override
        public Builder createdBy(@NonNull final UUID createdBy) {
            this.operations.add(m -> m.createdBy = createdBy);
            return this;
        }

        private Builder location(final Location location) {
            this.operations.add(m -> m.location = location);
            return this;
        }

        @Override
        public Builder reason(@NonNull final MovementReason reason) {
            this.operations.add(m -> m.reason = reason);
            return this;
        }

        @Override
        public Builder createdDate(@NonNull final OffsetDateTime createdDate) {
            this.operations.add(m -> m.createdDate = createdDate);
            return this;
        }

        @Override
        public Builder valueDate(@NonNull final OffsetDateTime valueDate) {
            this.operations.add(m -> m.valueDate = valueDate);
            return this;
        }

        @Override
        public Builder linkedMovement(final Movement linkedMovement) {
            this.operations.add(m -> m.linkedMovement = linkedMovement);
            return this;
        }

        @Override
        public Builder linkedOrder(final Order linkedOrder) {
            this.operations.add(m -> m.linkedOrder = linkedOrder);
            return this;
        }

        @Override
        public Builder responsibleUser(final UUID responsibleUser) {
            this.operations.add(m -> m.responsibleUser = responsibleUser);
            return this;
        }

        @Override
        public Movement build() {
            final Movement movement = new Movement();
            this.operations.forEach(c -> c.accept(movement));
            // handling default values
            movement.id = movement.id == null ? UUID.randomUUID() : movement.id;
            movement.version = movement.version == null ? 0 : movement.version;
            movement.reason = movement.reason == null ? MovementReason.UNKNOWN : movement.reason;
            final OffsetDateTime now = OffsetDateTime.now();
            movement.createdDate = movement.createdDate == null ? now : movement.createdDate;
            movement.valueDate = movement.valueDate == null ? now : movement.valueDate;
            return movement;
        }

        private Builder clone(final Movement movement) {
            this.sign = (movement.quantity > 0) ? Math::abs : i -> Math.abs(i) * -1;
            this.id(movement.id);
            this.version(movement.version);
            this.createdBy(movement.createdBy);
            this.createdDate(movement.createdDate);
            this.goods(movement.goods);
            this.location(movement.location);
            this.quantity(movement.quantity);
            this.reason(movement.reason);
            this.valueDate(movement.valueDate);
            this.linkedMovement(movement.linkedMovement);
            this.linkedOrder(movement.linkedOrder);
            this.responsibleUser(movement.responsibleUser);
            return this;
        }

    }

}
