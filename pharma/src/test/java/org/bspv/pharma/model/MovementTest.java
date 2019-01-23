package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bspv.pharma.model.Movement.MovementReason;
import org.junit.Test;

public class MovementTest {

	@Test
	public void minimalBuildingTest() {

		// given
		Integer qty = 10;
		Goods goods = Goods.builder().name("goods").build();
		Location location = Location.builder().name("location").build();

		// when
		Movement movement = Movement.builder().of(goods).quantity(qty).from(location).build();

		// then
		assertNotNull("Id should not be null!", movement.getId());
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
		Goods goods = Goods.builder().name("Goods").build();
		Integer qty = 10;
		Location location = Location.builder().name("location").build();
		UUID id = UUID.randomUUID();
		MovementReason reason = MovementReason.CONSUMPTION;
		LocalDateTime createdDate = LocalDateTime.now();
		LocalDateTime valueDate = LocalDateTime.now();
		Movement linkedMovement = Movement.builder().of(goods).quantity(qty).from(location).build();
		Order linkedOrder = Order.builder().build();
		UUID responsibleUser = UUID.randomUUID();

		// when
		Movement movement = Movement.builder().of(goods).quantity(qty).from(location).id(id).reason(reason)
				.createdDate(createdDate).valueDate(valueDate).linkedMovement(linkedMovement).linkedOrder(linkedOrder)
				.responsibleUser(responsibleUser).build();

		// then
		assertEquals("Id should be equal to given value", id, movement.getId());
		assertEquals("Goods should be equal to given value", goods, movement.getGoods());
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

		Integer qty = 10;
		Goods goods = Goods.builder().name("goods").build();
		Location location = Location.builder().name("location").build();

		// when
		Movement movement = Movement.builder().of(goods).quantity(qty).to(location).build();
		Movement movementCopy = movement.toBuilder().build();

		// then
		assertEquals("Both should be equal!", movementCopy.getId(), movement.getId());
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
		Integer qty = 10;
		Goods goods = Goods.builder().name("goods").build();
		Location location = Location.builder().name("location").build();

		// when
		try {
			Movement.builder().of(goods).quantity(qty).from(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(goods).quantity(qty).to(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(goods).quantity(null).from(location).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(null).quantity(qty).from(location).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}

	}
	@Test
	public void nullExtraElementBuildingTest() {
		
		// given
		Integer qty = 10;
		Goods goods = Goods.builder().name("goods").build();
		Location location = Location.builder().name("location").build();
		
		// when
		try {
			Movement.builder().of(goods).quantity(qty).from(location).id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(goods).quantity(qty).from(location).reason(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(goods).quantity(qty).from(location).createdDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Movement.builder().of(goods).quantity(qty).from(location).valueDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		
	}

}
