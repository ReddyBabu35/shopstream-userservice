CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $func$
BEGIN
	NEW.updated_at = NOW();
	RETURN NEW;
END;
$func$
LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();