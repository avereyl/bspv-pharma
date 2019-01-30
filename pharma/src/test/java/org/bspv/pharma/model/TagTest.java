package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TagTest {

	@Test
	public void minimalBuildingTest() {
		//given
		String key = "KEY";
		//when
		Tag tag = Tag.builder().key(key).build();
		//then
		assertEquals("Key of the tag should be the given key.", key, tag.getKey());
		assertFalse("No value should be found", tag.getValue().isPresent());
	}
	
	@Test
	public void maximalBuildingTest() {
		//given
		String key = "KEY";
		String value = "value";
		//when
		Tag tag = Tag.builder().key(key).value(value).build();
		//then
		assertEquals("Key of the tag should be the given key.", key, tag.getKey());
		assertEquals("Value of the tag should be equal to the given value", value, tag.getValue().get());
	}
	
	@Test
	public void copyBuildingTest() {
		//given
		String key = "KEY";
		String value = "value";
		//when
		Tag tag = Tag.builder().key(key).value(value).build();
		Tag copyTag = tag.toBuilder().build();
		//then
		assertEquals(tag, copyTag);
		assertEquals("Key of the tag should be the given key.", tag.getKey(), copyTag.getKey());
		assertEquals("Value of the tag should be equal to the given value", tag.getValue().get(), copyTag.getValue().get());
	}
	
	@Test
	public void predefinedBuildingTest() {
		//given
		//when
		Tag batteryTag = Tag.BATTERY_TAG.get().build();
		Tag expiryTag = Tag.EXPIRY_TAG.get().build();
		//then
		assertEquals("Key of the tag should be the given key.", "battery", batteryTag.getKey());
		assertEquals("Value of the tag should be equal to the given value", "expires", expiryTag.getKey());
		assertFalse("No value should be found", batteryTag.getValue().isPresent());
		assertFalse("No value should be found", expiryTag.getValue().isPresent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void missingKeyBuildingTest() {
		//given
		String key = null;
		//when
		Tag.builder().key(key).build();
		//then -> should fire an exception
	}

}
