package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.bspv.pharma.model.Movement.MovementReason;
import org.junit.Test;

public class MovementTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {

        // given
        final Integer qty = 10;
        final Goods goods = Goods.builder().name("goods").createdBy(testID).build();
        final Location location = Location.builder().name("location").createdBy(testID).build();

        // when
        final Movement movement = Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).build();

        // then
        assertNotNull("Id should not be null!", movement.getId());
        assertNotNull("Version should not be null!", movement.getVersion());
        assertEquals("Goods should be equals", goods, movement.getGoods());
        assertEquals("Quantity should be negative and equals to -qty", new Integer(-1 * qty), movement.getQuantity());
        assertEquals("Location should be equals", location, movement.getLocation());
        assertNotNull("CreatedDate should not be null!", movement.getCreatedDate());
        assertEquals("Location should be equals", movement.getCreatedDate(), movement.getValueDate());
        assertEquals("Reason for movement should be UNKNOWN", MovementReason.UNKNOWN, movement.getReason());
        assertFalse("Linked movement should not exist", movement.getLinkedMovement().isPresent());
        assertFalse("Linked order should not exist", movement.getLinkedOrder().isPresent());
    }

    @Test
    public void maximalBuildingTest() {

        // given
        final Goods goods = Goods.builder().name("Goods").createdBy(testID).build();
        final Integer qty = 10;
        final Location location = Location.builder().name("location").createdBy(testID).build();
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final MovementReason reason = MovementReason.CONSUMPTION;
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final OffsetDateTime valueDate = OffsetDateTime.now();
        final Movement linkedMovement = Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID)
                .build();
        final Order linkedOrder = Order.builder().createdBy(testID).build();
        final UUID responsibleUser = UUID.randomUUID();

        // when
        // @formatter:off
		final Movement movement = Movement.builder()
				.of(goods)
				.quantity(qty)
				.from(location)
				.createdBy(testID)
				.id(id)
				.version(version)
				.reason(reason)
				.createdDate(createdDate)
				.valueDate(valueDate)
				.linkedMovement(linkedMovement)
				.linkedOrder(linkedOrder)
				.responsibleUser(responsibleUser)
				.build();
		// @formatter:on

        // then
        assertEquals("Id should be equal to given value", id, movement.getId());
        assertEquals("Version should be equal to given value", version, movement.getVersion());
        assertEquals("Quantity should be negative and equals to -qty", new Integer(-1 * qty), movement.getQuantity());
        assertEquals("Location should be equal to given value", location, movement.getLocation());
        assertEquals("CreatedDate should be equal to given value", createdDate, movement.getCreatedDate());
        assertEquals("Valuedate should be equal to given value", valueDate, movement.getValueDate());
        assertEquals("Reason for movement should be equal to given value", reason, movement.getReason());
        assertTrue("Linked movement should exist", movement.getLinkedMovement().isPresent());
        assertEquals("Linked movement be equal to given value", linkedMovement, movement.getLinkedMovement().get());
        assertTrue("Linked order should exist", movement.getLinkedOrder().isPresent());
        assertEquals("Linked order should be equal to given value", linkedOrder, movement.getLinkedOrder().get());
        assertEquals("Responsible user id should equal to given value", responsibleUser,
                movement.getResponsibleUser().get());
    }

    @Test
    public void copyBuildingTest() {

        final Integer qty = 10;
        final Goods goods = Goods.builder().name("goods").createdBy(testID).build();
        final Location location = Location.builder().name("location").createdBy(testID).build();

        // when
        final Movement movement = Movement.builder().of(goods).quantity(qty).to(location).createdBy(testID).build();
        final Movement movementCopy = movement.toBuilder().build();

        // then
        assertEquals(movementCopy, movement);
        assertEquals("Both should be equal!", movementCopy.getId(), movement.getId());
        assertEquals("Both should be equal!", movementCopy.getVersion(), movement.getVersion());
        assertEquals("Both should be equal!", movementCopy.getGoods(), movement.getGoods());
        assertEquals("Both should be equal!", movementCopy.getQuantity(), movement.getQuantity());
        assertEquals("Both should be equal!", movementCopy.getLocation(), movement.getLocation());
        assertEquals("Both should be equal!", movementCopy.getCreatedDate(), movement.getCreatedDate());
        assertEquals("Both should be equal!", movementCopy.getValueDate(), movement.getValueDate());
        assertEquals("Both should be equal!", movementCopy.getReason(), movement.getReason());
        assertEquals("Both should be equal!", movementCopy.getLinkedMovement(), movement.getLinkedMovement());
        assertEquals("Both should be equal!", movementCopy.getLinkedOrder(), movement.getLinkedOrder());
        assertEquals("Both should be equal!", movementCopy.getResponsibleUser(), movement.getResponsibleUser());
    }

    @Test
    public void nullMandatoryElementBuildingTest() {

        // given
        final Integer qty = 10;
        final Goods goods = Goods.builder().name("goods").createdBy(testID).build();
        final Location location = Location.builder().name("location").createdBy(testID).build();

        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(null).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(qty).to(null).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(null).from(location).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(null).quantity(qty).from(location).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }

    }

    @Test
    public void nullExtraElementBuildingTest() {

        // given
        final Integer qty = 10;
        final Goods goods = Goods.builder().name("goods").createdBy(testID).build();
        final Location location = Location.builder().name("location").createdBy(testID).build();

        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).version(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).reason(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).createdDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Movement.builder().of(goods).quantity(qty).from(location).createdBy(testID).valueDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }

    }

}
