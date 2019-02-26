/*
 * This file is generated by jOOQ.
 */
package org.bspv.pharma.jooq;


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
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index GOODS_PKEY = Indexes0.GOODS_PKEY;
    public static final Index INVENTORIES_PKEY = Indexes0.INVENTORIES_PKEY;
    public static final Index LOCATIONS_PKEY = Indexes0.LOCATIONS_PKEY;
    public static final Index UK_LOCATIONS__1 = Indexes0.UK_LOCATIONS__1;
    public static final Index MOVEMENTS_PKEY = Indexes0.MOVEMENTS_PKEY;
    public static final Index ORDER_EXTRAS_PKEY = Indexes0.ORDER_EXTRAS_PKEY;
    public static final Index ORDER_GOODS_PKEY = Indexes0.ORDER_GOODS_PKEY;
    public static final Index ORDERS_PKEY = Indexes0.ORDERS_PKEY;
    public static final Index ORDERS_EVENTS_PKEY = Indexes0.ORDERS_EVENTS_PKEY;
    public static final Index POSITION_DETAILS_PKEY = Indexes0.POSITION_DETAILS_PKEY;
    public static final Index POSITIONS_PKEY = Indexes0.POSITIONS_PKEY;
    public static final Index TAGS_PKEY = Indexes0.TAGS_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index GOODS_PKEY = Internal.createIndex("goods_pkey", Goods.GOODS, new OrderField[] { Goods.GOODS.ID }, true);
        public static Index INVENTORIES_PKEY = Internal.createIndex("inventories_pkey", Inventories.INVENTORIES, new OrderField[] { Inventories.INVENTORIES.ID }, true);
        public static Index LOCATIONS_PKEY = Internal.createIndex("locations_pkey", Locations.LOCATIONS, new OrderField[] { Locations.LOCATIONS.ID }, true);
        public static Index UK_LOCATIONS__1 = Internal.createIndex("uk_locations__1", Locations.LOCATIONS, new OrderField[] { Locations.LOCATIONS.CODE }, true);
        public static Index MOVEMENTS_PKEY = Internal.createIndex("movements_pkey", Movements.MOVEMENTS, new OrderField[] { Movements.MOVEMENTS.ID }, true);
        public static Index ORDER_EXTRAS_PKEY = Internal.createIndex("order_extras_pkey", OrderExtras.ORDER_EXTRAS, new OrderField[] { OrderExtras.ORDER_EXTRAS.ORDER_ID, OrderExtras.ORDER_EXTRAS.EXTRA }, true);
        public static Index ORDER_GOODS_PKEY = Internal.createIndex("order_goods_pkey", OrderGoods.ORDER_GOODS, new OrderField[] { OrderGoods.ORDER_GOODS.ORDER_ID, OrderGoods.ORDER_GOODS.GOODS_ID }, true);
        public static Index ORDERS_PKEY = Internal.createIndex("orders_pkey", Orders.ORDERS, new OrderField[] { Orders.ORDERS.ID }, true);
        public static Index ORDERS_EVENTS_PKEY = Internal.createIndex("orders_events_pkey", OrdersEvents.ORDERS_EVENTS, new OrderField[] { OrdersEvents.ORDERS_EVENTS.ID }, true);
        public static Index POSITION_DETAILS_PKEY = Internal.createIndex("position_details_pkey", PositionDetails.POSITION_DETAILS, new OrderField[] { PositionDetails.POSITION_DETAILS.ID }, true);
        public static Index POSITIONS_PKEY = Internal.createIndex("positions_pkey", Positions.POSITIONS, new OrderField[] { Positions.POSITIONS.ID }, true);
        public static Index TAGS_PKEY = Internal.createIndex("tags_pkey", Tags.TAGS, new OrderField[] { Tags.TAGS.GOODS_ID, Tags.TAGS.KEY_ }, true);
    }
}