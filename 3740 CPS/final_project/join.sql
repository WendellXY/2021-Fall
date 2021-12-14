use final_project;

SELECT `character`.character_id, unpublished_character.character_element, unpublished_character.character_name
FROM unpublished_character
LEFT JOIN `character`
ON `character`.character_name = unpublished_character.character_name;

SELECT `character`.character_id, unpublished_character.character_element, unpublished_character.character_name
FROM unpublished_character
RIGHT JOIN `character`
ON `character`.character_name = unpublished_character.character_name;

SELECT `character`.character_id, unpublished_character.character_element, unpublished_character.character_name
FROM unpublished_character
LEFT JOIN `character`
ON `character`.character_name = unpublished_character.character_name
WHERE unpublished_character.character_element = 'Cryo';