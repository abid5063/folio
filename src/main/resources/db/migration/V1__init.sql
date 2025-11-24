CREATE TABLE users (
    userid SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    designation VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(256) NOT NULL
);

CREATE TABLE meetings (
    meetingId SERIAL PRIMARY KEY,
    initiatorId INTEGER NOT NULL REFERENCES users(userid),
    collaboratorsId VARCHAR(256),
    startTime TIMESTAMP NOT NULL,
    endTime TIMESTAMP,
    agenda VARCHAR(1024)
);

CREATE TABLE pricingTables (
    tableID SERIAL PRIMARY KEY,
    data VARCHAR(4096)
);

CREATE TABLE contracts (
    contractId SERIAL PRIMARY KEY,
    agreementNo VARCHAR(512) UNIQUE NOT NULL,
    startDate DATE NOT NULL,
    endingDate DATE NOT NULL,
    bsclSignatoryId INTEGER REFERENCES users(userid),
    contractorSignatory VARCHAR(1024) NOT NULL,
    pricingId INTEGER REFERENCES pricingTables(tableID),
    contractDownloadLink VARCHAR(1024),
    customization VARCHAR(4096)
);
