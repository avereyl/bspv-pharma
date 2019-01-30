package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	
	public static class Builder {

		private Builder() {
		}

		private final List<Consumer<Inventory>> operations = new ArrayList<>();

		public Builder id(@NonNull UUID id) {
			this.operations.add(i -> i.id = id);
			return this;
		}
		public Builder version(@NonNull Long version) {
			this.operations.add(o -> o.version = version);
			return this;
		}
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			this.operations.add(i -> i.createdDate = createdDate);
			return this;
		}
		public Builder closedDate(LocalDateTime closedDate) {
			this.operations.add(i -> i.closedDate = closedDate);
			return this;
		}
		public Builder position(@NonNull StockPosition position) {
			this.operations.add(i -> i.positions.add(position.toBuilder().linkedInventory(i).build()));
			return this;
		}
		public Builder positions(@NonNull Set<StockPosition> positions) {
			positions.forEach(this::position);
			return this;
		}
		public Builder positions() {
			this.operations.add(i -> i.positions.clear());
			return this;
		}
		public Builder responsibleUser(UUID responsibleUser) {
			this.operations.add(i -> i.responsibleUser = responsibleUser);
			return this;
		}
		public Builder comment(@NonNull String comment) {
			this.operations.add(i -> i.comment = comment);
			return this;
		}

		private Builder clone(Inventory inventory) {
			this.id(inventory.id);
			this.version(inventory.version);
			this.createdDate(inventory.createdDate);
			this.closedDate(inventory.closedDate);
			this.positions(inventory.positions);
			this.responsibleUser(inventory.responsibleUser);
			this.comment(inventory.comment);
			return this;
		}
		
		public Inventory build() {
			Inventory inventory = new Inventory();
			this.operations.forEach(c -> c.accept(inventory));
			// handling default values
			inventory.id = inventory.id == null ? UUID.randomUUID() : inventory.id;
			inventory.version = inventory.version == null ? 0 : inventory.version;
			inventory.createdDate = inventory.createdDate == null ? LocalDateTime.now() : inventory.createdDate;
			inventory.comment = inventory.comment == null ? "" : inventory.comment;
			return inventory;
		}
		
	}
	
	public static Builder builder() {
		return new Inventory.Builder();
	}
	public Builder toBuilder() {
		return new Inventory.Builder().clone(this);
	}

	@Getter
	private UUID id;
	/**
	 * Version number for optimistic locking.
	 */
	@Getter
	private Long version;
	@Getter
	private LocalDateTime createdDate;
	private LocalDateTime closedDate;
	private final Set<StockPosition> positions = new HashSet<>();
	private UUID responsibleUser;
	@Getter
	private String comment;

	public Optional<UUID> getResponsibleUser() {
		return Optional.ofNullable(this.responsibleUser);
	}
	
	public Optional<LocalDateTime> getClosedDate() {
		return Optional.ofNullable(this.closedDate);
	}

	public Set<StockPosition> getPositions() {
		return Collections.unmodifiableSet(this.positions);
	}
	
	public boolean isClosed() {
		return getClosedDate().isPresent();
	}

}
