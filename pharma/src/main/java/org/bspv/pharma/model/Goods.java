/**
 * 
 */
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

/**
 * 
 * @author guillaume
 *
 */
@ToString
@EqualsAndHashCode(of = { "id" })
public final class Goods implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = -2516920878572437352L;
	
	private Goods() {//use builder instead
	}

	/**
	 * Identifier of this piece of goods.
	 */
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
	
	private UUID lastModifiedBy;//might be null
	private LocalDateTime lastModifiedDate;//might be null
	
	@Getter
	private String name;
	@Getter
	private String description;
	
	private LocalDateTime deprecatedDate;//might be null
	private LocalDateTime obsoleteDate;//might be null
	private Location defaultLocation;//might be null
	
	@Getter
	private Integer minimumOrderQuantity;
	@Getter
	private Integer maximumOrderQuantity;
	@Getter
	private Integer optimumOrderQuantity;

	private final Set<Tag> tags = new HashSet<>();// getter return a RO collection 
	
	public static interface GoodsNameBuilder {
		GoodsCreatedByBuilder name(@NonNull String name);
	}
	public static interface GoodsCreatedByBuilder {
		GoodsBuilder createdBy(@NonNull UUID createdBy);
	}

	public static interface GoodsBuilder {
		GoodsBuilder id(@NonNull UUID id);
		GoodsBuilder version(@NonNull Long version);
		GoodsBuilder description(@NonNull String description);
		GoodsBuilder createdDate(@NonNull LocalDateTime createdDate);
		GoodsBuilder lastModifiedBy(UUID uuid);
		GoodsBuilder lastModifiedDate(LocalDateTime lastUpdatedDate);
		GoodsBuilder deprecatedDate(LocalDateTime deprecatedDate);
		GoodsBuilder obsoleteDate(LocalDateTime obsoleteDate);
		GoodsBuilder defaultLocation(Location defaultLocation);
		GoodsBuilder minimumOrderQuantity(@NonNull Integer minimumOrderQuantity);
		GoodsBuilder maximumOrderQuantity(@NonNull Integer maximumOrderQuantity);
		GoodsBuilder optimumOrderQuantity(@NonNull Integer optimumOrderQuantity);
		GoodsBuilder tags();
		GoodsBuilder tag(@NonNull Tag tag);
		GoodsBuilder tags(@NonNull Set<Tag> tags);
		Goods build();
	}

	public static class Builder implements GoodsNameBuilder, GoodsCreatedByBuilder, GoodsBuilder {

		private final List<Consumer<Goods>> operations = new ArrayList<>();

		private Builder() {
		}
		
		@Override
		public Builder id(@NonNull UUID id) {
			operations.add(g -> g.id = id);
			return this;
		}
		@Override
		public Builder version(@NonNull Long version) {
			this.operations.add(o -> o.version = version);
			return this;
		}

		@Override
		public Builder description(@NonNull String description) {
			operations.add(g -> g.description = description);
			return this;
		}

		@Override
		public Builder createdBy(@NonNull UUID createdBy) {
			operations.add(g -> g.createdBy = createdBy);
			return this;
		}

		@Override
		public Builder createdDate(@NonNull LocalDateTime createdDate) {
			operations.add(g -> g.createdDate = createdDate);
			return this;
		}

		@Override
		public Builder lastModifiedBy(UUID lastModifiedBy) {
			operations.add(g -> g.lastModifiedBy = lastModifiedBy);
			return this;
		}
		@Override
		public Builder lastModifiedDate(LocalDateTime lastModifiedDate) {
			operations.add(g -> g.lastModifiedDate = lastModifiedDate);
			return this;
		}

		@Override
		public Builder deprecatedDate(LocalDateTime deprecatedDate) {
			operations.add(g -> g.deprecatedDate = deprecatedDate);
			return this;
		}

		@Override
		public Builder obsoleteDate(LocalDateTime obsoleteDate) {
			operations.add(g -> g.obsoleteDate = obsoleteDate);
			return this;
		}

		@Override
		public Builder defaultLocation(Location defaultLocation) {
			operations.add(g -> g.defaultLocation = defaultLocation);
			return this;
		}

		@Override
		public Builder minimumOrderQuantity(@NonNull Integer minimumOrderQuantity) {
			operations.add(g -> g.minimumOrderQuantity = minimumOrderQuantity);
			return this;
		}

		@Override
		public GoodsBuilder maximumOrderQuantity(@NonNull Integer maximumOrderQuantity) {
			operations.add(g -> g.maximumOrderQuantity = maximumOrderQuantity);
			return this;
		}

		@Override
		public Builder optimumOrderQuantity(@NonNull Integer optimumOrderQuantity) {
			operations.add(g -> g.optimumOrderQuantity = optimumOrderQuantity);
			return this;
		}

		@Override
		public Builder tag(@NonNull Tag tag) {
			operations.add(g -> g.tags.add(tag));
			return this;
		}

		@Override
		public Builder tags() {
			operations.add(g -> g.tags.clear());
			return this;
		}

		@Override
		public Builder tags(@NonNull Set<Tag> tags) {
			operations.add(g -> g.tags.addAll(tags));
			return this;
		}

		@Override
		public Builder name(@NonNull String name) {
			operations.add(g -> g.name = name);
			return this;
		}

		/**
		 * 
		 * @return new instance of {@link Goods}
		 */
		@Override
		public Goods build() {
			Goods goods = new Goods();
			operations.forEach(c -> c.accept(goods));
			// handling default values
			goods.id = goods.id == null ? UUID.randomUUID() : goods.id;
			goods.version = goods.version == null ? 0 : goods.version;
			goods.description= goods.description == null ? "" : goods.description;
			goods.createdDate= goods.createdDate == null ? LocalDateTime.now() : goods.createdDate;
			goods.minimumOrderQuantity= goods.minimumOrderQuantity == null ? 1 : goods.minimumOrderQuantity;
			goods.maximumOrderQuantity= goods.maximumOrderQuantity == null ? 1 : goods.maximumOrderQuantity;
			goods.optimumOrderQuantity= goods.optimumOrderQuantity == null ? 1 : goods.optimumOrderQuantity;
			return goods;
		}

		private Builder clone(Goods goods) {
			this.id(goods.id);
			this.version(goods.version);
			this.name(goods.name);
			this.description(goods.description);
			this.createdBy(goods.createdBy);
			this.createdDate(goods.createdDate);
			this.lastModifiedBy(goods.lastModifiedBy);
			this.lastModifiedDate(goods.lastModifiedDate);
			this.deprecatedDate(goods.deprecatedDate);
			this.obsoleteDate(goods.obsoleteDate);
			this.defaultLocation(goods.defaultLocation);
			this.minimumOrderQuantity(goods.minimumOrderQuantity);
			this.maximumOrderQuantity(goods.maximumOrderQuantity);
			this.optimumOrderQuantity(goods.optimumOrderQuantity);
			this.tags(goods.tags);
			return this;
		}
	}

	
	
	public Optional<UUID> getLastModifiedBy(){
		return Optional.ofNullable(this.lastModifiedBy);
	}
	public Optional<LocalDateTime> getLastModifiedDate(){
		return Optional.ofNullable(this.lastModifiedDate);
	}
	public Optional<LocalDateTime> getDeprecatedDate(){
		return Optional.ofNullable(this.deprecatedDate);
	}
	public Optional<LocalDateTime> getObsoleteDate(){
		return Optional.ofNullable(this.obsoleteDate);
	}
	public Optional<Location> getDefaultLocation() {
		return Optional.ofNullable(this.defaultLocation);
	}
	public Set<Tag> getTags() {
		return Collections.unmodifiableSet(this.tags);
	}
	
	public boolean isDeprecated() {
		return isDeprecatedFrom(LocalDateTime.now());
	}
	public boolean isDeprecatedFrom(LocalDateTime referenceDatetime) {
		return this.getDeprecatedDate().orElse(LocalDateTime.MAX).isBefore(referenceDatetime);
	}
	public boolean isObsolete() {
		return isObsoleteFrom(LocalDateTime.now());
	}
	public boolean isObsoleteFrom(LocalDateTime referenceDatetime) {
		return this.getObsoleteDate().orElse(LocalDateTime.MAX).isBefore(referenceDatetime);
	}

	public static GoodsNameBuilder builder() {
		return new Goods.Builder();
	}
	public Builder toBuilder() {
		return new Goods.Builder().clone(this);
	}

}
