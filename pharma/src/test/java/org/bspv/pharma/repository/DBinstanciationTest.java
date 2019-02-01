package org.bspv.pharma.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;

import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;

public class DBinstanciationTest {

	@Rule
	public PreparedDbRule db = EmbeddedPostgresRules
			.preparedDatabase(FlywayPreparer.forClasspathLocation("db/migration/postgres"));

	@Test
	public void dummyTest() {
		assertNotNull(db);
	}
}
