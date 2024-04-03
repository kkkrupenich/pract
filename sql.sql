BEGIN;

CREATE TABLE IF NOT EXISTS public."Role"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "RoleName" character varying NOT NULL
);

CREATE TABLE IF NOT EXISTS public."Passport"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "SerialNumber" character varying NOT NULL UNIQUE,
    "IdentificationNumber" character varying NOT NULL UNIQUE,
    "Registration" character varying NOT NULL,
    "IssueDate" date NOT NULL,
    "ExpirationDate" date NOT NULL
);

CREATE TABLE IF NOT EXISTS public."Subscription"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "Status" boolean NOT NULL,
    "ExpirationDate" date NOT NULL
);

CREATE TABLE IF NOT EXISTS public."User"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "Email" character varying NOT NULL UNIQUE,
    "Password" character varying NOT NULL,
    "FIO" character varying NOT NULL,
    "PassportID" serial UNIQUE NOT NULL REFERENCES public."Passport"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "RoleID" serial NOT NULL REFERENCES public."Role"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "Balance" double precision NOT NULL,
    "SubscriptionID" serial REFERENCES public."Subscription"("ID") ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS public."Chance"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "LoseChance" double precision NOT NULL,
    "ReturnChance" double precision NOT NULL,
    "WinChance" double precision NOT NULL
);

CREATE TABLE IF NOT EXISTS public."Card"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "Number" integer NOT NULL UNIQUE,
    "ExpirationDate" date NOT NULL,
    "HoldersName" character varying NOT NULL,
    "CVV" integer NOT NULL
);


CREATE TABLE IF NOT EXISTS public."Game"
(
    "ID" serial NOT NULL PRIMARY KEY,
    "Name" character varying NOT NULL,
    "PremiumStatus" boolean NOT NULL,
    "ChanceID" serial NOT NULL REFERENCES public."Chance"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "MinimalBet" double precision NOT NULL,
    "MaximumBet" double precision NOT NULL
);

CREATE TABLE IF NOT EXISTS public."Card_User"
(
    "CardID" serial NOT NULL REFERENCES public."Card"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "UserID" serial NOT NULL REFERENCES public."User"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY ("CardID", "UserID")
);

CREATE TABLE IF NOT EXISTS public."Review"
(
	"ID" serial NOT NULL PRIMARY KEY,
    "UserID" serial NOT NULL REFERENCES public."User"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "GameID" serial NOT NULL REFERENCES public."Game"("ID") ON DELETE CASCADE ON UPDATE CASCADE,
    "Message" character varying NOT NULL,
    "Rating" character varying NOT NULL,
    "Date" date NOT NULL
);

END;