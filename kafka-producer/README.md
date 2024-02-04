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
### Start app
consider to start with `spring.profiles.active=experimental-avro`
## Subject Strategies
### Example of Input Data
- topic name — report_details_topic
- schema name — ReportDetails
- schema namespace — com.vl.model.avro
### Strategies
- `TopicNameStrategy` is default one - `${topic-name}-value` ~ `report_details_topic-value`

TopicNameStrategy can be used when all the data in the particular topic conforms with the same schema.

- `RecordNameStrategy` - `${schema-namespace}.${schema-name}` ~ `com.vl.model.avro.ReportDetails`

The recordname strategy can be used for when a particular record type can occur across multiple topics.
- `TopicRecordNameStrategy` - `${topic-name}-${schema-namespace}.${schema-name}` ~ `report_details_topic-com.vl.model.avro.ReportDetails`

This strategy groups multiple record per topic.
