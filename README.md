# Mountain DB
This Project is a key-Value database with the intention of quickly streaming json objects to what ever server requested them. The aim is to use this database where a fast nosql solution is needed 

**Working Routes**
- /get/ (give it a key and it will return the file)
- /batchget/ (gets a list of values)
- /exists/ (returns 1 if key exists, 0 if it does not)
- /size (returns how many files are in the database
- /insert/ (post route that contains file in either string or bytes)

**Routes To Impliment**
- /delete/ (route that delete file associated with key)
- /dumpKeys/ (gets a list of all the keys in the db)

**Features to Impliments**
- configuration file for automatic configuration running 
- method to backup or for one db to request to replicate another database
