package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bspv.pharma.model.OrderEvent.OrderEventType;
import org.junit.Test;

public class OrderEventTest {

	private final static UUID testID = UUID.randomUUID();

	@Test
	public void minimalBuildingTest() {
		// given
		// when
		OrderEvent event = OrderEvent.builder().type(OrderEventType.PRINTING).createdBy(testID).build();
		// then
		assertNotNull(event.getId());
		assertNotNull(event.getCreatedBy());
		assertNotNull(event.getCreatedDate());
		assertNotNull(event.getType());
	}
	@Test
	public void maximalBuildingTest() {
		// given
		LocalDateTime createdDate = LocalDateTime.now();
		UUID id = UUID.randomUUID();
		// when
		OrderEvent event = OrderEvent.builder()
				.type(OrderEventType.DISPATCH)
				.createdBy(testID)
				.createdDate(createdDate)
				.id(id)
				.build();
		// then
		assertEquals(id, event.getId());
		assertEquals(testID, event.getCreatedBy());
		assertEquals(createdDate, event.getCreatedDate());
		assertEquals(OrderEventType.DISPATCH, event.getType());
	}
	@Test
	public void copyBuildingTest() {
		// given
		LocalDateTime createdDate = LocalDateTime.now();
		UUID id = UUID.randomUUID();
		// when
		OrderEvent event = OrderEvent.builder()
				.type(OrderEventType.RECEPTION)
				.createdBy(testID)
				.createdDate(createdDate)
				.id(id)
				.build();
		OrderEvent eventCopy = event.toBuilder().build();
		// then
		assertEquals(event.getId(), eventCopy.getId());
		assertEquals(event.getCreatedBy(), eventCopy.getCreatedBy());
		assertEquals(event.getCreatedDate(), eventCopy.getCreatedDate());
		assertEquals(event.getType(), eventCopy.getType());
	}
}
