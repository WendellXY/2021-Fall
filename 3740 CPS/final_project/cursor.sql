SET @character_names

DECLARE name_cursor CURSOR FOR SELECT character_name FROM final_project.character;
OPEN name_cursor;
FETCH name_cursor INTO @character_names;
CLOSE name_cursor;

SELECT @character_names;