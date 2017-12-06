# com.polygonnode.FileServer
This Project is a key-Value database with the intention of quickly streaming small (16kb or less) files to what ever server requested them. The aim is to use this modlue is to use it in a nanoservices architecture. 

**Working Routes**
- /get/ (give it a key and it will return the file)
- /exists/ (returns 1 if key exists, 0 if it does not)
- /size (returns how many files are in the database

**Routes To Impliment**
- /insert/ (post route that contains file in either string or bytes)
- /delete/ (route that delete file associated with key)

**Features to Impliments**
- better file saving and loading (org.apatche.io)
- returning of file in bytes for direct streaminging into container
