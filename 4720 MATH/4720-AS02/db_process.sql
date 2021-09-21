SELECT * FROM gmet99;
-- .schema gmet99

.header on
.mode column
pragma table_info('gmet99');

SELECT Tmin, Tmax FROM gmet99;
SELECT Rain, Sun FROM gmet99;

-- SELECT MIN(Tmin) FROM gmet99;
-- SELECT MAX(Tmax) FROM gmet99;
SELECT MIN(Tmin), MAX(Tmin), MIN(Tmin), MAX(Tmax) FROM gmet99;

SELECT Rain, Sun FROM gmet99 GROUP BY Month;
SELECT Rain, Sun FROM gmet99 GROUP BY Year;
SELECT AVG(Rain), AVG(Sun), Year FROM gmet99 GROUP BY Year;

SELECT COUNT(Rain) FROM gmet99;
SELECT AVG(Rain) FROM gmet99;
SELECT SUM(Rain) FROM gmet99;

SELECT COUNT(Rain), Month FROM gmet99 GROUP BY Month;
SELECT AVG(Rain), Month FROM gmet99 GROUP BY Month;

SELECT SUM(Rain), Month FROM gmet99 GROUP BY Month ORDER BY Month ASC;
SELECT SUM(Rain), Month FROM gmet99 GROUP BY Month ORDER BY Month DESC;

-- SELECT SUM(Rain) FROM gmet99 WHERE Month = 10 OR Month = 8;
-- SELECT SUM(Rain) FROM gmet99 WHERE Month = 10 OR Month = 8;