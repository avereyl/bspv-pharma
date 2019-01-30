package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bspv.pharma.model.AdditionalDetails.DetailsType;
import org.junit.Test;

public class StockPositionTest {

	@Test
	public void minimalBuildingTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		// when
		StockPosition position = StockPosition.builder().location(location).goods(goods).build();
		// then
		Integer defaultValue = new Integer(0);
		assertEquals(location, position.getLocation());
		assertEquals(goods, position.getGoods());
		assertEquals(defaultValue, position.getCurrent());
		assertEquals(defaultValue, position.getMinimum());
		assertEquals(defaultValue, position.getMaximum());
		assertEquals(defaultValue, position.getOptimum());
		assertEquals(StockPosition.StockPostitionType.COMPUTED, position.getType());
		assertNotNull(position.getId());
		assertNotNull(position.getVersion());
		assertNotNull(position.getCreatedDate());
		assertNotNull(position.getValueDate());
		assertNotNull(position.getAdditionalDetails());
		assertTrue(position.getAdditionalDetails().isEmpty());
		assertFalse(position.getResponsibleUser().isPresent());
		assertFalse(position.getLinkedInventory().isPresent());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		UUID id = UUID.randomUUID();
		Long version = 1L;
		LocalDateTime createdDate = LocalDateTime.now();
		LocalDateTime valueDate = LocalDateTime.now();
		Integer current = 10;
		Integer minimum = 5;
		Integer maximum = 20;
		Integer optimum = 15;
		AdditionalDetails additionalDetail = AdditionalDetails.builder().type(DetailsType.MISC).build();
		UUID responsibleUser = UUID.randomUUID();
		// when
		// @formatter:off
		StockPosition position = StockPosition.builder().location(location).goods(goods).id(id).version(version).createdDate(createdDate)
				.valueDate(valueDate).current(current).minimum(minimum).maximum(maximum).optimum(optimum).pending()
				.computed().checked().additionalDetails().additionalDetails(new HashSet<>())
				.additionalDetail(additionalDetail).responsibleUser(responsibleUser).build();
		// @formatter:on
		// then
		assertEquals(location, position.getLocation());
		assertEquals(goods, position.getGoods());
		assertEquals(current, position.getCurrent());
		assertEquals(minimum, position.getMinimum());
		assertEquals(maximum, position.getMaximum());
		assertEquals(optimum, position.getOptimum());
		assertEquals(StockPosition.StockPostitionType.CHECKED, position.getType());
		assertEquals(id, position.getId());
		assertEquals(version, position.getVersion());
		assertEquals(createdDate, position.getCreatedDate());
		assertEquals(valueDate, position.getValueDate());
		assertEquals(1, position.getAdditionalDetails().size());
		assertEquals(additionalDetail, position.getAdditionalDetails().toArray(new AdditionalDetails[0])[0]);
		assertEquals(responsibleUser, position.getResponsibleUser().get());
	}

	@Test
	public void copyBuildingTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		// when
		StockPosition position = StockPosition.builder().location(location).goods(goods).build();
		StockPosition positionCopy = position.toBuilder().build();
		// then
		assertEquals(position, positionCopy);
		assertEquals(position.getLocation(), positionCopy.getLocation());
		assertEquals(position.getGoods(), positionCopy.getGoods());
		assertEquals(position.getCurrent(), positionCopy.getCurrent());
		assertEquals(position.getMinimum(), positionCopy.getMinimum());
		assertEquals(position.getMaximum(), positionCopy.getMaximum());
		assertEquals(position.getOptimum(), positionCopy.getOptimum());
		assertEquals(position.getType(), positionCopy.getType());
		assertEquals(position.getId(), positionCopy.getId());
		assertEquals(position.getVersion(), positionCopy.getVersion());
		assertEquals(position.getCreatedDate(), positionCopy.getCreatedDate());
		assertEquals(position.getValueDate(), positionCopy.getValueDate());
		assertEquals(position.getAdditionalDetails(), positionCopy.getAdditionalDetails());
		assertEquals(position.getResponsibleUser(), positionCopy.getResponsibleUser());
	}

	@Test
	public void nullMandatoryElementBuildingTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		try {
			// when
			StockPosition.builder().location(null).goods(goods).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}

	@Test
	public void nullExtraElementBuildingTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		try {
			// when
			StockPosition.builder().location(location).goods(goods).id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).version(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).minimum(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).maximum(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).optimum(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).current(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).type(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).createdDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).valueDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).additionalDetail(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			StockPosition.builder().location(location).goods(goods).additionalDetails(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}
	
	@Test
	public void mayMovementBeAppliedTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		StockPosition position = StockPosition.builder().location(location).goods(goods).build();
		Movement movement1 = Movement.builder().of(goods).quantity(0).from(location).build();
		Movement movement2 = Movement.builder().of(goods).quantity(1).from(location).build();
		// when
		boolean b1 = position.mayMovementBeApplied(movement1);
		boolean b2 = position.mayMovementBeApplied(movement2);
		// then
		assertTrue("It should be possible to apply the movement to the position", b1);
		assertTrue("It should be possible to apply the movement to the position", b2);
	}

	@Test
	public void canMovementBeAppliedImmediatelyTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(1).build();
		Movement movement1 = Movement.builder().of(goods).quantity(1).from(location).valueDate(now.plusMinutes(1)).build();
		Movement movement2 = movement1.toBuilder().quantity(2).build();
		Movement movement3 = movement1.toBuilder().valueDate(now.minusMinutes(1)).build();
		// when
		boolean b1 = position.canMovementBeAppliedImmediately(movement1);
		boolean b2 = position.canMovementBeAppliedImmediately(movement2);
		boolean b3 = position.canMovementBeAppliedImmediately(movement3);
		// then
		assertTrue("It should be possible to apply the movement to the position", b1);
		assertFalse("It should NOT be possible to apply the movement to the position", b2);
		assertFalse("It should NOT be possible to apply the movement to the position", b3);
	}


	@Test
	public void computeNewPositionSuccessfullyFromOneMovementTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(1)
				.build();
		Movement movement = Movement.builder().of(goods).quantity(1).from(location).valueDate(now.plusMinutes(1)).build();
		// when
		StockPosition newPosition = position.computeNewPosition(movement);
		// then
		assertEquals(new Integer(0), newPosition.getCurrent());
	}
	@Test(expected=IllegalArgumentException.class)
	public void computeNewPositionWithFailureFromOneMovementTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(1)
				.build();
		Movement movement = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();
		// when
		position.computeNewPosition(movement);
		// then
	}

	@Test
	public void computeNewPositionFromSeveralMovementsSuccessfullyTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start).build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();//expect 8
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();//expect 5
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();//expect 13
		Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();//expect 16
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();//expect 6
		
		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		StockPosition newPosition = position.computeNewPosition(movements);
		// then
		assertEquals(new Integer(6), newPosition.getCurrent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void computeNewPositionFromSeveralMovementsWithFailure01Test() {
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start).build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();//expect 8
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(1)).quantity(10).build();//expect failure
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();
		Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();
		
		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		position.computeNewPosition(movements);
		// then
	}
	@Test(expected=IllegalArgumentException.class)
	public void computeNewPositionFromSeveralMovementsWithFailure02Test() {
		// given
		Location location = Location.builder().name("").build();
		Location otherLocation = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start)
				.build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();// expect 8
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// failure
		Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();

		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		position.computeNewPosition(movements);
		// then
	}

	@Test
	public void computeNewPositionSilentlySuccessfullyFromOneMovementTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(1)
				.build();
		Movement movement = Movement.builder().of(goods).quantity(1).from(location).valueDate(now.plusMinutes(1)).build();
		// when
		StockPosition newPosition = position.computeNewPositionSilently(movement);
		// then
		assertEquals(new Integer(0), newPosition.getCurrent());
	}
	
	@Test
	public void computeNewPositionSilentlyWithFailureFromOneMovementTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(1)
				.build();
		Movement movement = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();
		// when
		StockPosition newPosition = position.computeNewPositionSilently(movement);
		// then
		assertEquals(position.getCurrent(), newPosition.getCurrent());
	}

	@Test
	public void computeNewPositionSilentlyFromSeveralMovementsSuccessfullyTest() {
		// given
		Location location = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start).build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();//expect 8
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();//expect 5
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();//expect 13
		Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();//expect 16
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();//expect 6

		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		StockPosition newPosition = position.computeNewPositionSilently(movements);
		// then
		assertEquals(new Integer(6), newPosition.getCurrent());
	}
	@Test
	public void computeNewPositionSilentlyFromSeveralMovementsWithFailureIgnoredTest() {
		// given
		Location location = Location.builder().name("").build();
		Location otherLocation = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start)
				.build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();// expect 8
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// ignored
		Movement m3 = m0.toBuilder().valueDate(now.plusMinutes(4)).to(location).quantity(3).build();// expect 8
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(8).build();// expect 0

		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		StockPosition newPosition = position.computeNewPositionSilently(movements);
		// then
		assertEquals(new Integer(0), newPosition.getCurrent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void computeNewPositionSilentlyFromSeveralMovementsWithFailureTest() {
		// given
		Location location = Location.builder().name("").build();
		Location otherLocation = Location.builder().name("").build();
		Goods goods = Goods.builder().name("").build();
		LocalDateTime now = LocalDateTime.now();
		Integer start = 10;
		StockPosition position = StockPosition.builder().location(location).goods(goods).valueDate(now).current(start)
				.build();
		List<Movement> movements = new ArrayList<>();
		Movement m0 = Movement.builder().of(goods).quantity(2).from(location).valueDate(now.plusMinutes(1)).build();// expect 8
		Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(9).build();// failure
		Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// ignored
		Movement m3 = m0.toBuilder().valueDate(now.plusMinutes(4)).to(location).quantity(3).build();
		Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(8).build();
		
		movements.add(m0);
		movements.add(m1);
		movements.add(m2);
		movements.add(m3);
		movements.add(m4);
		// when
		StockPosition newPosition = position.computeNewPositionSilently(movements);
		// then
		assertEquals(new Integer(0), newPosition.getCurrent());
	}

}
