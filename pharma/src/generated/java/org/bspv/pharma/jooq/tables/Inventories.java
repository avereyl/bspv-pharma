/*
 * This file is generated by jOOQ.
 */
package org.bspv.pharma.jooq.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.bspv.pharma.jooq.Indexes;
import org.bspv.pharma.jooq.Keys;
import org.bspv.pharma.jooq.Public;
import org.bspv.pharma.jooq.tables.records.InventoriesRecord;
import org.bspv.pharma.repository.jooq.converter.OffsetDateTimeConverter;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Inventories extends TableImpl<InventoriesRecord> {

    private static final long serialVersionUID = -1152621409;

    /**
     * The reference instance of <code>public.inventories</code>
     */
    public static final Inventories INVENTORIES = new Inventories();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InventoriesRecord> getRecordType() {
        return InventoriesRecord.class;
    }

    /**
     * The column <code>public.inventories.id</code>.
     */
    public final TableField<InventoriesRecord, UUID> ID = createField("id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.inventories.version</code>.
     */
    public final TableField<InventoriesRecord, Long> VERSION = createField("version", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.inventories.created_by</code>.
     */
    public final TableField<InventoriesRecord, UUID> CREATED_BY = createField("created_by", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.inventories.created_date</code>.
     */
    public final TableField<InventoriesRecord, OffsetDateTime> CREATED_DATE = createField("created_date", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new OffsetDateTimeConverter());

    /**
     * The column <code>public.inventories.last_modified_by</code>.
     */
    public final TableField<InventoriesRecord, UUID> LAST_MODIFIED_BY = createField("last_modified_by", org.jooq.impl.SQLDataType.UUID, this, "");

    /**
     * The column <code>public.inventories.last_modified_date</code>.
     */
    public final TableField<InventoriesRecord, OffsetDateTime> LAST_MODIFIED_DATE = createField("last_modified_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new OffsetDateTimeConverter());

    /**
     * The column <code>public.inventories.closed_date</code>.
     */
    public final TableField<InventoriesRecord, OffsetDateTime> CLOSED_DATE = createField("closed_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new OffsetDateTimeConverter());

    /**
     * The column <code>public.inventories.closed_by</code>.
     */
    public final TableField<InventoriesRecord, UUID> CLOSED_BY = createField("closed_by", org.jooq.impl.SQLDataType.UUID, this, "");

    /**
     * The column <code>public.inventories.comment</code>.
     */
    public final TableField<InventoriesRecord, String> COMMENT = createField("comment", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>public.inventories</code> table reference
     */
    public Inventories() {
        this(DSL.name("inventories"), null);
    }

    /**
     * Create an aliased <code>public.inventories</code> table reference
     */
    public Inventories(String alias) {
        this(DSL.name(alias), INVENTORIES);
    }

    /**
     * Create an aliased <code>public.inventories</code> table reference
     */
    public Inventories(Name alias) {
        this(alias, INVENTORIES);
    }

    private Inventories(Name alias, Table<InventoriesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Inventories(Name alias, Table<InventoriesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Inventories(Table<O> child, ForeignKey<O, InventoriesRecord> key) {
        super(child, key, INVENTORIES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.INVENTORIES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<InventoriesRecord> getPrimaryKey() {
        return Keys.INVENTORIES_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<InventoriesRecord>> getKeys() {
        return Arrays.<UniqueKey<InventoriesRecord>>asList(Keys.INVENTORIES_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventories as(String alias) {
        return new Inventories(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventories as(Name alias) {
        return new Inventories(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventories rename(String name) {
        return new Inventories(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Inventories rename(Name name) {
        return new Inventories(name, null);
    }
}