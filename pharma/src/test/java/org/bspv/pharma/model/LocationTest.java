package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

public class LocationTest {

	@Test
	public void minimalBuildingTest() {
		// given
		String name = "Location name";
		// when
		Location location = Location.builder().name(name).build();
		// then
		assertEquals("Name of the location should be the given name.", name, location.getName());
		assertNotNull(location.getId());
		assertNotNull(location.getCode());
		assertNotNull(location.getCreatedDate());
		assertNotNull(location.getDescription());
		assertFalse(location.isObsolete());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		String name = "Location name";
		UUID id = UUID.randomUUID();
		String code = "CODE";
		String description = "My description for this location";
		LocalDateTime createdDate = LocalDateTime.now();
		LocalDateTime obsoleteDate = LocalDateTime.now();
		// when
		Location location = Location.builder().name(name).id(id).code(code).description(description)
				.createdDate(createdDate).obsoleteDate(obsoleteDate).build();
		// then
		assertEquals("Name of the location should be the given name.", name, location.getName());
		assertEquals(id, location.getId());
		assertEquals(code, location.getCode());
		assertEquals(description, location.getDescription());
		assertEquals(createdDate, location.getCreatedDate());
		assertEquals(obsoleteDate, location.getObsoleteDate().get());
	}

	@Test
	public void copyBuildingTest() {
		// given
		String name = "Location name";
		// when
		Location location = Location.builder().name(name).build();
		Location locationCopy = location.toBuilder().build();
		// then
		assertEquals(locationCopy.getName(), location.getName());
		assertEquals(locationCopy.getId(), location.getId());
		assertEquals(locationCopy.getCode(), location.getCode());
		assertEquals(locationCopy.getCreatedDate(), location.getCreatedDate());
		assertEquals(locationCopy.getDescription(), location.getDescription());
		assertFalse(location.isObsolete());
	}

	@Test
	public void nullMandatoryElementBuildingTest() {
		// given
		String name = null;
		// when
		try {
			Location.builder().name(name).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}

	@Test
	public void nullExtraElementBuildingTest() {
		// given
		String name = "Location name";
		try {
			// when
			Location.builder().name(name).id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Location.builder().name(name).code(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Location.builder().name(name).description(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		// when
		try {
			Location.builder().name(name).createdDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}

}
