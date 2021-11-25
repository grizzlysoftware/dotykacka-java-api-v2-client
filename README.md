# Dotykacka Java API Client
Compatible with Dotykacka api version 2.x

## Notes
### ETAG support
`ETAG` is an object checksum calculated by the server side while getting and updating objects and it's returned in `response headers`.
It is helpful for dealing with concurrent updating API objects. 

How does it work?

- Getting objects from API:

Everytime `CloudEntity` object is fetched from API its `etag` property is filled with value from `ETAG` response headers.
`etag` property is applied to `CloudEntity` object every time it's fetched from API

- Putting updated objects to API:

Everytime `CloudEntity` object is about to being PUT/PATCH to API it's `etag` property value is applied as request `If-Match` header.
Unfortunately this is very inefficient because right before request is being sent,
request body(target object) is deserialized by specific interceptor to retrieve forementioned `etag` property.


Warning! 

While fetching collections, `ETAG` is calculated for the state of collection. It means that:
- collection items order
- collection items count 
- collection items state
are considered while calculating `ETAG`. 

It means also that trying to update only ONE of the items from such collection 
and using its `ETAG`(calculated for collection as whole) will end up in failure returning `412` status code.

In order to update single item from collection you have to re-fetch single that item by its id and apply it's `etag` to the updated item's `etag`.
Another way to update that single item is to pass collection as a whole to batch update.

See more at [https://docs.api.dotypos.com/api-reference/general/etags](https://docs.api.dotypos.com/api-reference/general/etags)

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

## License
```
Copyright 2021 Grizzly Software, https://grizzlysoftware.pl

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

```
