# ScientificPublications

Software for working with scientific publications.

Project for course "XML and WEB Services" at Faculty of Technical Sciences, University of Novi Sad.

Team members:

- Dragan Ćulibrk SW-22/2016
- Nikola Ignjatović SW-20/2016
- Luka Marić SW-8/2016

# Technologies used

- [Spring Framework](https://spring.io/): an application framework and inversion of control container for the Java platform
- [exist-db](http://exist-db.org/exist/apps/homepage/index.html): an open source software project for NoSQL databases built on XML technology
- [Apache Jena Fuseki](https://jena.apache.org/documentation/fuseki2/): SPARQL server used for metadata manipulation & extraction
- [Angular](https://angular.io/): TypeScript-based open-source web application framework

# Project setup

Clone the repository:

`git clone https://github.com/XML-TIM4/ScientificPublications.git`

- **BACKEND**: Open terminal in Backend root directory and run `mvnw spring-boot:run`
- **exist-db**: Install as service and run on port 8081
- **Apache Jena Fuseki**: Download and extract .zip, open terminal in root directory and run `fuseki-server.bat`, it should be available at port 3030
- **Angular**: Open terminal in Frontend root directory and run `npm install` and `npm run start`, it should be available at port 4200
