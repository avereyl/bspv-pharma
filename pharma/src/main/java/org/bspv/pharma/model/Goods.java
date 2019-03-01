/**
 *
 */
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

    private Goods() {// use builder instead
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

    private UUID createdBy;// might be null (before persisted)
    private OffsetDateTime createdDate;// might be null (before persisted)
    private UUID lastModifiedBy;// might be null
    private OffsetDateTime lastModifiedDate;// might be null

    @Getter
    private String name;
    @Getter
    private String description;

    private OffsetDateTime deprecatedDate;// might be null
    private OffsetDateTime obsoleteDate;// might be null
    private Location defaultLocation;// might be null

    @Getter
    private Integer minimumOrderQuantity;
    @Getter
    private Integer maximumOrderQuantity;
    @Getter
    private Integer optimumOrderQuantity;

    private final Set<Tag> tags = new HashSet<>();// getter return a RO collection

    public static interface GoodsNameBuilder {
        GoodsBuilder name(@NonNull String name);
    }

    public static interface GoodsBuilder {
        GoodsBuilder id(@NonNull UUID id);

        GoodsBuilder version(@NonNull Long version);

        GoodsBuilder description(@NonNull String description);

        GoodsBuilder createdBy(@NonNull UUID createdBy);

        GoodsBuilder createdDate(@NonNull OffsetDateTime createdDate);

        GoodsBuilder lastModifiedBy(UUID uuid);

        GoodsBuilder lastModifiedDate(OffsetDateTime lastUpdatedDate);

        GoodsBuilder deprecatedDate(OffsetDateTime deprecatedDate);

        GoodsBuilder obsoleteDate(OffsetDateTime obsoleteDate);

        GoodsBuilder defaultLocation(Location defaultLocation);

        GoodsBuilder minimumOrderQuantity(@NonNull Integer minimumOrderQuantity);

        GoodsBuilder maximumOrderQuantity(@NonNull Integer maximumOrderQuantity);

        GoodsBuilder optimumOrderQuantity(@NonNull Integer optimumOrderQuantity);

        GoodsBuilder tags();

        GoodsBuilder tag(@NonNull Tag tag);

        GoodsBuilder tags(@NonNull Set<Tag> tags);

        Goods build();
    }

    public static class Builder implements GoodsNameBuilder, GoodsBuilder {

        private final List<Consumer<Goods>> operations = new ArrayList<>();

        private Builder() {
        }

        @Override
        public Builder id(@NonNull final UUID id) {
            this.operations.add(g -> g.id = id);
            return this;
        }

        @Override
        public Builder version(@NonNull final Long version) {
            this.operations.add(o -> o.version = version);
            return this;
        }

        @Override
        public Builder description(@NonNull final String description) {
            this.operations.add(g -> g.description = description);
            return this;
        }

        @Override
        public Builder createdBy(@NonNull final UUID createdBy) {
            this.operations.add(g -> g.createdBy = createdBy);
            return this;
        }

        @Override
        public Builder createdDate(@NonNull final OffsetDateTime createdDate) {
            this.operations.add(g -> g.createdDate = createdDate);
            return this;
        }

        @Override
        public Builder lastModifiedBy(final UUID lastModifiedBy) {
            this.operations.add(g -> g.lastModifiedBy = lastModifiedBy);
            return this;
        }

        @Override
        public Builder lastModifiedDate(final OffsetDateTime lastModifiedDate) {
            this.operations.add(g -> g.lastModifiedDate = lastModifiedDate);
            return this;
        }

        @Override
        public Builder deprecatedDate(final OffsetDateTime deprecatedDate) {
            this.operations.add(g -> g.deprecatedDate = deprecatedDate);
            return this;
        }

        @Override
        public Builder obsoleteDate(final OffsetDateTime obsoleteDate) {
            this.operations.add(g -> g.obsoleteDate = obsoleteDate);
            return this;
        }

        @Override
        public Builder defaultLocation(final Location defaultLocation) {
            this.operations.add(g -> g.defaultLocation = defaultLocation);
            return this;
        }

        @Override
        public Builder minimumOrderQuantity(@NonNull final Integer minimumOrderQuantity) {
            this.operations.add(g -> g.minimumOrderQuantity = minimumOrderQuantity);
            return this;
        }

        @Override
        public GoodsBuilder maximumOrderQuantity(@NonNull final Integer maximumOrderQuantity) {
            this.operations.add(g -> g.maximumOrderQuantity = maximumOrderQuantity);
            return this;
        }

        @Override
        public Builder optimumOrderQuantity(@NonNull final Integer optimumOrderQuantity) {
            this.operations.add(g -> g.optimumOrderQuantity = optimumOrderQuantity);
            return this;
        }

        @Override
        public Builder tag(@NonNull final Tag tag) {
            this.operations.add(g -> g.tags.add(tag));
            return this;
        }

        @Override
        public Builder tags() {
            this.operations.add(g -> g.tags.clear());
            return this;
        }

        @Override
        public Builder tags(@NonNull final Set<Tag> tags) {
            this.operations.add(g -> g.tags.addAll(tags));
            return this;
        }

        @Override
        public Builder name(@NonNull final String name) {
            this.operations.add(g -> g.name = name);
            return this;
        }

        /**
         *
         * @return new instance of {@link Goods}
         */
        @Override
        public Goods build() {
            final Goods goods = new Goods();
            this.operations.forEach(c -> c.accept(goods));
            // handling default values
            goods.id = goods.id == null ? UUID.randomUUID() : goods.id;
            goods.version = goods.version == null ? 0 : goods.version;
            goods.description = goods.description == null ? "" : goods.description;
            goods.createdDate = goods.createdDate == null ? OffsetDateTime.now() : goods.createdDate;
            goods.minimumOrderQuantity = goods.minimumOrderQuantity == null ? 1 : goods.minimumOrderQuantity;
            goods.maximumOrderQuantity = goods.maximumOrderQuantity == null ? 1 : goods.maximumOrderQuantity;
            goods.optimumOrderQuantity = goods.optimumOrderQuantity == null ? 1 : goods.optimumOrderQuantity;
            return goods;
        }

        private Builder clone(final Goods goods) {
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

    public Optional<UUID> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    public Optional<OffsetDateTime> getCreatedDate() {
        return Optional.ofNullable(this.createdDate);
    }

    public Optional<UUID> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    public Optional<OffsetDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    public Optional<OffsetDateTime> getDeprecatedDate() {
        return Optional.ofNullable(this.deprecatedDate);
    }

    public Optional<OffsetDateTime> getObsoleteDate() {
        return Optional.ofNullable(this.obsoleteDate);
    }

    public Optional<Location> getDefaultLocation() {
        return Optional.ofNullable(this.defaultLocation);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    public boolean isDeprecated() {
        return this.isDeprecatedFrom(OffsetDateTime.now());
    }

    public boolean isDeprecatedFrom(final OffsetDateTime referenceDatetime) {
        return this.getDeprecatedDate().orElse(OffsetDateTime.MAX).isBefore(referenceDatetime);
    }

    public boolean isObsolete() {
        return this.isObsoleteFrom(OffsetDateTime.now());
    }

    public boolean isObsoleteFrom(final OffsetDateTime referenceDatetime) {
        return this.getObsoleteDate().orElse(OffsetDateTime.MAX).isBefore(referenceDatetime);
    }

    public static GoodsNameBuilder builder() {
        return new Goods.Builder();
    }

    public Builder toBuilder() {
        return new Goods.Builder().clone(this);
    }

}
