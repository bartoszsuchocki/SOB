


DROP DATABASE IF EXISTS biblioteka_testowa;

CREATE database biblioteka_testowa;

USE biblioteka_testowa;

/* TWORZENIE TABELI SERS */
CREATE TABLE users
	(
    PESEL		BIGINT			NOT NULL,
    name		VARCHAR(40)		NULL,
    surname		VARCHAR(40)		NULL,
    login		VARCHAR(30)		NOT NULL,
    password	VARCHAR(30)		NOT NULL,
    role		INT				NOT NULL,
    PRIMARY KEY (PESEL)
    );
    
/* TWORZENIE TABELI BOOKS */
CREATE TABLE books
	(
    signature	VARCHAR(30)		NOT NULL,
    title		VARCHAR(30)		NOT NULL,
    author		VARCHAR(50)		NOT NULL,
    date		DATE			NOT NULL,
    islent		VARCHAR(4)		NOT NULL	DEFAULT 'NO',
    PESEL 		BIGINT			NULL,
    PRIMARY KEY (signature),
    FOREIGN KEY (PESEL) REFERENCES users(PESEL)
    );


delimiter //

/* DODAWANIE UZYTKOWNIKOW */
CREATE PROCEDURE adduser 
	(
    PESEL		BIGINT,
    name		VARCHAR(40),
    surname		VARCHAR(40),
    login		VARCHAR(30),
    password	VARCHAR(30)
    )
BEGIN
    IF NOT EXISTS
		(SELECT * FROM users u
			WHERE u.PESEL = PESEL)
	THEN
		IF NOT EXISTS
			(SELECT * FROM users wu
				WHERE wu.login = login)
		THEN
			INSERT INTO users(PESEL, name, surname, login, password, role)
			VALUES(PESEL, name, surname, login, password, 1);
        END IF;
	END IF;
END;
//
  
  
//
/* DODAWANIE ADMINA */
CREATE PROCEDURE addadmin
	(
    PESEL		BIGINT,
    login		VARCHAR(30),
    password	VARCHAR(30)
    )
BEGIN
    IF NOT EXISTS
		(SELECT * FROM users u
			WHERE u.PESEL = PESEL)
	THEN
		IF NOT EXISTS
			(SELECT * FROM users wu
				WHERE wu.login = login)
		THEN
			INSERT INTO users(PESEL,login, password, role)
			VALUES(PESEL, login, password, 2);
        END IF;
	END IF;
END;
//
  
  
/* DODAWANIE KSIAZKI */
//
CREATE PROCEDURE addbook
	(
    signature	VARCHAR(30),
    title		VARCHAR(30),
    author		VARCHAR(50)
	)
BEGIN
    IF NOT EXISTS 
		(SELECT * FROM books b
			WHERE b.signature = signature)
	THEN
		INSERT INTO books(signature, title, author, date)
		VALUES(signature, title, author, CURDATE());
	END IF;
END;
//


//
CREATE PROCEDURE getnewbooks ()
BEGIN
	SELECT * FROM books b
		WHERE b.islent = 'NO'
		ORDER BY b.date DESC
		LIMIT 5;
END;
//


//
CREATE PROCEDURE lentbook
	(
    signature	VARCHAR(30),
    login 		VARCHAR(30)
    )
BEGIN
	IF EXISTS
		(SELECT * FROM books b
			WHERE b.signature = signature
            AND b.islent LIKE 'NO')
		THEN
		IF EXISTS
			(SELECT * FROM users u
				WHERE u.login = login)
			THEN
			UPDATE books b
			SET b.islent = 'YES',
				b.PESEL = (SELECT uw.PESEL FROM users uw 
							WHERE uw.login = login)
			WHERE b.signature = signature;
		END IF;
	END IF;
END;
//


//
CREATE PROCEDURE returnbook
	(
    signature 	VARCHAR(30)
    )
BEGIN
	IF EXISTS
		(SELECT * FROM books b
			WHERE b.signature = signature
			AND b.islent LIKE 'YES')
		THEN
        UPDATE books b 
        SET b.islent = 'NO',
			b.PESEL = NULL
		WHERE b.signature = signature;
	END IF;
END;
//


//
CREATE PROCEDURE deletebook
	(
    signature VARCHAR(30)
    )
BEGIN
	DELETE FROM books
    WHERE books.signature  = signature;
END;
//

//
CREATE PROCEDURE getallbooks ()
BEGIN
	SELECT * FROM books b;
END;
//


//
CREATE PROCEDURE getbooks
	(
	title		VARCHAR(30)
    )
BEGIN
	SELECT * FROM books b
		WHERE b.title LIKE CONCAT("%",title,"%");
END
//


//
CREATE PROCEDURE getuserbooks
	(
    login		VARCHAR(30)
    )
BEGIN
	SELECT * FROM books b
		WHERE b.PESEL = 
				(SELECT u.PESEL
					FROM users u 
					WHERE u.login = login);
END
//


//

create PROCEDURE getuser
	
(
    login		VARCHAR(30)
   )

BEGIN 
	SELECT * FROM users u
		WHERE u.login = login;
END;


//

//

delimiter ;



grant all on biblioteka_testowa.* to user1;

call adduser(98112589778,'Adam','Kowalski','kowal','haslo');
CALL adduser(98050489784, 'Jan', 'Kowalski', 'janko', 'janko123');
CALL addbook('H001', 'Illiada', 'Homer');
CALL addbook('S001', 'Antygona', 'Sofokles');
CALL addbook('S002', 'Potop', 'Henryk Sienkiewicz');
CALL addadmin(1, 'admin', 'admin');
CALL lentbook('H001', 'janko123');

