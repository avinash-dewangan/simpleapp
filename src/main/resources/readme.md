# Summary:
* ddl-auto=none: Hibernate doesn't touch the schema.
* ddl-auto=create: Hibernate creates the schema, but it's destroyed when the application shuts down.
* ddl-auto=create-drop: Hibernate creates and drops the schema on application startup and shutdown.
* ddl-auto=update: Hibernate updates the schema based on the entity mappings.
* ddl-auto=validate: Hibernate validates the schema against the entity mappings.


-- Adding the 'create' and 'update' columns with default values
ALTER TABLE district_webpage_details
ADD COLUMN create_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN update_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;