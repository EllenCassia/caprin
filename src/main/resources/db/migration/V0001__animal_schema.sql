CREATE SCHEMA ANIMAL;

--ANIMAL
CREATE TABLE ANIMAL.TE_ANIMAL
(
    ID              SERIAL,
    DHCRIACAO       TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    ID_EXTERNO      BIGINT,
    REGISTRO        VARCHAR(255)                NOT NULL,
    NOME            VARCHAR(255),
    SITUACAO        VARCHAR(10),
    DNA             BOOLEAN,
    TOD             VARCHAR(5)                  NOT NULL,
    TOE             VARCHAR(5)                  NOT NULL,
    CRIADOR         VARCHAR(255)                NOT NULL,
    PROPRIETARIO    VARCHAR(255)                NOT NULL,
    AFIXO           VARCHAR(255)                NOT NULL,
    DATA_NASCIMENTO DATE                        NOT NULL,
    SEXO            VARCHAR(1)                  NOT NULL,
    CATEGORIA       VARCHAR(100),
    RACA            VARCHAR(100)                NOT NULL,
    PAI             JSON                        NOT NULL,
    MAE             JSON                        NOT NULL,

    CONSTRAINT PK_TE_ANIMAL PRIMARY KEY (ID)
);