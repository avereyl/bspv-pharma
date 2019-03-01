package org.bspv.pharma.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.Test;

public class TagTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        final String key = "KEY";
        // when
        final Tag tag = Tag.builder().key(key).createdBy(testID).build();
        // then
        assertEquals("Key of the tag should be the given key.", key, tag.getKey());
        assertEquals("Creator of the tag should be the given one", testID, tag.getCreatedBy());
        assertNotNull("CreatedDate should not be null", tag.getCreatedDate());
        assertFalse("No value should be found", tag.getValue().isPresent());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final String key = "KEY";
        final String value = "value";
        // when
        final Tag tag = Tag.builder().key(key).createdBy(testID).createdDate(createdDate).value(value).build();
        // then
        assertEquals(key, tag.getKey());
        assertEquals(testID, tag.getCreatedBy());
        assertEquals(createdDate, tag.getCreatedDate());
        assertEquals(value, tag.getValue().get());
    }

    @Test
    public void copyBuildingTest() {
        // given
        final String key = "KEY";
        final String value = "value";
        // when
        final Tag tag = Tag.builder().key(key).createdBy(testID).value(value).build();
        final Tag copyTag = tag.toBuilder().build();
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
        final Tag batteryTag = Tag.BATTERY_TAG_BUILDER_FACTORY.get().createdBy(testID).build();
        final Tag expiryTag = Tag.EXPIRY_TAG_BUILDER_FACTORY.get().createdBy(testID).build();
        // then
        assertEquals("Key of the tag should be the given key.", "battery", batteryTag.getKey());
        assertEquals("Value of the tag should be equal to the given value", "expires", expiryTag.getKey());
        assertFalse("No value should be found", batteryTag.getValue().isPresent());
        assertFalse("No value should be found", expiryTag.getValue().isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingKeyBuildingTest() {
        // given
        final String key = null;
        // when
        Tag.builder().key(key).createdBy(testID).build();
        // then -> should fire an exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingCreatorBuildingTest() {
        // given
        final String key = "Key";
        // when
        Tag.builder().key(key).createdBy(null).build();
        // then -> should fire an exception
    }

}
