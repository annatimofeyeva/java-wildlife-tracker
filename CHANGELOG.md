* Introduced abstract Animal class to hold logic shared by EndangeredAnimal and NonEndangeredAnimal
* Animal data moved to the single table instead of 2
* DB schema updated
* IllegalArgumentException is thrown in case bad parameters are supplied to the constructors
* Added route to catch exception and provide diagnostics
* Added tests to check that exception is thrown in case of bad parameters
