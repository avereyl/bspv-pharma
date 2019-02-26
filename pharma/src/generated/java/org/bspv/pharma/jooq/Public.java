/*
 * This file is generated by jOOQ.
 */
package org.bspv.pharma.jooq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.bspv.pharma.jooq.tables.Goods;
import org.bspv.pharma.jooq.tables.Inventories;
import org.bspv.pharma.jooq.tables.Locations;
import org.bspv.pharma.jooq.tables.Movements;
import org.bspv.pharma.jooq.tables.OrderExtras;
import org.bspv.pharma.jooq.tables.OrderGoods;
import org.bspv.pharma.jooq.tables.Orders;
import org.bspv.pharma.jooq.tables.OrdersEvents;
import org.bspv.pharma.jooq.tables.PositionDetails;
import org.bspv.pharma.jooq.tables.Positions;
import org.bspv.pharma.jooq.tables.Tags;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 262229891;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.goods</code>.
     */
    public final Goods GOODS = org.bspv.pharma.jooq.tables.Goods.GOODS;

    /**
     * The table <code>public.inventories</code>.
     */
    public final Inventories INVENTORIES = org.bspv.pharma.jooq.tables.Inventories.INVENTORIES;

    /**
     * The table <code>public.locations</code>.
     */
    public final Locations LOCATIONS = org.bspv.pharma.jooq.tables.Locations.LOCATIONS;

    /**
     * The table <code>public.movements</code>.
     */
    public final Movements MOVEMENTS = org.bspv.pharma.jooq.tables.Movements.MOVEMENTS;

    /**
     * The table <code>public.order_extras</code>.
     */
    public final OrderExtras ORDER_EXTRAS = org.bspv.pharma.jooq.tables.OrderExtras.ORDER_EXTRAS;

    /**
     * The table <code>public.order_goods</code>.
     */
    public final OrderGoods ORDER_GOODS = org.bspv.pharma.jooq.tables.OrderGoods.ORDER_GOODS;

    /**
     * The table <code>public.orders</code>.
     */
    public final Orders ORDERS = org.bspv.pharma.jooq.tables.Orders.ORDERS;

    /**
     * The table <code>public.orders_events</code>.
     */
    public final OrdersEvents ORDERS_EVENTS = org.bspv.pharma.jooq.tables.OrdersEvents.ORDERS_EVENTS;

    /**
     * The table <code>public.position_details</code>.
     */
    public final PositionDetails POSITION_DETAILS = org.bspv.pharma.jooq.tables.PositionDetails.POSITION_DETAILS;

    /**
     * The table <code>public.positions</code>.
     */
    public final Positions POSITIONS = org.bspv.pharma.jooq.tables.Positions.POSITIONS;

    /**
     * The table <code>public.tags</code>.
     */
    public final Tags TAGS = org.bspv.pharma.jooq.tables.Tags.TAGS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Goods.GOODS,
            Inventories.INVENTORIES,
            Locations.LOCATIONS,
            Movements.MOVEMENTS,
            OrderExtras.ORDER_EXTRAS,
            OrderGoods.ORDER_GOODS,
            Orders.ORDERS,
            OrdersEvents.ORDERS_EVENTS,
            PositionDetails.POSITION_DETAILS,
            Positions.POSITIONS,
            Tags.TAGS);
    }
}