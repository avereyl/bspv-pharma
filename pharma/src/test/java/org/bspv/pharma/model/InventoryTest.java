package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.UUID;

import org.junit.Test;

public class InventoryTest {

	@Test
	public void minimalBuildingTest() {
		// given
		// when
		Inventory inventory = Inventory.builder().build();
		// then
		assertNotNull(inventory.getId());
		assertNotNull(inventory.getCreatedDate());
		assertNotNull(inventory.getComment());
		assertNotNull(inventory.getPositions());
		assertFalse(inventory.getClosedDate().isPresent());
		assertFalse(inventory.getResponsibleUser().isPresent());
		assertTrue(inventory.getPositions().isEmpty());
		assertFalse(inventory.isClosed());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		UUID id = UUID.randomUUID();
		LocalDateTime createdDate = LocalDateTime.now();
		String comment = "comment";
		Location location = Location.builder().name("location").build();
		Goods goods = Goods.builder().name("goods").build();
		StockPosition position = StockPosition.builder().location(location).goods(goods).build();
		LocalDateTime closedDate = LocalDateTime.now();
		UUID responsibleUser = UUID.randomUUID();

		// when
		// @formatter:off
		Inventory inventory = Inventory.builder().id(id).createdDate(createdDate).comment(comment).positions()
				.positions(new HashSet<>()).position(position).responsibleUser(responsibleUser).closedDate(closedDate)
				.build();
		// @formatter:on
		// then
		assertEquals(id, inventory.getId());
		assertEquals(createdDate, inventory.getCreatedDate());
		assertEquals(comment, inventory.getComment());
		assertEquals(1, inventory.getPositions().size());
		assertEquals(position, inventory.getPositions().toArray(new StockPosition[0])[0]);
		assertEquals(closedDate, inventory.getClosedDate().get());
		assertEquals(responsibleUser, inventory.getResponsibleUser().get());
	}

	@Test
	public void copyBuildingTest() {
		// given
		// when
		Inventory inventory = Inventory.builder().build();
		Inventory inventoryCopy = inventory.toBuilder().build();
		// then
		assertEquals(inventory.getId(), inventoryCopy.getId());
		assertEquals(inventory.getCreatedDate(), inventoryCopy.getCreatedDate());
		assertEquals(inventory.getComment(), inventoryCopy.getComment());
		assertEquals(inventory.getPositions(), inventoryCopy.getPositions());
		assertEquals(inventory.getClosedDate(), inventoryCopy.getClosedDate());
		assertEquals(inventory.getResponsibleUser(), inventoryCopy.getResponsibleUser());
	}

	@Test
	public void nullExtraElementBuildingTest() {
		// given
		// when
		try {
			Inventory.builder().id(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdDate(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().comment(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().position(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().positions(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}
}
