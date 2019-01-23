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
	
	@Test
	public void copyBuildingTest() {
		//given
		String key = "KEY";
		String value = "value";
		LocalDateTime valueDate = LocalDateTime.now();
		//when
		AdditionalDetails details = AdditionalDetails.builder().key(key).value(value).valueDate(valueDate).build();
		AdditionalDetails detailsCopy = details.toBuilder().build();
		//then
		assertEquals("Both keys should be equal.", detailsCopy.getKey(), details.getKey());
		assertEquals("Both values should be equal.", detailsCopy.getValue().get(), details.getValue().get());
		assertEquals("Both value dates should be equal.", detailsCopy.getValueDate(), details.getValueDate());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void missingKeyBuildingTest() {
		//given
		String key = null;
		//when
		AdditionalDetails.builder().key(key).build();
		//then -> should fire an exception
	}
}
