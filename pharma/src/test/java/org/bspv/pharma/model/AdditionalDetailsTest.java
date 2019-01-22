package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

public class AdditionalDetailsTest {


	@Test
	public void minimalBuildingTest() {
		//given
		String key = "KEY";
		//when
		AdditionalDetails details = AdditionalDetails.builder().key(key).build();
		//then
		assertEquals("Key of the tag should be the given key.", key, details.getKey());
		assertFalse("No value should be found", details.getValue().isPresent());
		assertNotNull("Value date should be set", details.getValueDate());
	}
	
	@Test
	public void maximalBuildingTest() {
		//given
		String key = "KEY";
		String value = "value";
		LocalDateTime valueDate = LocalDateTime.now();
		//when
		AdditionalDetails details = AdditionalDetails.builder().key(key).value(value).valueDate(valueDate).build();
		//then
		assertEquals("Key should be the given key.", key, details.getKey());
		assertEquals("Value should be equal to the given value", value, details.getValue().get());
		assertEquals("Value date should be equal to the given value date", valueDate, details.getValueDate());
	}
}
