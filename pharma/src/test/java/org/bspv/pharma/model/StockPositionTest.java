package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.bspv.pharma.model.AdditionalDetails.DetailsType;
import org.junit.Test;

public class StockPositionTest {

    private static final UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        // when
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .build();
        // then
        final Integer defaultValue = new Integer(0);
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
        assertFalse(position.getLinkedInventory().isPresent());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final OffsetDateTime valueDate = OffsetDateTime.now();
        final Integer current = 10;
        final Integer minimum = 5;
        final Integer maximum = 20;
        final Integer optimum = 15;
        final AdditionalDetails additionalDetail = AdditionalDetails.builder().type(DetailsType.MISC).createdBy(testID)
                .build();
        // when
        // @formatter:off
		final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID).id(id).version(version).createdDate(createdDate)
				.valueDate(valueDate).current(current).minimum(minimum).maximum(maximum).optimum(optimum).pending()
				.computed().checked().additionalDetails().additionalDetails(new HashSet<>())
				.additionalDetail(additionalDetail).build();
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
    }

    @Test
    public void copyBuildingTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        // when
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .build();
        final StockPosition positionCopy = position.toBuilder().build();
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
    }

    @Test
    public void nullMandatoryElementBuildingTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        try {
            // when
            StockPosition.builder().location(null).goods(goods).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(null).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

    @Test
    public void nullExtraElementBuildingTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).version(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).minimum(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).maximum(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).optimum(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).current(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).type(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).createdDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).valueDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).additionalDetail(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            StockPosition.builder().location(location).goods(goods).createdBy(testID).additionalDetails(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

    @Test
    public void mayMovementBeAppliedTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .build();
        final Movement movement1 = Movement.builder().of(goods).quantity(0).from(location).createdBy(testID).build();
        final Movement movement2 = Movement.builder().of(goods).quantity(1).from(location).createdBy(testID).build();
        // when
        final boolean b1 = position.mayMovementBeApplied(movement1);
        final boolean b2 = position.mayMovementBeApplied(movement2);
        // then
        assertTrue("It should be possible to apply the movement to the position", b1);
        assertTrue("It should be possible to apply the movement to the position", b2);
    }

    @Test
    public void canMovementBeAppliedImmediatelyTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(1).build();
        final Movement movement1 = Movement.builder().of(goods).quantity(1).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();
        final Movement movement2 = movement1.toBuilder().quantity(2).build();
        final Movement movement3 = movement1.toBuilder().valueDate(now.minusMinutes(1)).build();
        // when
        final boolean b1 = position.canMovementBeAppliedImmediately(movement1);
        final boolean b2 = position.canMovementBeAppliedImmediately(movement2);
        final boolean b3 = position.canMovementBeAppliedImmediately(movement3);
        // then
        assertTrue("It should be possible to apply the movement to the position", b1);
        assertFalse("It should NOT be possible to apply the movement to the position", b2);
        assertFalse("It should NOT be possible to apply the movement to the position", b3);
    }

    @Test
    public void computeNewPositionSuccessfullyFromOneMovementTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(1).build();
        final Movement movement = Movement.builder().of(goods).quantity(1).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();
        // when
        final StockPosition newPosition = position.computeNewPosition(movement);
        // then
        assertEquals(new Integer(0), newPosition.getCurrent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNewPositionWithFailureFromOneMovementTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(1).build();
        final Movement movement = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();
        // when
        position.computeNewPosition(movement);
        // then
    }

    @Test
    public void computeNewPositionFromSeveralMovementsSuccessfullyTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();// expect 13
        final Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();// expect 16
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();// expect 6

        movements.add(m0);
        movements.add(m1);
        movements.add(m2);
        movements.add(m3);
        movements.add(m4);
        // when
        final StockPosition newPosition = position.computeNewPosition(movements);
        // then
        assertEquals(new Integer(6), newPosition.getCurrent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNewPositionFromSeveralMovementsWithFailure01Test() {
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(1)).quantity(10).build();// expect failure
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();
        final Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();

        movements.add(m0);
        movements.add(m1);
        movements.add(m2);
        movements.add(m3);
        movements.add(m4);
        // when
        position.computeNewPosition(movements);
        // then
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNewPositionFromSeveralMovementsWithFailure02Test() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Location otherLocation = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// failure
        final Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();

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
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(1).build();
        final Movement movement = Movement.builder().of(goods).quantity(1).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();
        // when
        final StockPosition newPosition = position.computeNewPositionSilently(movement);
        // then
        assertEquals(new Integer(0), newPosition.getCurrent());
    }

    @Test
    public void computeNewPositionSilentlyWithFailureFromOneMovementTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(1).build();
        final Movement movement = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();
        // when
        final StockPosition newPosition = position.computeNewPositionSilently(movement);
        // then
        assertEquals(position.getCurrent(), newPosition.getCurrent());
    }

    @Test
    public void computeNewPositionSilentlyFromSeveralMovementsSuccessfullyTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(location).quantity(8).build();// expect 13
        final Movement m3 = m2.toBuilder().valueDate(now.plusMinutes(4)).quantity(3).build();// expect 16
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(10).build();// expect 6

        movements.add(m0);
        movements.add(m1);
        movements.add(m2);
        movements.add(m3);
        movements.add(m4);
        // when
        final StockPosition newPosition = position.computeNewPositionSilently(movements);
        // then
        assertEquals(new Integer(6), newPosition.getCurrent());
    }

    @Test
    public void computeNewPositionSilentlyFromSeveralMovementsWithFailureIgnoredTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Location otherLocation = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(3).build();// expect 5
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// ignored
        final Movement m3 = m0.toBuilder().valueDate(now.plusMinutes(4)).to(location).quantity(3).build();// expect 8
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(8).build();// expect 0

        movements.add(m0);
        movements.add(m1);
        movements.add(m2);
        movements.add(m3);
        movements.add(m4);
        // when
        final StockPosition newPosition = position.computeNewPositionSilently(movements);
        // then
        assertEquals(new Integer(0), newPosition.getCurrent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void computeNewPositionSilentlyFromSeveralMovementsWithFailureTest() {
        // given
        final Location location = Location.builder().name("").createdBy(testID).build();
        final Location otherLocation = Location.builder().name("").createdBy(testID).build();
        final Goods goods = Goods.builder().name("").createdBy(testID).build();
        final OffsetDateTime now = OffsetDateTime.now();
        final Integer start = 10;
        final StockPosition position = StockPosition.builder().location(location).goods(goods).createdBy(testID)
                .valueDate(now).current(start).build();
        final List<Movement> movements = new ArrayList<>();
        final Movement m0 = Movement.builder().of(goods).quantity(2).from(location).createdBy(testID)
                .valueDate(now.plusMinutes(1)).build();// expect 8
        final Movement m1 = m0.toBuilder().valueDate(now.plusMinutes(2)).quantity(9).build();// failure
        final Movement m2 = m0.toBuilder().valueDate(now.plusMinutes(3)).to(otherLocation).quantity(8).build();// ignored
        final Movement m3 = m0.toBuilder().valueDate(now.plusMinutes(4)).to(location).quantity(3).build();
        final Movement m4 = m0.toBuilder().valueDate(now.plusMinutes(5)).quantity(8).build();

        movements.add(m0);
        movements.add(m1);
        movements.add(m2);
        movements.add(m3);
        movements.add(m4);
        // when
        final StockPosition newPosition = position.computeNewPositionSilently(movements);
        // then
        assertEquals(new Integer(0), newPosition.getCurrent());
    }

}
