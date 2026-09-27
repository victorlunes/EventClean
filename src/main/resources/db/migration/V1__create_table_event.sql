CREATE TABLE events
(
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    location      VARCHAR(255) NOT NULL,
    organizer     VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    identificator VARCHAR(255) NOT NULL,
    start_event   DATETIME(6)  NOT NULL,
    end_event     DATETIME(6)  NOT NULL,
    capacity      INT          NOT NULL,
    -- utf8mb4_0900_bin is used for words enums, verify uppercase
    type_event    VARCHAR(50)  COLLATE utf8mb4_0900_bin NOT NULL,

    -- if you create new enum, update this check :)
    CONSTRAINT chk_events_type_event
        CHECK (type_event IN ('SHOW', 'PALESTRA', 'WORKSHOP', 'ESPORTIVO'))
);