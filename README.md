# bulk-registration
Bulk Contact Registration Demo

### Run application

- mvn clean install

### End point
- curl -v http://localhost:5555/csv

It will read records from <b>records.csv</b> and validate each record. 
If a record is valid, a new file will be created with its details, else violations will be logged in console.


