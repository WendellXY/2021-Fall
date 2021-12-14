use final_project;

SELECT character_element, COUNT(character_element) AS TOTALNUMBER FROM `character` GROUP BY character_element HAVING TOTALNUMBER > 1;

SELECT character_country, COUNT(character_country) AS TOTALNUMBER FROM `character` GROUP BY character_country HAVING TOTALNUMBER > 3;