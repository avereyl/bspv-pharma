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

public class GoodsTest {

	@Test
	public void minimalBuildingTest() {
		// given
		String name = "name of the goods";
		// when
		Goods goods = Goods.builder().name(name).build();
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
		assertFalse(goods.getLastUpdatedDate().isPresent());
		assertTrue(goods.getTags().isEmpty());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		String name = "name of the goods";
		UUID id = UUID.randomUUID();
		Long version = 1L;
		LocalDateTime createdDate = LocalDateTime.now();
		LocalDateTime lastUpdatedDate = LocalDateTime.now();
		LocalDateTime deprecatedDate = lastUpdatedDate.plusYears(1L);
		LocalDateTime obsoleteDate = deprecatedDate.plusYears(1L);
		Location defaultLocation = Location.builder().name("location").build();
		String description = "description for this goods";
		Integer minimumOrderQuantity = 1;
		Integer maximumOrderQuantity = 9;
		Integer optimumOrderQuantity = 5;
		Tag tag = Tag.builder().key("TAG").build();
		// when
		// @formatter:off
		Goods goods = Goods.builder().name(name).id(id).version(version).createdDate(createdDate).lastUpdatedDate(lastUpdatedDate)
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
		assertEquals(lastUpdatedDate, goods.getLastUpdatedDate().get());
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
		String name = "name of the goods";
		UUID id = UUID.randomUUID();
		Long version = 1L;
		LocalDateTime createdDate = LocalDateTime.now();
		LocalDateTime lastUpdatedDate = LocalDateTime.now();
		LocalDateTime deprecatedDate = lastUpdatedDate.plusYears(1L);
		LocalDateTime obsoleteDate = deprecatedDate.plusYears(1L);
		Location defaultLocation = Location.builder().name("location").build();
		String description = "description for this goods";
		Integer minimumOrderQuantity = 1;
		Integer maximumOrderQuantity = 9;
		Integer optimumOrderQuantity = 5;
		Tag tag = Tag.builder().key("TAG").build();
		// when
		// @formatter:off
		Goods goods = Goods.builder().name(name).id(id).version(version).createdDate(createdDate).lastUpdatedDate(lastUpdatedDate)
				.defaultLocation(defaultLocation).description(description).deprecatedDate(deprecatedDate)
				.obsoleteDate(obsoleteDate).minimumOrderQuantity(minimumOrderQuantity)
				.maximumOrderQuantity(maximumOrderQuantity).optimumOrderQuantity(optimumOrderQuantity).tags()
				.tags(new HashSet<>()).tag(tag).build();
		// @formatter:on
		Goods goodsCopy = goods.toBuilder().build();
		// then
		assertEquals(goods, goodsCopy);//convention
		assertEquals("Name of the location should be the given name.", goods.getName(), goods.getName());
		assertEquals(goodsCopy.getId(), goods.getId());
		assertEquals(goodsCopy.getVersion(), goods.getVersion());
		assertEquals(goodsCopy.getCreatedDate(), goods.getCreatedDate());
		assertEquals(goodsCopy.getLastUpdatedDate(), goods.getLastUpdatedDate());
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
		String name = null;
		try {
			// when
			Goods.builder().name(name).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}
	@Test
	public void nullExtraElementBuildingTest() {
		// given
		String name = "Goods name";
		try {
			// when
			Goods.builder().name(name).id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).version(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).description(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).createdDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).minimumOrderQuantity(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).maximumOrderQuantity(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).optimumOrderQuantity(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).tag(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			Goods.builder().name(name).tags(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		
	}

	
}
