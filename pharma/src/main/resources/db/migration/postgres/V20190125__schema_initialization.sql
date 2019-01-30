DROP TABLE IF EXISTS goods;
CREATE TABLE goods (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	name VARCHAR(75) NOT NULL,
	description VARCHAR(250) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	last_updated_date TIMESTAMP,
	deprecated_date TIMESTAMP,
	obsolete_date TIMESTAMP,
	default_location UUID,
	minimum_order_quantity INT NOT NULL DEFAULT 1,
	maximum_order_quantity INT NOT NULL DEFAULT 1,
	optimum_order_quantity INT NOT NULL DEFAULT 1,
	PRIMARY KEY (id),
	CONSTRAINT fk_goods__locations__1 FOREIGN KEY(default_location) REFERENCES locations(id)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	code VARCHAR(75) NOT NULL,
	name VARCHAR(75) NOT NULL,
	description VARCHAR(250) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	obsolete_date TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uk_locations__1 UNIQUE(code)
);

DROP TABLE IF EXISTS movements;
CREATE TABLE movements (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	goods UUID NOT NULL,
	location UUID NOT NULL,
	quantity INT NOT NULL DEFAULT 0,
	reason VARCHAR(50) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	value_date TIMESTAMP NOT NULL,
	responsible_user UUID,
	linked_movement UUID,
	linked_order UUID,
	PRIMARY KEY (id),
	CONSTRAINT fk_movements__goods__1 FOREIGN KEY(goods) REFERENCES goods(id),
	CONSTRAINT fk_movements__locations__1 FOREIGN KEY(location) REFERENCES locations(id),
	--CONSTRAINT fk_movements__users__1 FOREIGN KEY(responsible_user) REFERENCES users(id),
	CONSTRAINT fk_movements__movements__1 FOREIGN KEY(linked_movement) REFERENCES movements(id),
	CONSTRAINT fk_movements__orders__1 FOREIGN KEY(linked_order) REFERENCES orders(id)
);

DROP TABLE IF EXISTS positions;
CREATE TABLE positions (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	goods UUID NOT NULL,
	location UUID NOT NULL,
	minimum INT NOT NULL DEFAULT 0,
	maximum INT NOT NULL DEFAULT 0,
	optimum INT NOT NULL DEFAULT 0,
	current INT NOT NULL DEFAULT 0,
	type_ VARCHAR(50) NOT NULL,
	created_date TIMESTAMP NOT NULL,
	value_date TIMESTAMP NOT NULL,
	responsible_user UUID,
	linked_inventory UUID,
	PRIMARY KEY (id),
	CONSTRAINT fk_positions__goods__1 FOREIGN KEY(goods) REFERENCES goods(id),
	CONSTRAINT fk_positions__locations__1 FOREIGN KEY(location) REFERENCES locations(id),
	--CONSTRAINT fk_positions__users__1 FOREIGN KEY(responsible_user) REFERENCES users(id),
	CONSTRAINT fk_positions__inventories__1 FOREIGN KEY(linked_inventory) REFERENCES inventories(id)
);

DROP TABLE IF EXISTS inventories;
CREATE TABLE inventories (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_date TIMESTAMP NOT NULL,
	closed_date TIMESTAMP,
	responsible_user UUID,
	comment VARCHAR(250) NOT NULL,
	
	PRIMARY KEY (id)
	--, CONSTRAINT fk_inventories__users__1 FOREIGN KEY(responsible_user) REFERENCES users(id)
);
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_date TIMESTAMP NOT NULL,
	sent_date TIMESTAMP,
	received_date TIMESTAMP,
	validated_date TIMESTAMP,
	user_responsible_for_creation UUID,
	user_responsible_for_validation UUID,
	responsible_user UUID,
	internal_comment VARCHAR(250) NOT NULL,
	external_comment VARCHAR(250) NOT NULL,
	PRIMARY KEY (id)
	--, CONSTRAINT fk_orders__users__1 FOREIGN KEY(user_responsible_for_creation) REFERENCES users(id)
	--, CONSTRAINT fk_orders__users__2 FOREIGN KEY(user_responsible_for_validation) REFERENCES users(id)
);
DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
	key_ VARCHAR(50) NOT NULL,
	value_ VARCHAR(75) NOT NULL,
	goods UUID NOT NULL,
	PRIMARY KEY (goods, key_),
	CONSTRAINT fk_tags__goods__1 FOREIGN KEY(goods) REFERENCES goods(id),
);

DROP TABLE IF EXISTS details;
CREATE TABLE details (
	id UUID NOT NULL,
	validity_start_date TIMESTAMP NOT NULL,
	validity_end_date TIMESTAMP NOT NULL,
	value_date TIMESTAMP NOT NULL,
	type_ VARCHAR(50) NOT NULL,
	value_ VARCHAR(75) NOT NULL,
	position UUID NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_details__positions__1 FOREIGN KEY(position) REFERENCES positions(id)
);
