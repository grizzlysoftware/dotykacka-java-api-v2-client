#Dotykacka Java API Client
Compatible with Dotykacka api version 2.x

## Recommended pre-requisities
* Java 11
* Gradle 6.0.2

## Artifact

### Maven
```
<dependency>
    <groupId>pl.grizzlysoftware</groupId>
    <artifactId>dotykacka-java-api-v2-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```
compile group: 'pl.grizzlysoftware', name: 'dotykacka-java-api-v2-client', version: '1.0.0'
```

## Running unit tests
```
gradlew clean test 
```

## Contribution
You are very welcome to fork and submit pull-requests

## TODO

Things to implement:
- Base sales report
- POS Actions
  - Create order
  - Update order
  - Add order items
  - Split order
  - Issue order
  - Pay issued order
  - Create and issue order
  - Create, issue and pay order
  - Split and issue order
  - Split, issue and pay order
  - Issue and pay
  - Change order status (Work in progress(?))
  - Get list of open orders
  - Change item's course
  - Prepare next course
  - Set/unset item(s) as takeaway
