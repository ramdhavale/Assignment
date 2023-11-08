-- Create the "categories" table
CREATE TABLE dms_schemas.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


-- Create the "items" table with a foreign key reference to "categories"
CREATE TABLE dms_schemas.items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
	description  VARCHAR(255),
    category_id INT REFERENCES categories(id)
);


-- Maven commnd to test the code
mvn archetype:generate -DgroupId=com.dms -DartifactId=ItemCategoryManager -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false