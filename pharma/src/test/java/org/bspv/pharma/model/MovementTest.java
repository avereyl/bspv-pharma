package org.bspv.pharma.model;

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
	public void MovementMinimalBuildingTest() {
		
		// given
		Integer qty = 10;
		Goods goods = Goods.builder().name("goods").build();
		Location location = Location.builder().name("location").build();

		// when
		Movement movement = Movement.builder().of(goods).quantity(qty).from(location).build();
		
		// then
		assertNotNull("Id should not be null!", movement.getId());
		assertEquals("Goods should be equals", goods, movement.getGoods());
		assertEquals("Quantity should be negative and equals to -qty", new Integer(-1*qty), movement.getQuantity());
		assertEquals("Location should be equals", location, movement.getLocation());
		assertNotNull("CreatedDate should not be null!", movement.getCreatedDate());
		assertEquals("Location should be equals", movement.getCreatedDate(), movement.getValueDate());
		assertEquals("Reason for movement should be UNKNOWN", MovementReason.UNKNOWN, movement.getReason());
		assertFalse("Linked movement should not exist", movement.getLinkedMovement().isPresent());
		assertFalse("Linked order should not exist", movement.getLinkedOrder().isPresent());
	}
	
	@Test
	public void MovementMaximalBuildingTest() {
		
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
		
		// when
		Movement movement = Movement.builder()
				.of(goods)
				.quantity(qty)
				.from(location)
				.id(id)
				.reason(reason)
				.createdDate(createdDate)
				.valueDate(valueDate)
				.linkedMovement(linkedMovement)
				.linkedOrder(linkedOrder)
				.build();
		
		// then
		assertEquals("Id should be equal to given value", id, movement.getId());
		assertEquals("Goods should be equal to given value", goods, movement.getGoods());
		assertEquals("Quantity should be negative and equals to -qty", new Integer(-1*qty), movement.getQuantity());
		assertEquals("Location should be equal to given value", location, movement.getLocation());
		assertEquals("CreatedDate should be equal to given value", createdDate, movement.getCreatedDate());
		assertEquals("Valuedate should be equal to given value", valueDate, movement.getValueDate());
		assertEquals("Reason for movement should be equal to given value", reason, movement.getReason());
		assertTrue("Linked movement should exist", movement.getLinkedMovement().isPresent());
		assertEquals("Linked movement be equal to given value", linkedMovement, movement.getLinkedMovement().get());
		assertTrue("Linked order should exist", movement.getLinkedOrder().isPresent());
		assertEquals("Linked order be equal to given value", linkedOrder, movement.getLinkedOrder().get());
	}
	
}
