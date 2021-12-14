SELECT UPPER(element_name) FROM final_project.element;

SELECT CONCAT(UPPER(archon_name), '_', UPPER(archon_country), '_', UPPER(archon_element)) FROM final_project.archon;

SELECT CONCAT(archon_name, ' , the god of ', LOWER(archon_element), ' ') FROM final_project.archon;

SELECT CONCAT(archon_id, ': ', UPPER(archon_name)) FROM final_project.archon;

SELECT CONCAT(character_name, ': ', character_element) FROM final_project.character;