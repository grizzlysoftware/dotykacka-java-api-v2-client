#Dotykacka Java API Client
Compatible with Dotykacka api version 2.x

## Recommended pre-requisities
* Java 11
* Gradle 7.2 (not necessarily, it's used inside wrapper and it was tested by me)

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

## Running integration tests
### WARNING !
```
THERES NO SANDBOX ENVIRONMENT FOR INTEGRATION TESTS PROVIDED BY dotykacka.cz
THE ONLY WAY FOR RUNNING INTEGRATION TEST IS TO PROVIDE YOUR OWN CLOUD_ID AND REQUIRED IDS
```

### YOU ARE RUNNING INTEGRATION TESTS ON YOUR OWN RISK !!

### Execution

DOTYKACKA_API_URL - api service url
DOTYKACKA_API_CLOUD_ID - your cloud (tenant) id
DOTYKACKA_API_TOKEN - refresh token obtained from dotykacka admin portal, see guide [here](https://docs.api.dotypos.com/authorization)
INTEGRATION_TEST - flag that tells test engine to execute integration tests. So in order to run integration tests its value must be non-empty

If you want to execute tests with default ids just substitute `xxxxx` with desired values and invoke:

```
export DOTYKACKA_API_URL=xxxxx \
export DOTYKACKA_API_CLOUD_ID=xxxxx \
export DOTYKACKA_API_TOKEN=xxxxx \
export INTEGRATION_TEST=x && gradlew clean test
```

If you want to execute tests with your tenant specific ids 
you have to override them - simply by exporting additional variables. 
As in previous case - just substitute `xxxxx` with desired values and invoke as follows: 

```
export DOTYKACKA_API_URL=xxxxx \
export DOTYKACKA_API_CLOUD_ID=xxxxx \
export DOTYKACKA_API_TOKEN=xxxxx \
export BRANCH_ID=xxxxx \
export CLOUD_ID=xxxxx \
export CATEGORY_ID=xxxxx \
export COMPANY_ID=xxxxx \
export DISCOUNT_GROUP_ID=xxxxx \
export EMPLOYEE_ID=xxxxx \
export INGREDIENT_ID=xxxxx \
export MONEY_LOG_ID=xxxxx \
export ORDER_ID=xxxxx \
export ORDER_ITEM_ID=xxxxx \
export PRODUCT_ID=xxxxx \
export TABLE_ID=xxxxx \
export TAG_ID=xxxxx \
export WAREHOUSE_ID=xxxxx \
export INTEGRATION_TEST=x && gradlew clean test
```

## Contribution
You are very welcome to fork and submit pull-requests

## TODO
Things to fix:
- filtering by timestamp in OrderItemService

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
