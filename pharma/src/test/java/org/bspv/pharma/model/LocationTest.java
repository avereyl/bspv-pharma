package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class LocationTest {

	@Test
	public void minimalBuildingTest() {
		//given
		String name = "Location name";
		//when
		Location location = Location.builder().name(name).build();
		//then
		assertEquals("Name of the location should be the given name.", name, location.getName());
		//....
		assertFalse("No obsolete date should be found", location.getObsoleteDate().isPresent());
	}

}
