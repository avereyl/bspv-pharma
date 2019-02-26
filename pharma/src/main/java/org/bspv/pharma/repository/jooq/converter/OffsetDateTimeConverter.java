/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bspv.pharma.repository.jooq.converter;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.jooq.Converter;

public class OffsetDateTimeConverter implements Converter<Timestamp, OffsetDateTime> {

	/**
	 *
	 */
	private static final long serialVersionUID = 8668180700669912302L;

	@Override
	public OffsetDateTime from(final Timestamp databaseObject) {
		return databaseObject == null ? null : OffsetDateTime.ofInstant(databaseObject.toInstant(), ZoneOffset.UTC);
	}

	@Override
	public Timestamp to(final OffsetDateTime userObject) {
		return userObject == null ? null : Timestamp.from(userObject.atZoneSameInstant(ZoneOffset.UTC).toInstant());
	}

	@Override
	public Class<Timestamp> fromType() {
		return Timestamp.class;
	}

	@Override
	public Class<OffsetDateTime> toType() {
		return OffsetDateTime.class;
	}

}
