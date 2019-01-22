/**
 * 
 */
package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.Getter;
import lombok.NonNull;

/**
 * 
 * @author guillaume
 *
 */
public final class Goods implements Serializable {

	/**
	 * Generated Serial UID.
	 */
	private static final long serialVersionUID = -2516920878572437352L;
	
	public static interface GoodsNameBuilder {
		GoodsBuilder name(@NonNull String name);
	}

	public static interface GoodsBuilder {
		GoodsBuilder id(@NonNull UUID id);
		GoodsBuilder description(@NonNull String description);
		GoodsBuilder createdDate(@NonNull LocalDateTime createdDate);
		GoodsBuilder lastUpdatedDate(LocalDateTime lastUpdatedDate);
		GoodsBuilder deprecatedDate(LocalDateTime deprecatedDate);
		GoodsBuilder obsoleteDate(LocalDateTime obsoleteDate);
		GoodsBuilder defaultLocation(Location defaultLocation);
		GoodsBuilder minimumOrderQuantity(@NonNull Integer minimumOrderQuantity);
		GoodsBuilder maximumOrderQuantity(@NonNull Integer maximumOrderQuantity);
		GoodsBuilder optimumOrderQuantity(@NonNull Integer optimumOrderQuantity);
		GoodsBuilder tag(@NonNull Tag tag);
		GoodsBuilder tags();
		GoodsBuilder tags(@NonNull Set<Tag> tags);
		Goods build();
	}

	public static class Builder implements GoodsNameBuilder, GoodsBuilder {

		private final List<Consumer<Goods>> operations = new ArrayList<>();

		private Builder() {
		}
		
		@Override
		public GoodsBuilder id(@NonNull UUID id) {
			operations.add(g -> g.id = id);
			return this;
		}

		@Override
		public GoodsBuilder description(@NonNull String description) {
			operations.add(g -> g.description = description);
			return this;
		}

		@Override
		public GoodsBuilder createdDate(@NonNull LocalDateTime createdDate) {
			operations.add(g -> g.createdDate = createdDate);
			return this;
		}

		@Override
		public GoodsBuilder lastUpdatedDate(LocalDateTime lastUpdatedDate) {
			operations.add(g -> g.lastUpdatedDate = lastUpdatedDate);
			return this;
		}

		@Override
		public GoodsBuilder deprecatedDate(LocalDateTime deprecatedDate) {
			operations.add(g -> g.deprecatedDate = deprecatedDate);
			return this;
		}

		@Override
		public GoodsBuilder obsoleteDate(LocalDateTime obsoleteDate) {
			operations.add(g -> g.obsoleteDate = obsoleteDate);
			return this;
		}

		@Override
		public GoodsBuilder defaultLocation(Location defaultLocation) {
			operations.add(g -> g.defaultLocation = defaultLocation);
			return this;
		}

		@Override
		public GoodsBuilder minimumOrderQuantity(@NonNull Integer minimumOrderQuantity) {
			operations.add(g -> g.minimumOrderQuantity = minimumOrderQuantity);
			return this;
		}

		@Override
		public GoodsBuilder maximumOrderQuantity(@NonNull Integer maximumOrderQuantity) {
			operations.add(g -> g.maximumOrderQuantity = maximumOrderQuantity);
			return this;
		}

		@Override
		public GoodsBuilder optimumOrderQuantity(@NonNull Integer optimumOrderQuantity) {
			operations.add(g -> g.optimumOrderQuantity = optimumOrderQuantity);
			return this;
		}

		@Override
		public GoodsBuilder tag(@NonNull Tag tag) {
			operations.add(g -> g.getTags().add(tag));
			return this;
		}

		@Override
		public GoodsBuilder tags() {
			operations.add(g -> g.getTags().clear());
			return this;
		}

		@Override
		public GoodsBuilder tags(@NonNull Set<Tag> tags) {
			operations.add(g -> g.getTags().addAll(tags));
			return this;
		}

		@Override
		public GoodsBuilder name(String name) {
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
			goods.description= goods.description == null ? "" : goods.description;
			goods.createdDate= goods.createdDate == null ? LocalDateTime.now() : goods.createdDate;
			goods.minimumOrderQuantity= goods.minimumOrderQuantity == null ? 1 : goods.minimumOrderQuantity;
			goods.maximumOrderQuantity= goods.maximumOrderQuantity == null ? 1 : goods.maximumOrderQuantity;
			goods.optimumOrderQuantity= goods.optimumOrderQuantity == null ? 1 : goods.optimumOrderQuantity;
			return goods;
		}

		public Builder clone(Goods goods) {
			this.id(goods.id);
			this.name(goods.name);
			this.description(goods.description);
			this.createdDate(goods.createdDate);
			this.lastUpdatedDate(goods.lastUpdatedDate);
			this.deprecatedDate(goods.deprecatedDate);
			this.obsoleteDate(goods.obsoleteDate);
			this.defaultLocation(goods.defaultLocation);
			this.minimumOrderQuantity(goods.minimumOrderQuantity);
			this.maximumOrderQuantity(goods.maximumOrderQuantity);
			this.optimumOrderQuantity(goods.optimumOrderQuantity);
			this.tags(goods.getTags());
			return this;
		}
	}

	private Goods() {//use builder instead
	}

	/**
	 * Identifier of this piece of goods.
	 */
	@Getter
	private UUID id;
	@Getter
	private String name;
	@Getter
	private String description;
	@Getter
	private LocalDateTime createdDate;
	
	private LocalDateTime lastUpdatedDate;
	private LocalDateTime deprecatedDate;
	private LocalDateTime obsoleteDate;

	private Location defaultLocation;
	
	@Getter
	private Integer minimumOrderQuantity;
	@Getter
	private Integer maximumOrderQuantity;
	@Getter
	private Integer optimumOrderQuantity;
	@Getter
	private final Set<Tag> tags = new HashSet<>();
	
	public Optional<LocalDateTime> getLastUpdatedDate(){
		return Optional.ofNullable(this.lastUpdatedDate);
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
	
	public boolean isDeprecated() {
		return isDeprecatedAfter(LocalDateTime.now());
	}
	public boolean isDeprecatedAfter(LocalDateTime referenceDatetime) {
		return this.getDeprecatedDate().orElse(LocalDateTime.MAX).isAfter(referenceDatetime);
	}
	public boolean isObsolete() {
		return isObsoleteAfter(LocalDateTime.now());
	}
	public boolean isObsoleteAfter(LocalDateTime referenceDatetime) {
		return this.getObsoleteDate().orElse(LocalDateTime.MAX).isAfter(referenceDatetime);
	}

	public static GoodsNameBuilder builder() {
		return new Goods.Builder();
	}
	public Builder toBuilder() {
		return new Goods.Builder().clone(this);
	}

}
