DROP DATABASE IF EXISTS biblioteka;

CREATE database biblioteka;

USE biblioteka;

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
		ORDER BY b.date DESC
		LIMIT 5;
END;
//


//
CREATE PROCEDURE updatebook
	(
    signature	VARCHAR(30),
    islent		VARCHAR(4)
    )
BEGIN
	UPDATE books b
    SET b.islent = islent
    WHERE b.signature = signature;
END;
//

//
CREATE PROCEDURE deletebook
	(
    signature VARCHAR(30)
    )
BEGIN
	DELETE FROM books
    WHERE b.signature  = signature;
END;
//

//
CREATE PROCEDURE getbooks ()
BEGIN
	SELECT * FROM books b;
END;
//

//
CREATE PROCEDURE getuser
	(
    login		VARCHAR(30),
    password	VARCHAR(30)
    )
BEGIN
	IF EXISTS 
	(SELECT * FROM users u
		WHERE u.login = login)
	THEN
		SELECT *.us, bs.signature, bs.title, bs.author, bs.date
        FROM users us
		INNER JOIN books bs on us.PESEL = bs.PESEL
	
	ELSEIF 
        SELECT 'User not found' as Error
	 END IF;
END;

delimiter ;


CALL addbook('H001', 'Illiada', 'Homer');
CALL addbook('S001', 'Antygona', 'Sofokles');
CALL addbook('M001', 'Skąpiec', 'Molier');
CALL addbook('P001', 'Lalka', 'Bolesław Prus');
CALL addbook('S002', 'Potop', 'Henryk Sienkiewicz');
CALL addbook('C001', 'Dżuma', 'Albert Camus');
CALL addbook('M002', 'Tango', 'Sławomir Mrożek');
CALL addbook('M003', 'Dziady cz.IV', 'Adam Mickiewicz');

CALL addadmin(1, 'admin', 'admin');

CALL adduser(98050489784, 'Jan', 'Kowalski', 'janko', 'janko123');
CALL adduser(98121212121, 'Zuzanna', 'Nowak', 'zuziaaa', 'zuzanowa');
CALL adduser(98053198785, 'Antoni', 'Kowalski', 'antoni98', 'anton321');
CALL adduser(98060698745, 'Filip', 'Wiśniewski', 'filipw', 'wisniafilip');
CALL adduser(98020406789, 'Julia', 'Woźniak', 'julawu', '98julia');

CALL updatebook('M001', 'YES');