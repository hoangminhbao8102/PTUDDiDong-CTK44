PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE android_metadata (locale TEXT);
INSERT INTO android_metadata VALUES('vi_VN');
CREATE TABLE Account (email text PRIMARY KEY, password text);
INSERT INTO Account VALUES('m@example.com','1');
COMMIT;