-- SET OPERATOR QUERY

-- 1. Show all characters inclduing archons in Genshin Impact

SELECT character_name FROM final_project.character
UNION
SELECT archon_name FROM final_project.archon;

-- 2. Show all characters having Cryo Vision and are from Inadzuma
-- MySQL does not support INTERSECT operator.

-- SELECT character_name FROM final_project.character WHERE character_element LIKE "Cryo"
-- INTERSECT
-- SELECT character_name FROM final_project.character WHERE character_country IS "Inadzuma";

-- 3. Show all characters having Cryo Vision and are not from Inadzuma
-- MySQL does not support MINUS operator

-- SELECT character_name FROM final_project.character WHERE character_element LIKE "Cryo"
-- MINUS
-- SELECT character_name FROM final_project.character WHERE character_country Like "Inadzuma";

-- 4. Show characters and Mondstadt and the archon of "Inadzuma"

SELECT character_name FROM final_project.character WHERE character_country = "Mondstadt"
UNION
SELECT archon_name FROM final_project.archon WHERE archon_country = "Inadzuma";

-- 5. Show all children character and Anemo archon
SELECT character_name FROM final_project.character WHERE character_age < 18
UNION
SELECT archon_name FROM final_project.archon WHERE archon_element = "Anemo"

-- JOIN QUERY

SELECT archon_name FROM final_project.archon
FULL OUTER JOIN final_project.character
ON final_project.archon.archon_element = final_project.character.character_element