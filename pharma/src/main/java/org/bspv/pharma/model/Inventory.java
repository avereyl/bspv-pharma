package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

@ToString
@EqualsAndHashCode(of = { "id" })
public final class Inventory implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 3218224950557063655L;

    private Inventory() {
        // use the builder instead
    }

    @Getter
    private UUID id;
    @Getter
    private Long version;
    @Getter
    private UUID createdBy;
    @Getter
    private OffsetDateTime createdDate;
    private UUID lastModifiedBy;// might be null
    private OffsetDateTime lastModifiedDate;// might be null
    private OffsetDateTime closedDate;// might be null
    private UUID closedBy;// might be null
    private final Set<StockPosition> positions = new HashSet<>();
    @Getter
    private String comment;

    public static interface InventoryCreatedByBuilder {
        InventoryBuilder createdBy(@NonNull UUID createdBy);
    }

    public static interface InventoryBuilder {
        InventoryBuilder id(UUID id);

        InventoryBuilder version(Long version);

        InventoryBuilder createdDate(OffsetDateTime createdDate);

        InventoryBuilder lastModifiedBy(UUID lastModifiedBy);

        InventoryBuilder lastModifiedDate(OffsetDateTime lastModifiedDate);

        InventoryBuilder closedBy(UUID closedBy);

        InventoryBuilder closedDate(OffsetDateTime closedDate);

        InventoryBuilder position(StockPosition position);

        InventoryBuilder positions(Set<StockPosition> positions);

        InventoryBuilder positions();

        InventoryBuilder comment(String comment);

        Inventory build();
    }

    public static class Builder implements InventoryCreatedByBuilder, InventoryBuilder {

        private Builder() {
        }

        private final List<Consumer<Inventory>> operations = new ArrayList<>();

        @Override
        public Builder id(@NonNull final UUID id) {
            this.operations.add(i -> i.id = id);
            return this;
        }

        @Override
        public Builder version(@NonNull final Long version) {
            this.operations.add(o -> o.version = version);
            return this;
        }

        @Override
        public Builder createdBy(@NonNull final UUID createdBy) {
            this.operations.add(i -> i.createdBy = createdBy);
            return this;
        }

        @Override
        public Builder createdDate(@NonNull final OffsetDateTime createdDate) {
            this.operations.add(i -> i.createdDate = createdDate);
            return this;
        }

        @Override
        public Builder lastModifiedBy(final UUID lastModifiedBy) {
            this.operations.add(i -> i.lastModifiedBy = lastModifiedBy);
            return this;
        }

        @Override
        public Builder lastModifiedDate(final OffsetDateTime lastModifiedDate) {
            this.operations.add(i -> i.lastModifiedDate = lastModifiedDate);
            return this;
        }

        @Override
        public Builder closedDate(final OffsetDateTime closedDate) {
            this.operations.add(i -> i.closedDate = closedDate);
            return this;
        }

        @Override
        public Builder closedBy(final UUID closedBy) {
            this.operations.add(i -> i.closedBy = closedBy);
            return this;
        }

        @Override
        public Builder position(@NonNull final StockPosition position) {
            this.operations.add(i -> i.positions.add(position.toBuilder().linkedInventory(i).build()));
            return this;
        }

        @Override
        public Builder positions(@NonNull final Set<StockPosition> positions) {
            positions.forEach(this::position);
            return this;
        }

        @Override
        public Builder positions() {
            this.operations.add(i -> i.positions.clear());
            return this;
        }

        @Override
        public Builder comment(@NonNull final String comment) {
            this.operations.add(i -> i.comment = comment);
            return this;
        }

        private Builder clone(final Inventory inventory) {
            this.id(inventory.id);
            this.version(inventory.version);
            this.createdBy(inventory.createdBy);
            this.createdDate(inventory.createdDate);
            this.lastModifiedBy(inventory.lastModifiedBy);
            this.lastModifiedDate(inventory.lastModifiedDate);
            this.closedBy(inventory.closedBy);
            this.closedDate(inventory.closedDate);
            this.positions(inventory.positions);
            this.comment(inventory.comment);
            return this;
        }

        @Override
        public Inventory build() {
            final Inventory inventory = new Inventory();
            this.operations.forEach(c -> c.accept(inventory));
            // handling default values
            inventory.id = inventory.id == null ? UUID.randomUUID() : inventory.id;
            inventory.version = inventory.version == null ? 0 : inventory.version;
            inventory.createdDate = inventory.createdDate == null ? OffsetDateTime.now() : inventory.createdDate;
            inventory.comment = inventory.comment == null ? "" : inventory.comment;
            return inventory;
        }

    }

    public static InventoryCreatedByBuilder builder() {
        return new Inventory.Builder();
    }

    public Builder toBuilder() {
        return new Inventory.Builder().clone(this);
    }

    public Optional<UUID> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    public Optional<OffsetDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    public Optional<UUID> getClosedBy() {
        return Optional.ofNullable(this.closedBy);
    }

    public Optional<OffsetDateTime> getClosedDate() {
        return Optional.ofNullable(this.closedDate);
    }

    public Set<StockPosition> getPositions() {
        return Collections.unmodifiableSet(this.positions);
    }

    public boolean isClosed() {
        return this.getClosedDate().isPresent();
    }

}
