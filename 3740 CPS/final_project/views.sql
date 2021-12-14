use final_project;

CREATE VIEW ALL_CHARACTER_NAME AS
SELECT archon_name FROM archon
UNION
SELECT character_name FROM `character`;


CREATE VIEW ALL_CRYO_CHARACTER_NAME AS
SELECT archon_name FROM archon WHERE archon_element = 'Cryo'
UNION
SELECT character_name FROM `character` WHERE character_element = 'Cryo';