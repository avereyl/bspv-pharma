package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.Test;

public class LocationTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        final String name = "Location name";
        // when
        final Location location = Location.builder().name(name).createdBy(testID).build();
        // then
        assertEquals("Name of the location should be the given name.", name, location.getName());
        assertNotNull(location.getId());
        assertNotNull(location.getVersion());
        assertNotNull(location.getCode());
        assertNotNull(location.getCreatedDate());
        assertNotNull(location.getDescription());
        assertFalse(location.isObsolete());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final String name = "Location name";
        final UUID id = UUID.randomUUID();
        final Long version = 1L;
        final String code = "CODE";
        final String description = "My description for this location";
        final OffsetDateTime createdDate = OffsetDateTime.now();
        final OffsetDateTime obsoleteDate = OffsetDateTime.now();
        // when
        final Location location = Location.builder().name(name).createdBy(testID).id(id).version(version).code(code)
                .description(description).createdDate(createdDate).obsoleteDate(obsoleteDate).build();
        // then
        assertEquals("Name of the location should be the given name.", name, location.getName());
        assertEquals(id, location.getId());
        assertEquals(version, location.getVersion());
        assertEquals(code, location.getCode());
        assertEquals(description, location.getDescription());
        assertEquals(createdDate, location.getCreatedDate());
        assertEquals(obsoleteDate, location.getObsoleteDate().get());
    }

    @Test
    public void copyBuildingTest() {
        // given
        final String name = "Location name";
        // when
        final Location location = Location.builder().name(name).createdBy(testID).build();
        final Location locationCopy = location.toBuilder().build();
        // then
        assertEquals(locationCopy, location);
        assertEquals(locationCopy.getName(), location.getName());
        assertEquals(locationCopy.getId(), location.getId());
        assertEquals(locationCopy.getVersion(), location.getVersion());
        assertEquals(locationCopy.getCode(), location.getCode());
        assertEquals(locationCopy.getCreatedDate(), location.getCreatedDate());
        assertEquals(locationCopy.getDescription(), location.getDescription());
        assertFalse(location.isObsolete());
    }

    @Test
    public void nullMandatoryElementBuildingTest() {
        // given
        final String name = null;
        // when
        try {
            Location.builder().name(name).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

    @Test
    public void nullExtraElementBuildingTest() {
        // given
        final String name = "Location name";
        // when
        try {
            Location.builder().name(name).createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Location.builder().name(name).createdBy(testID).version(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Location.builder().name(name).createdBy(testID).code(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Location.builder().name(name).createdBy(testID).description(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        // when
        try {
            Location.builder().name(name).createdBy(testID).createdDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }

}
