package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

public class OrderTest {

	@Test
	public void minimalBuildingTest() {
		// given
		// when
		Order order = Order.builder().build();
		// then
		assertNotNull(order.getId());
		assertNotNull(order.getCreatedDate());
		assertNotNull(order.getInternalComment());
		assertNotNull(order.getExternalComment());
		assertNotNull(order.getItems());
		assertNotNull(order.getExtraItems());
		assertFalse(order.getSentDate().isPresent());
		assertFalse(order.getReceivedDate().isPresent());
		assertFalse(order.getValidatedDate().isPresent());
		assertFalse(order.getUserResponsibleForCreation().isPresent());
		assertFalse(order.getUserResponsibleForValidation().isPresent());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		UUID id = UUID.randomUUID();
		LocalDateTime createdDate = LocalDateTime.now();
		String internalComment = "internalComment";
		String externalComment = "externalComment";
		Map<Goods, Integer> items = new HashMap<>();
		Goods goods = Goods.builder().name("goods").build();
		Integer quantity = 1;
		Map<String, Integer> extraItems = new HashMap<>();
		LocalDateTime sentDate = LocalDateTime.now();
		LocalDateTime receivedDate = LocalDateTime.now();
		LocalDateTime validatedDate = LocalDateTime.now();
		UUID userResponsibleForCreation = UUID.randomUUID();
		UUID userResponsibleForValidation = UUID.randomUUID();
		// when
		// @formatter:off
		Order order = Order.builder()
				.id(id)
				.createdDate(createdDate)
				.internalComment(internalComment)
				.externalComment(externalComment)
				.items()
				.items(items)
				.item(goods, quantity)
				.extraItems()
				.extraItems(extraItems)
				.extraItem("extra items", quantity)
				.sentDate(sentDate)
				.receivedDate(receivedDate)
				.validatedDate(validatedDate)
				.userResponsibleForCreation(userResponsibleForCreation)
				.userResponsibleForValidation(userResponsibleForValidation)
				.build();
		// @formatter:on
		// then
		assertEquals(id, order.getId());
		assertEquals(createdDate, order.getCreatedDate());
		assertEquals(internalComment, order.getInternalComment());
		assertEquals(externalComment, order.getExternalComment());
		
		assertEquals(1, order.getItems().size());
		assertTrue(order.getItems().containsKey(goods));
		assertEquals(quantity, order.getItems().get(goods));
		assertEquals(1, order.getExtraItems().size());
		
		assertEquals(sentDate, order.getSentDate().get());
		assertEquals(receivedDate, order.getReceivedDate().get());
		assertEquals(validatedDate, order.getValidatedDate().get());
		assertEquals(userResponsibleForCreation, order.getUserResponsibleForCreation().get());
		assertEquals(userResponsibleForValidation, order.getUserResponsibleForValidation().get());
	}

	@Test
	public void copyBuildingTest() {
		// given
		// when
		Order order = Order.builder().build();
		Order orderCopy = order.toBuilder().build();
		// then
		assertEquals(order.getId(), orderCopy.getId());
		assertEquals(order.getCreatedDate(), orderCopy.getCreatedDate());
		assertEquals(order.getInternalComment(), orderCopy.getInternalComment());
		assertEquals(order.getExternalComment(), orderCopy.getExternalComment());
		assertEquals(order.getItems(), orderCopy.getItems());
		assertEquals(order.getExtraItems(), orderCopy.getExtraItems());
		assertEquals(order.getSentDate(), orderCopy.getSentDate());
		assertEquals(order.getReceivedDate(), orderCopy.getReceivedDate());
		assertEquals(order.getValidatedDate(), orderCopy.getValidatedDate());
		assertEquals(order.getUserResponsibleForCreation(), orderCopy.getUserResponsibleForCreation());
		assertEquals(order.getUserResponsibleForValidation(), orderCopy.getUserResponsibleForValidation());
	}
	
	@Test
	public void nullExtraElementBuildingTest() {
		// given
		// when
		try {
			Order.builder().id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().createdDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().internalComment(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().externalComment(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().item(null, 1).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			Order.builder().item(Goods.builder().name("").build(), null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().items(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().extraItem(null, 1).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().extraItem("", null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Order.builder().extraItems(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}

}
