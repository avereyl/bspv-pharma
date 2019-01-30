package org.bspv.pharma.model;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.bspv.pharma.model.AdditionalDetails.DetailsType;
import org.junit.Test;

public class AdditionalDetailsTest {


	@Test
	public void minimalBuildingTest() {
		//given
		//when
		AdditionalDetails details = AdditionalDetails.builder().type(DetailsType.MISC).build();
		//then
		assertEquals("Key of the tag should be the given key.", DetailsType.MISC, details.getType());
		assertEquals("Empty value should be found", "", details.getValue());
		assertNotNull("Validity start date should be set", details.getValidityStartDate());
		assertNotNull("Validity end date should be set", details.getValidityEndDate());
		assertNotNull("Value date should be set", details.getValueDate());
		assertEquals(details.getValidityEndDate(), details.getValueDate());
	}
	
	@Test
	public void maximalBuildingTest() {
		//given
		UUID id = UUID.randomUUID();
		DetailsType type = DetailsType.COMMENT;
		String value = "This is a comment!";
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime valueDate = now;
		LocalDateTime validityStartDate = now;
		LocalDateTime validityEndDate = now.plusDays(7);
		//when
		// @formatter:off
		AdditionalDetails details = AdditionalDetails.builder()
				.type(type)
				.id(id)
				.value(value)
				.valueDate(valueDate)
				.validFrom(validityStartDate)
				.validUntil(validityEndDate)
				.build();
		// @formatter:on
		//then
		assertEquals(id, details.getId());
		assertEquals(type, details.getType());
		assertEquals(value, details.getValue());
		assertEquals(valueDate, details.getValueDate());
		assertEquals(validityStartDate, details.getValidityStartDate());
		assertEquals(validityEndDate, details.getValidityEndDate());
	}
	
	@Test
	public void copyBuildingTest() {
		UUID id = UUID.randomUUID();
		DetailsType type = DetailsType.COMMENT;
		String value = "This is a comment!";
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime valueDate = now;
		LocalDateTime validityStartDate = now;
		LocalDateTime validityEndDate = now.plusDays(7);
		//when
		// @formatter:off
		AdditionalDetails details = AdditionalDetails.builder()
				.type(type)
				.id(id)
				.value(value)
				.valueDate(valueDate)
				.validFrom(validityStartDate)
				.validUntil(validityEndDate)
				.build();
		// @formatter:on
		AdditionalDetails detailsCopy = details.toBuilder().build();
		//then
		assertEquals(details, detailsCopy);//convention
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
			AdditionalDetails.builder().type(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).id(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).value(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).valueDate(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).validFrom(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
		try {
			// when
			AdditionalDetails.builder().type(DetailsType.EXPIRY_DATE).validUntil(null).build();
			fail("Should have failed with an IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			// then
		}
	}
}
