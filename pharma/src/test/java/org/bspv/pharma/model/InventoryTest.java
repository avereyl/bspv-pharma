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
	
	private final static UUID testID = UUID.randomUUID();

	@Test
	public void minimalBuildingTest() {
		// given
		// when
		Inventory inventory = Inventory.builder().createdBy(testID).build();
		// then
		assertNotNull(inventory.getId());
		assertNotNull(inventory.getVersion());
		assertNotNull(inventory.getCreatedDate());
		assertNotNull(inventory.getComment());
		assertNotNull(inventory.getPositions());
		assertFalse(inventory.getClosedDate().isPresent());
		assertTrue(inventory.getPositions().isEmpty());
		assertFalse(inventory.isClosed());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		UUID id = UUID.randomUUID();
		Long version = 1L;
		LocalDateTime createdDate = LocalDateTime.now();
		String comment = "comment";
		Location location = Location.builder().name("location").createdBy(testID).build();
		Goods goods = Goods.builder().name("goods").createdBy(testID).build();
		StockPosition position = StockPosition.builder()
				.location(location)
				.goods(goods)
				.createdBy(testID)
				.build();
		LocalDateTime closedDate = LocalDateTime.now();

		// when
		// @formatter:off
		Inventory inventory = Inventory.builder().createdBy(testID).id(id).version(version).createdDate(createdDate).comment(comment).positions()
				.positions(new HashSet<>()).position(position).closedDate(closedDate)
				.build();
		// @formatter:on
		// then
		assertEquals(id, inventory.getId());
		assertEquals(version, inventory.getVersion());
		assertEquals(createdDate, inventory.getCreatedDate());
		assertEquals(comment, inventory.getComment());
		assertEquals(1, inventory.getPositions().size());
		assertEquals(position, inventory.getPositions().toArray(new StockPosition[0])[0]);
		assertEquals(closedDate, inventory.getClosedDate().get());
		assertEquals(testID, inventory.getCreatedBy());
	}

	@Test
	public void copyBuildingTest() {
		// given
		// when
		Inventory inventory = Inventory.builder().createdBy(testID).build();
		Inventory inventoryCopy = inventory.toBuilder().build();
		// then
		assertEquals(inventory, inventoryCopy);
		assertEquals(inventory.getId(), inventoryCopy.getId());
		assertEquals(inventory.getVersion(), inventoryCopy.getVersion());
		assertEquals(inventory.getCreatedDate(), inventoryCopy.getCreatedDate());
		assertEquals(inventory.getComment(), inventoryCopy.getComment());
		assertEquals(inventory.getPositions(), inventoryCopy.getPositions());
		assertEquals(inventory.getClosedDate(), inventoryCopy.getClosedDate());
	}

	@Test
	public void nullExtraElementBuildingTest() {
		// given
		// when
		try {
			Inventory.builder().createdBy(testID).id(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdBy(testID).version(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdBy(testID).createdDate(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdBy(testID).comment(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdBy(testID).position(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Inventory.builder().createdBy(testID).positions(null).build();
			// then
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}
}
