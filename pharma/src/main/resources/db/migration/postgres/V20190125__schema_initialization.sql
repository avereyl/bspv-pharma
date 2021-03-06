
DROP TABLE IF EXISTS locations;
CREATE TABLE locations (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	last_modified_by UUID,
	last_modified_date TIMESTAMP,
	code VARCHAR(75) NOT NULL,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	obsolete_date TIMESTAMP,
	PRIMARY KEY (id),
	CONSTRAINT uk_locations__1 UNIQUE(code)
);

DROP TABLE IF EXISTS goods;
CREATE TABLE goods (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	last_modified_by UUID,
	last_modified_date TIMESTAMP,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	deprecated_date TIMESTAMP,
	obsolete_date TIMESTAMP,
	default_location UUID,
	minimum_order_quantity INT NOT NULL DEFAULT 1,
	maximum_order_quantity INT NOT NULL DEFAULT 1,
	optimum_order_quantity INT NOT NULL DEFAULT 1,
	PRIMARY KEY (id),
	CONSTRAINT fk_goods__locations__1 FOREIGN KEY(default_location) REFERENCES locations(id)
);


DROP TABLE IF EXISTS inventories;
CREATE TABLE inventories (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	last_modified_by UUID,
	last_modified_date TIMESTAMP,
	closed_date TIMESTAMP,
	closed_by UUID,
	comment VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	last_modified_by UUID,
	last_modified_date TIMESTAMP,
	internal_comment VARCHAR(255) NOT NULL,
	external_comment VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS order_goods;
CREATE TABLE order_goods (
	order_id UUID NOT NULL,
	goods_id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	quantity BIGINT NOT NULL DEFAULT 0,
	PRIMARY KEY (order_id, goods_id),
	CONSTRAINT fk_order_goods__orders FOREIGN KEY(order_id) REFERENCES orders(id),
	CONSTRAINT fk_order_goods__goods FOREIGN KEY(goods_id) REFERENCES goods(id)
);

DROP TABLE IF EXISTS order_extras;
CREATE TABLE order_extras (
	order_id UUID NOT NULL,
	extra VARCHAR(255) NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	quantity BIGINT NOT NULL DEFAULT 0,
	PRIMARY KEY (order_id, extra),
	CONSTRAINT fk_order_goods__orders FOREIGN KEY(order_id) REFERENCES orders(id)
);

DROP TABLE IF EXISTS order_events;
CREATE TABLE orders_events (
	id UUID NOT NULL,
	order_id UUID NOT NULL,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	event_type VARCHAR(75) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_order_events__orders FOREIGN KEY(order_id) REFERENCES orders(id)
);

DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	key_ VARCHAR(75) NOT NULL,
	value_ VARCHAR(255) NOT NULL,
	goods_id UUID NOT NULL,
	PRIMARY KEY (goods_id, key_),
	CONSTRAINT fk_tags__goods__1 FOREIGN KEY(goods_id) REFERENCES goods(id)
);

DROP TABLE IF EXISTS movements;
CREATE TABLE movements (
	id UUID NOT NULL,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	goods_id UUID NOT NULL,
	location_id UUID NOT NULL,
	quantity INT NOT NULL DEFAULT 0,
	reason VARCHAR(50) NOT NULL,
	value_date TIMESTAMP NOT NULL,
	responsible_user UUID,
	linked_movement_id UUID,
	linked_order_id UUID,
	PRIMARY KEY (id),
	CONSTRAINT fk_movements__goods__1 FOREIGN KEY(goods_id) REFERENCES goods(id),
	CONSTRAINT fk_movements__locations__1 FOREIGN KEY(location_id) REFERENCES locations(id),
	CONSTRAINT fk_movements__movements__1 FOREIGN KEY(linked_movement_id) REFERENCES movements(id),
	CONSTRAINT fk_movements__orders__1 FOREIGN KEY(linked_order_id) REFERENCES orders(id)
);

DROP TABLE IF EXISTS positions;
CREATE TABLE positions (
	id UUID NOT NULL,
	version BIGINT NOT NULL DEFAULT 1,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	goods_id UUID NOT NULL,
	location_id UUID NOT NULL,
	minimum INT NOT NULL DEFAULT 0,
	maximum INT NOT NULL DEFAULT 0,
	optimum INT NOT NULL DEFAULT 0,
	current INT NOT NULL DEFAULT 0,
	type_ VARCHAR(75) NOT NULL,
	value_date TIMESTAMP NOT NULL,
	linked_inventory_id UUID,
	PRIMARY KEY (id),
	CONSTRAINT fk_positions__goods__1 FOREIGN KEY(goods_id) REFERENCES goods(id),
	CONSTRAINT fk_positions__locations__1 FOREIGN KEY(location_id) REFERENCES locations(id),
	CONSTRAINT fk_positions__inventories__1 FOREIGN KEY(linked_inventory_id) REFERENCES inventories(id)
);


DROP TABLE IF EXISTS position_details;
CREATE TABLE position_details (
	id UUID NOT NULL,
	created_by UUID NOT NULL,
	created_date TIMESTAMP NOT NULL,
	position_id UUID NOT NULL,
	validity_start_date TIMESTAMP NOT NULL,
	validity_end_date TIMESTAMP NOT NULL,
	value_date TIMESTAMP NOT NULL,
	type_ VARCHAR(75) NOT NULL,
	value_ VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_position_details__positions__1 FOREIGN KEY(position_id) REFERENCES positions(id)
);

