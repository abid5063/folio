CREATE TABLE users (
    userid SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    name VARCHAR(256) NOT NULL,
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

CREATE TABLE contracts (
    contractId SERIAL PRIMARY KEY,
    agreementNo VARCHAR(512) UNIQUE NOT NULL,
    agreementName VARCHAR(512) NOT NULL,
    signingDate DATE NOT NULL,
    startDate DATE NOT NULL,
    endingDate DATE NOT NULL,
    companyRegNo VARCHAR(200) NOT NULL,
    registrationAddress VARCHAR(512),
    administrativeAddress VARCHAR(512),
    operationAddress VARCHAR(512),
    bsclSignatoryId INTEGER REFERENCES users(userid),
    contractorSignatory VARCHAR(1024) NOT NULL,
    contact1 VARCHAR(1024),
    contact2 VARCHAR(1024),
    contact3 VARCHAR(1024),
    totalPrice INTEGER NOT NULL,
    emergencyContact1 VARCHAR(1024),
    emergencyContact2 VARCHAR(1024),
    emergencyContact3 VARCHAR(1024),
    emergencyContact4 VARCHAR(1024),
    contractDownloadLink VARCHAR(1024),
    customization VARCHAR(4096)
);

CREATE TABLE contacts (
    contactId SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    institution VARCHAR(100) NOT NULL,
    designation VARCHAR(20),
    phone VARCHAR(20) NOT NULL,
    phone2 VARCHAR(20),
    email VARCHAR(50),
    whatsapp VARCHAR(20),
    isCurrent BOOLEAN NOT NULL DEFAULT TRUE,
    relevantDept VARCHAR(200),
    extraInfo VARCHAR(1000)
);