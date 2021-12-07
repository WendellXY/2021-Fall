-- SELECT column_name(s)
-- FROM table_name
-- WHERE column_name IN (value1, value2, ...);

SELECT * FROM final_project.character
WHERE character_country IN ('Liyue', 'Inadzuma');

-- SELECT column_name(s)
-- FROM table_name
-- WHERE column_name BETWEEN value1 AND value2;

SELECT * FROM final_project.character
WHERE character_id BETWEEN 2 AND 4;

-- SELECT column1, column2, ...
-- FROM table_name
-- WHERE columnN LIKE pattern;

SELECT * FROM final_project.element
WHERE element_name LIKE 'A%';