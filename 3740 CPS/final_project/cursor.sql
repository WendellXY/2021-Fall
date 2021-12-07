DELIMITER $$
CREATE PROCEDURE createCharacterList (
	INOUT characterList varchar(4000)
)
BEGIN
	DECLARE finished INTEGER DEFAULT 0;
	DECLARE mycharacter varchar(100) DEFAULT "";

	-- declare cursor for character
	DEClARE curChracter
		CURSOR FOR
			SELECT character_name FROM final_project.character;

	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET finished = 1;

	OPEN curChracter;

	getCharacter: LOOP
		FETCH curChracter INTO mycharacter;
		IF finished = 1 THEN
			LEAVE getCharacter;
		END IF;
		-- build character list
		SET characterList = CONCAT(mycharacter,";",characterList);
	END LOOP getCharacter;
	CLOSE curChracter;

END$$
DELIMITER ;

SET @characterList = "";
CALL createCharacterList(@characterList);
SELECT @characterList;