## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

### Changelog

* Introduced abstract Animal class to hold logic shared by EndangeredAnimal and NonEndangeredAnimal
* Animal data moved to the single table instead of 2
* DB schema updated
* IllegalArgumentException is thrown in case bad parameters are supplied to the constructors
* Added route to catch exception and provide diagnostics
* Added tests to check that exception is thrown in case of bad parameters (empty name of the animal, empty location or ranger name of the sighting)
* Added timestamp of the sighting event

### License

Copyright (c) 2017 **_MIT License_**
