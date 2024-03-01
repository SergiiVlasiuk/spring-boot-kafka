## Build
### Start kafka
```
docker-compose up -d
```
### Register subjects in the schema registry
```
mvn schema-registry:register
```
### Build the app
```
mvn clean install
```
