use final_project;

SELECT character_name FROM `character` WHERE character_country IN (SELECT character_country FROM unpublished_character);

SELECT character_id, character_name FROM `character` WHERE character_age IN (SELECT character_age FROM unpublished_character);

SELECT character_id, character_name FROM `character` WHERE character_element IN (SELECT archon_element FROM archon);