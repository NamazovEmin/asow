CREATE TABLE IF NOT EXISTS stations
(
    id          serial      NOT NULL,
    name        varchar(60) NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT stations_pkey PRIMARY KEY (id),
    CONSTRAINT stations_name_key UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS railways
(
    id          serial NOT NULL,
    num         bigint NOT NULL,
    stations_id bigint NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT railways_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS wagon_passports
(
    id                serial NOT NULL,
    serial_number     bigint NOT NULL,
    wagon_type_id     bigint NOT NULL,
    container_weight  bigint NOT NULL,
    carrying_capacity bigint NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT wagon_passports_pkey PRIMARY KEY (id),
    CONSTRAINT wagon_passports_serial_number_key UNIQUE (serial_number)
);

CREATE TABLE IF NOT EXISTS wagon_type
(
    id   bigint       NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT wagon_type_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cargo_in_wagon
(
    id          serial NOT NULL,
    wagons_id   bigint NOT NULL,
    cargos_id   bigint NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT cargo_in_wagon_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cargos
(
    id          serial NOT NULL,
    code        bigint NOT NULL,
    name        varchar(60) NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT cargos_pkey PRIMARY KEY (id),
    CONSTRAINT cargos_code_key UNIQUE (code),
    CONSTRAINT cargos_name_key UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS wagons
(
    id                 serial NOT NULL,
    wagon_passports_id bigint NOT NULL,
    position_number    bigint,
    cargo_weight       bigint,
    railways_id        bigint,
    create_date        timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date        timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT wagons_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS wagon_in_order
(
    id          serial NOT NULL,
    wagons_id   bigint NOT NULL,
    orders_id   bigint NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT wagon_in_order_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id          serial NOT NULL,
    code        bigint NOT NULL,
    create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT orders_code_key UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS operations
(
    id                serial NOT NULL,
    operation_type_id bigint NOT NULL,
    from_railway_id   bigint NOT NULL,
    to_railway_id     bigint NOT NULL,
    wagon             varchar(255) NOT NULL,
    create_date       timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_date       timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT operations_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS operation_type
(
    id   bigint       NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT operation_type_pkey PRIMARY KEY (id)
);

ALTER TABLE operations
ADD CONSTRAINT "FK_OPERATIONS_ON_OPERATIONS_TYPE" FOREIGN KEY (operation_type_id)
    REFERENCES operation_type (id);

ALTER TABLE wagon_in_order
ADD CONSTRAINT "FK_WAGON_IN_ORDER_ON_WAGONS" FOREIGN KEY (wagons_id)
    REFERENCES wagons (id);

ALTER TABLE wagon_in_order
ADD CONSTRAINT "FK_WAGON_IN_ORDER_ON_ORDERS" FOREIGN KEY (orders_id)
    REFERENCES orders (id);

ALTER TABLE wagons
ADD CONSTRAINT "FK_WAGONS_ON_RAILWAYS" FOREIGN KEY (railways_id)
    REFERENCES railways (id);

ALTER TABLE wagons
ADD CONSTRAINT "FK_WAGONS_ON_WAGON_PASSPORTS" FOREIGN KEY (wagon_passports_id)
    REFERENCES wagon_passports (id);

ALTER TABLE cargo_in_wagon
ADD CONSTRAINT "FK_CARGO_IN_WAGON_ON_CARGO" FOREIGN KEY (cargos_id)
    REFERENCES cargos (id);

ALTER TABLE cargo_in_wagon
ADD CONSTRAINT "FK_CARGO_IN_WAGON_ON_WAGONS" FOREIGN KEY (wagons_id)
    REFERENCES wagons (id);

ALTER TABLE railways
ADD CONSTRAINT "FK_RAILWAYS_ON_STATIONS" FOREIGN KEY (stations_id)
    REFERENCES stations (id);

ALTER TABLE wagon_passports
ADD CONSTRAINT "FK_WAGON_PASSPORTS_ON_STATIONS" FOREIGN KEY (wagon_type_id)
    REFERENCES wagon_type (id);