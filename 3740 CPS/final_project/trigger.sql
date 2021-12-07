CREATE TRIGGER AUTO_UPDATE_COUNTRY
AFTER UPDATE ON final_project.country
FOR EACH ROW
	UPDATE final_project.character SET character_country = NEW.country_name
    WHERE character_country = OLD.country_name;