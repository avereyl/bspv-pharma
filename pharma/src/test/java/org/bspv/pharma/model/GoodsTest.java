package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.UUID;

import org.junit.Test;

public class GoodsTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        final String name = "name of the goods";
        // when
        final Goods goods = Goods.builder().name(name).createdBy(testID).build();
        // then
        assertEquals("Name of the location should be the given name.", name, goods.getName());
        assertNotNull(goods.getId());
        assertNotNull(goods.getVersion());
        assertNotNull(goods.getCreatedDate());
        assertNotNull(goods.getDescription());
        assertNotNull(goods.getMinimumOrderQuantity());
        assertNotNull(goods.getMaximumOrderQuantity());
        assertNotNull(goods.getOptimumOrderQuantity());
        assertFalse(goods.isDeprecated());
        assertFalse(goods.isObsolete());
        assertFalse(goods.getDefaultLocation().isPresent());
        assertFalse(goods.getLastModifiedDate().isPresent());
        assertTrue(goods.getTags().isEmpty());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final String name = "name of the goods";
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final OffsetDateTime LastModifiedDate = OffsetDateTime.now();
        final OffsetDateTime deprecatedDate = LastModifiedDate.plusYears(1L);
        final OffsetDateTime obsoleteDate = deprecatedDate.plusYears(1L);
        final Location defaultLocation = Location.builder().name("location").createdBy(testID).build();
        final String description = "description for this goods";
        final Integer minimumOrderQuantity = 1;
        final Integer maximumOrderQuantity = 9;
        final Integer optimumOrderQuantity = 5;
        final Tag tag = Tag.builder().key("TAG").createdBy(testID).build();
        // when
        // @formatter:off
		final Goods goods = Goods.builder().name(name).createdBy(testID).id(id).version(version).createdDate(createdDate).lastModifiedDate(LastModifiedDate)
				.defaultLocation(defaultLocation).description(description).deprecatedDate(deprecatedDate)
				.obsoleteDate(obsoleteDate).minimumOrderQuantity(minimumOrderQuantity)
				.maximumOrderQuantity(maximumOrderQuantity).optimumOrderQuantity(optimumOrderQuantity).tags()
				.tags(new HashSet<>()).tag(tag).build();
		// @formatter:on
        // then
        assertEquals("Name of the location should be the given name.", name, goods.getName());
        assertEquals(id, goods.getId());
        assertEquals(version, goods.getVersion());
        assertEquals(createdDate, goods.getCreatedDate());
        assertEquals(LastModifiedDate, goods.getLastModifiedDate().get());
        assertEquals(defaultLocation, goods.getDefaultLocation().get());
        assertEquals(description, goods.getDescription());
        assertEquals(deprecatedDate, goods.getDeprecatedDate().get());
        assertEquals(obsoleteDate, goods.getObsoleteDate().get());
        assertEquals(minimumOrderQuantity, goods.getMinimumOrderQuantity());
        assertEquals(maximumOrderQuantity, goods.getMaximumOrderQuantity());
        assertEquals(optimumOrderQuantity, goods.getOptimumOrderQuantity());
        assertEquals(1, goods.getTags().size());
        assertEquals(tag, goods.getTags().toArray(new Tag[0])[0]);
    }

    @Test
    public void copyBuildingTest() {
        // given
        final String name = "name of the goods";
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final OffsetDateTime LastModifiedDate = OffsetDateTime.now();
        final OffsetDateTime deprecatedDate = LastModifiedDate.plusYears(1L);
        final OffsetDateTime obsoleteDate = deprecatedDate.plusYears(1L);
        final Location defaultLocation = Location.builder().name("location").createdBy(testID).build();
        final String description = "description for this goods";
        final Integer minimumOrderQuantity = 1;
        final Integer maximumOrderQuantity = 9;
        final Integer optimumOrderQuantity = 5;
        final Tag tag = Tag.builder().key("TAG").createdBy(testID).build();
        // when
        // @formatter:off
		final Goods goods = Goods.builder().name(name).createdBy(testID).id(id).version(version).createdDate(createdDate).lastModifiedDate(LastModifiedDate)
				.defaultLocation(defaultLocation).description(description).deprecatedDate(deprecatedDate)
				.obsoleteDate(obsoleteDate).minimumOrderQuantity(minimumOrderQuantity)
				.maximumOrderQuantity(maximumOrderQuantity).optimumOrderQuantity(optimumOrderQuantity).tags()
				.tags(new HashSet<>()).tag(tag).build();
		// @formatter:on
        final Goods goodsCopy = goods.toBuilder().build();
        // then
        assertEquals(goods, goodsCopy);// convention
        assertEquals("Name of the location should be the given name.", goods.getName(), goods.getName());
        assertEquals(goodsCopy.getId(), goods.getId());
        assertEquals(goodsCopy.getVersion(), goods.getVersion());
        assertEquals(goodsCopy.getCreatedDate(), goods.getCreatedDate());
        assertEquals(goodsCopy.getLastModifiedDate(), goods.getLastModifiedDate());
        assertEquals(goodsCopy.getDefaultLocation(), goods.getDefaultLocation());
        assertEquals(goodsCopy.getDescription(), goods.getDescription());
        assertEquals(goodsCopy.getDeprecatedDate(), goods.getDeprecatedDate());
        assertEquals(goodsCopy.getObsoleteDate(), goods.getObsoleteDate());
        assertEquals(goodsCopy.getMinimumOrderQuantity(), goods.getMinimumOrderQuantity());
        assertEquals(goodsCopy.getMaximumOrderQuantity(), goods.getMaximumOrderQuantity());
        assertEquals(goodsCopy.getOptimumOrderQuantity(), goods.getOptimumOrderQuantity());
        assertEquals(goodsCopy.getTags(), goods.getTags());
    }

    @Test
    public void nullMandatoryElementBuildingTest() {
        // given
        final String name = null;
        try {
            // when
            Goods.builder().name(name).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

    @Test
    public void nullExtraElementBuildingTest() {
        // given
        final String name = "Goods name";
        try {
            // when
            Goods.builder().name(name).createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).version(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).description(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).createdDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).minimumOrderQuantity(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).maximumOrderQuantity(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).optimumOrderQuantity(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).tag(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            Goods.builder().name(name).createdBy(testID).tags(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }

    }

}
