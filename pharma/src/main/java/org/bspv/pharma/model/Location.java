package org.bspv.pharma.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = { "id" })
public final class Location implements Serializable {

    /**
     * Generated Serial UID.
     */
    private static final long serialVersionUID = 4950494554646408104L;

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

    @Getter
    private String code;
    @Getter
    private String name;
    @Getter
    private String description;

    private OffsetDateTime obsoleteDate;// might be null

    private Location() {
        // use builder instead
    }

    public static interface LocationNameBuilder {
        LocationCreatedByBuilder name(@NonNull String name);
    }

    public static interface LocationCreatedByBuilder {
        LocationBuilder createdBy(@NonNull UUID createdBy);
    }

    public static interface LocationBuilder {
        LocationBuilder id(@NonNull final UUID id);

        LocationBuilder version(@NonNull final Long version);

        LocationBuilder lastModifiedBy(UUID uuid);

        LocationBuilder lastModifiedDate(OffsetDateTime lastUpdatedDate);

        LocationBuilder code(@NonNull final String code);

        LocationBuilder description(@NonNull final String description);

        LocationBuilder createdDate(@NonNull final OffsetDateTime createdDate);

        LocationBuilder obsoleteDate(final OffsetDateTime obsoleteDate);

        Location build();
    }

    public static final class Builder implements LocationNameBuilder, LocationCreatedByBuilder, LocationBuilder {

        private final List<Consumer<Location>> operations = new ArrayList<>();

        @Override
        public Builder id(@NonNull final UUID id) {
            this.operations.add(l -> l.id = id);
            return this;
        }

        @Override
        public Builder version(@NonNull final Long version) {
            this.operations.add(l -> l.version = version);
            return this;
        }

        @Override
        public Builder code(@NonNull final String code) {
            this.operations.add(l -> l.code = code);
            return this;
        }

        @Override
        public Builder description(@NonNull final String description) {
            this.operations.add(l -> l.description = description);
            return this;
        }

        @Override
        public Builder createdBy(@NonNull final UUID createdBy) {
            this.operations.add(l -> l.createdBy = createdBy);
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
        public Builder createdDate(@NonNull final OffsetDateTime createdDate) {
            this.operations.add(l -> l.createdDate = createdDate);
            return this;
        }

        @Override
        public Builder obsoleteDate(final OffsetDateTime obsoleteDate) {
            this.operations.add(l -> l.obsoleteDate = obsoleteDate);
            return this;
        }

        @Override
        public Builder name(@NonNull final String name) {
            this.operations.add(l -> l.name = name);
            return this;
        }

        @Override
        public Location build() {
            final Location location = new Location();
            this.operations.forEach(c -> c.accept(location));
            // handling default values
            location.id = location.id == null ? UUID.randomUUID() : location.id;
            location.version = location.version == null ? 0L : location.version;
            location.code = location.code == null ? location.id.toString().substring(24) : location.code;
            location.description = location.description == null ? "" : location.description;
            location.createdDate = location.createdDate == null ? OffsetDateTime.now() : location.createdDate;
            return location;
        }

        private Builder clone(final Location location) {
            this.id(location.id);
            this.createdBy(location.createdBy);
            this.createdDate(location.createdDate);
            this.lastModifiedBy(location.lastModifiedBy);
            this.lastModifiedDate(location.lastModifiedDate);
            this.version(location.version);
            this.code(location.code);
            this.name(location.name);
            this.description(location.description);
            this.obsoleteDate(location.obsoleteDate);
            return this;
        }
    }

    public Optional<UUID> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    public Optional<OffsetDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    public Optional<OffsetDateTime> getObsoleteDate() {
        return Optional.ofNullable(this.obsoleteDate);
    }

    public boolean isObsolete() {
        return this.isObsoleteFrom(OffsetDateTime.now());
    }

    public boolean isObsoleteFrom(final OffsetDateTime referenceDatetime) {
        return this.getObsoleteDate().orElse(OffsetDateTime.MAX).isBefore(referenceDatetime);
    }

    public static LocationNameBuilder builder() {
        return new Location.Builder();
    }

    public Builder toBuilder() {
        return new Location.Builder().clone(this);
    }

}
