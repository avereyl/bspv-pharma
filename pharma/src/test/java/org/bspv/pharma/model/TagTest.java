package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

public class TagTest {

	private final static UUID testID = UUID.randomUUID();

	@Test
	public void minimalBuildingTest() {
		// given
		String key = "KEY";
		// when
		Tag tag = Tag.builder().key(key).createdBy(testID).build();
		// then
		assertEquals("Key of the tag should be the given key.", key, tag.getKey());
		assertEquals("Creator of the tag should be the given one", testID, tag.getCreatedBy());
		assertNotNull("CreatedDate should not be null", tag.getCreatedDate());
		assertFalse("No value should be found", tag.getValue().isPresent());
	}

	@Test
	public void maximalBuildingTest() {
		// given
		LocalDateTime createdDate = LocalDateTime.now();
		String key = "KEY";
		String value = "value";
		// when
		Tag tag = Tag.builder().key(key).createdBy(testID).createdDate(createdDate).value(value).build();
		// then
		assertEquals(key, tag.getKey());
		assertEquals(testID, tag.getCreatedBy());
		assertEquals(createdDate, tag.getCreatedDate());
		assertEquals(value, tag.getValue().get());
	}

	@Test
	public void copyBuildingTest() {
		// given
		String key = "KEY";
		String value = "value";
		// when
		Tag tag = Tag.builder().key(key).createdBy(testID).value(value).build();
		Tag copyTag = tag.toBuilder().build();
		// then
		assertEquals(tag, copyTag);
		assertEquals(tag.getKey(), copyTag.getKey());
		assertEquals(tag.getCreatedBy(), copyTag.getCreatedBy());
		assertEquals(tag.getCreatedDate(), copyTag.getCreatedDate());
		assertEquals(tag.getValue().get(), copyTag.getValue().get());
	}

	@Test
	public void predefinedBuildingTest() {
		// given
		// when
		Tag batteryTag = Tag.BATTERY_TAG_BUILDER_FACTORY.get().createdBy(testID).build();
		Tag expiryTag = Tag.EXPIRY_TAG_BUILDER_FACTORY.get().createdBy(testID).build();
		// then
		assertEquals("Key of the tag should be the given key.", "battery", batteryTag.getKey());
		assertEquals("Value of the tag should be equal to the given value", "expires", expiryTag.getKey());
		assertFalse("No value should be found", batteryTag.getValue().isPresent());
		assertFalse("No value should be found", expiryTag.getValue().isPresent());
	}

	@Test(expected = IllegalArgumentException.class)
	public void missingKeyBuildingTest() {
		// given
		String key = null;
		// when
		Tag.builder().key(key).createdBy(testID).build();
		// then -> should fire an exception
	}
	@Test(expected = IllegalArgumentException.class)
	public void missingCreatorBuildingTest() {
		// given
		String key = "Key";
		// when
		Tag.builder().key(key).createdBy(null).build();
		// then -> should fire an exception
	}

}
