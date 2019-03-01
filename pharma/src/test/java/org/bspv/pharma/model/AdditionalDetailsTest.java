package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.bspv.pharma.model.AdditionalDetails.DetailsType;
import org.junit.Test;

public class AdditionalDetailsTest {

    private final static UUID testID = UUID.randomUUID();

    @Test
    public void minimalBuildingTest() {
        // given
        // when
        final AdditionalDetails details = AdditionalDetails.builder().type(DetailsType.MISC).createdBy(testID).build();
        // then
        assertEquals("Key of the tag should be the given key.", DetailsType.MISC, details.getType());
        assertEquals("Empty value should be found", "", details.getValue());
        assertNotNull("Validity start date should be set", details.getValidityStartDate());
        assertNotNull("Validity end date should be set", details.getValidityEndDate());
        assertNotNull("Value date should be set", details.getValueDate());
        assertEquals(details.getValidityEndDate(), details.getValueDate());
    }

    @Test
    public void maximalBuildingTest() {
        // given
        final UUID id = UUID.randomUUID();
        final DetailsType type = DetailsType.COMMENT;
        final String value = "This is a comment!";
        final OffsetDateTime now = OffsetDateTime.now();
        final OffsetDateTime valueDate = now;
        final OffsetDateTime validityStartDate = now;
        final OffsetDateTime validityEndDate = now.plusDays(7);
        // when
        // @formatter:off
		final AdditionalDetails details = AdditionalDetails.builder()
				.type(type)
				.createdBy(testID)
				.id(id)
				.value(value)
				.valueDate(valueDate)
				.validFrom(validityStartDate)
				.validUntil(validityEndDate)
				.build();
		// @formatter:on
        // then
        assertEquals(id, details.getId());
        assertEquals(type, details.getType());
        assertEquals(value, details.getValue());
        assertEquals(valueDate, details.getValueDate());
        assertEquals(validityStartDate, details.getValidityStartDate());
        assertEquals(validityEndDate, details.getValidityEndDate());
    }

    @Test
    public void copyBuildingTest() {
        final UUID id = UUID.randomUUID();
        final DetailsType type = DetailsType.COMMENT;
        final String value = "This is a comment!";
        final OffsetDateTime now = OffsetDateTime.now();
        final OffsetDateTime valueDate = now;
        final OffsetDateTime validityStartDate = now;
        final OffsetDateTime validityEndDate = now.plusDays(7);
        // when
        // @formatter:off
		final AdditionalDetails details = AdditionalDetails.builder()
				.type(type)
				.createdBy(testID)
				.id(id)
				.value(value)
				.valueDate(valueDate)
				.validFrom(validityStartDate)
				.validUntil(validityEndDate)
				.build();
		// @formatter:on
        final AdditionalDetails detailsCopy = details.toBuilder().build();
        // then
        assertEquals(details, detailsCopy);// convention
        assertEquals(details.getId(), detailsCopy.getId());
        assertEquals(details.getType(), detailsCopy.getType());
        assertEquals(details.getValue(), detailsCopy.getValue());
        assertEquals(details.getValueDate(), detailsCopy.getValueDate());
        assertEquals(details.getValidityStartDate(), detailsCopy.getValidityStartDate());
        assertEquals(details.getValidityEndDate(), detailsCopy.getValidityEndDate());
    }

    @Test
    public void missingArgumlentsBuildingTest() {
        try {
            // when
            AdditionalDetails.builder().type(null).createdBy(testID).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).createdBy(testID).id(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).createdBy(testID).value(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).createdBy(testID).valueDate(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).createdBy(testID).validFrom(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
        try {
            // when
            AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).createdBy(testID).validUntil(null).build();
            fail("Should have failed with an IllegalArgumentException.");
        } catch (final IllegalArgumentException e) {
            // then
        }
    }
}
