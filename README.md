# Mountain DB
This Project is a key-Value database with the intention of quickly streaming json objects to what ever server requested them. The aim is to use this database where a fast and simple nosql solution is needed

**Working Routes**
- /get/key (give it a key and it will return the file)
- /batchget/key1,key2,ke3 (gets a list of values)
- /exists/key (returns 1 if key exists, 0 if it does not)
- /size (returns how many files are in the database
- /insert/key/value (stores value at key in the database)

**Routes To Impliment**
- /delete/key (route that delete file associated with key)
- /dumpKeys/ (gets a list of all the keys in the db)

**Features to Impliments**
- configuration file for automatic configuration running 
- method to backup or for one db to request to replicate another database
