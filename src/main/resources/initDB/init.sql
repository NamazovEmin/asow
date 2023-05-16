create table stations (
  id BIGINT not null
);


CREATE TABLE access_key_config
(
    id                    BIGINT        NOT NULL,
    server_tag            VARCHAR(255)  NOT NULL,
    thread_limit          INTEGER       NOT NULL,
    max_linked_ips        INTEGER       NOT NULL,
    ttl                   numeric(21)   NOT NULL,
    auth_type             VARCHAR(255)  NOT NULL,
    rotate_period         numeric(21)   NOT NULL,
    allowed_destinations  VARCHAR(1024) NOT NULL,
    disabled_destinations VARCHAR(1024) NOT NULL,
    CONSTRAINT pk_access_key_config PRIMARY KEY (id)
);