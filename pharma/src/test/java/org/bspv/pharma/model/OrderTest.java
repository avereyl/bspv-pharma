package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import org.bspv.pharma.model.OrderEvent.OrderEventType;
import org.junit.Test;

public class OrderTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        // when
        final Order order = Order.builder().createdBy(testID).build();
        // then
        assertNotNull(order.getId());
        assertNotNull(order.getVersion());
        assertNotNull(order.getCreatedDate());
        assertNotNull(order.getInternalComment());
        assertNotNull(order.getExternalComment());
        assertNotNull(order.getItems());
        assertNotNull(order.getExtraItems());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final String internalComment = "internalComment";
        final String externalComment = "externalComment";
        final Map<Goods, Integer> items = new HashMap<>();
        final Goods goods = Goods.builder().name("goods").createdBy(testID).build();
        final Integer quantity = 1;
        final Map<String, Integer> extraItems = new HashMap<>();
        final OrderEvent event = OrderEvent.builder().type(OrderEventType.VALIDATION).createdBy(testID).build();
        // when
        // @formatter:off
		final Order order = Order.builder()
				.createdBy(testID)
				.id(id)
				.version(version)
				.createdDate(createdDate)
				.internalComment(internalComment)
				.externalComment(externalComment)
				.items()
				.items(items)
				.item(goods, quantity)
				.extraItems()
				.extraItems(extraItems)
				.extraItem("extra items", quantity)
				.events()
				.events(new HashSet<>())
				.event(event)
				.build();
		// @formatter:on
        // then
        assertEquals(id, order.getId());
        assertEquals(version, order.getVersion());
        assertEquals(createdDate, order.getCreatedDate());
        assertEquals(internalComment, order.getInternalComment());
        assertEquals(externalComment, order.getExternalComment());

        assertEquals(1, order.getItems().size());
        assertTrue(order.getItems().containsKey(goods));
        assertEquals(quantity, order.getItems().get(goods));
        assertEquals(1, order.getExtraItems().size());
        assertEquals(1, order.getEvents().size());
        assertEquals(event, order.getEvents().stream().findFirst().get());

    }

    @Test
    public void copyBuildingTest() {
        // given
        // when
        final Order order = Order.builder().createdBy(testID).build();
        final Order orderCopy = order.toBuilder().build();
        // then
        assertEquals(order, orderCopy);
        assertEquals(order.getId(), orderCopy.getId());
        assertEquals(order.getVersion(), orderCopy.getVersion());
        assertEquals(order.getCreatedDate(), orderCopy.getCreatedDate());
        assertEquals(order.getInternalComment(), orderCopy.getInternalComment());
        assertEquals(order.getExternalComment(), orderCopy.getExternalComment());
        assertEquals(order.getItems(), orderCopy.getItems());
        assertEquals(order.getExtraItems(), orderCopy.getExtraItems());
    }

    @Test
    public void nullExtraElementBuildingTest() {
        // given
        // when
        try {
            Order.builder().createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).version(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).createdDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).internalComment(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).externalComment(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).item(null, 1).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            Order.builder().createdBy(testID).item(Goods.builder().name("").createdBy(testID).build(), null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).items(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).extraItem(null, 1).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).extraItem("", null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Order.builder().createdBy(testID).extraItems(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

}
