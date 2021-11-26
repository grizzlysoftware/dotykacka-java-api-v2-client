# Dotykacka Java API Client
Compatible with Dotykacka api version 2.x

## Notes
### Usage
Client is Cloud/tenant oriented. It means that for each Cloud you manage you need a dedicated client instance.

Example:
```
String url = "https://api.dotykacka.cz/v2";             // API url
String token = "YOUR_REFRESH_TOKEN";                    // refresh token generated through dotykacka cloud webapp
Long cloudId = 28435734252123L;                         // your cloud/tenant id
Duration requestTimeout = Duration.ofSeconds(5);        // duration until request times out

ApiConfiguration configuration = new ApiConfiguration(
        url,
        token,
        cloudId,
        requestTimeout
);

DotykackaApiClient client = new DotykackaApiClient(configuration);
```

V'oila! That's basically it. You can access any of the given client's service facades to interact with API.

```
client.authenticationServiceFacade;                         // authentication service
client.cloudServiceFacade;                                  // cloud entity service facade                                        
client.branchServiceFacade;                                 // branch entity service facade
client.categoryServiceFacade;                               // category entity service facade       
client.customerServiceFacade;                               // customer entity service facade        
client.discountGroupServiceFacade;                          // discount group entity service facade            
client.deliveryNoteServiceFacade;                           // delivery note entity service facade            
client.employeeServiceFacade;                               // employee entity service facade        
client.moneyLogServiceFacade;                               // money log entity service facade        
client.orderServiceFacade;                                  // order entity service facade    
client.orderItemServiceFacade;                              // order item entity service facade        
client.productServiceFacade;                                // product entity service facade        
client.productCustomizationServiceFacade;                   // product customization entity service facade                    
client.productIngredientServiceFacade;                      // product ingredient entity service facade                
client.reservationServiceFacade;                            // reservation entity service facade            
client.supplierServiceFacade;                               // supplier entity service facade        
client.tableServiceFacade;                                  // table entity service facade    
client.tagServiceFacade;                                    // tag entity service facade    
client.warehouseServiceFacade;                              // warehouse entity service facade        
client.warehouseBranchServiceFacade;                        // warehouse branch entity service facade                
client.eetSubjectServiceFacade;                             // eetSubject entity service facade        
```
API is basically CRUD, so the client's service facades are pretty descriptive by itself.

For example, have a look at `CustomerServiceFacade:`
- createCustomer - creates single customers
- createCustomers - creates customers in batch mode
- updateCustomers - updates customers in batch mode
- updateCustomer - updates single customer
- patchCustomer - patches single customer
- deleteCustomer - deletes customer by id
- getCustomer - gets customer by id
- getCustomers - gets paginated collection of customers for given page, limit and filter criteria parameters
- getAllCustomers - gets unpaginated collection of ALL customers for given filter criteria parameters

### Authentication and authorization

Client does authentication and authorization for you. It authenticates in API using given `REFRESH_TOKEN` to obtain `ACCESS_TOKEN` 
required for authorization while sending requests. Obtained `ACCESS_TOKEN` is stored in `AccessTokenProvider` instance. 
It is attached to every outgoing request's headers until it is valid. If it is not, fresh `ACCESS_TOKEN` is obtained automatically. 

`AccessTokenProvider` is not thread-safe, and it was not intended to be as I did not identify any side effects of having not thread-safe component implementation.

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
request body(target object) is deserialized by a specific interceptor to retrieve forementioned `etag` property.


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
Variables description:
- DOTYKACKA_API_URL - api service url
- DOTYKACKA_API_CLOUD_ID - your cloud (tenant) id
- DOTYKACKA_API_TOKEN - refresh token obtained from dotykacka admin portal, see guide [here](https://docs.api.dotypos.com/authorization)
- INTEGRATION_TEST - flag that tells test engine to execute integration tests. So in order to run integration tests its value must be non-empty

If you want to execute tests with default ids just substitute `xxxxx` with desired values and invoke:

```
DOTYKACKA_API_URL=xxxxx \
DOTYKACKA_API_CLOUD_ID=xxxxx \
DOTYKACKA_API_TOKEN=xxxxx \
INTEGRATION_TEST=x && gradlew clean test
```

If you want to execute tests with your tenant specific ids 
you have to override them - simply by exporting additional variables. 
As in previous case - just substitute `xxxxx` with desired values and invoke as follows: 

```
DOTYKACKA_API_URL=xxxxx \
DOTYKACKA_API_CLOUD_ID=xxxxx \
DOTYKACKA_API_TOKEN=xxxxx \
BRANCH_ID=xxxxx \
CLOUD_ID=xxxxx \
CATEGORY_ID=xxxxx \
COMPANY_ID=xxxxx \
DISCOUNT_GROUP_ID=xxxxx \
EMPLOYEE_ID=xxxxx \
INGREDIENT_ID=xxxxx \
MONEY_LOG_ID=xxxxx \
ORDER_ID=xxxxx \
ORDER_ITEM_ID=xxxxx \
PRODUCT_ID=xxxxx \
TABLE_ID=xxxxx \
TAG_ID=xxxxx \
WAREHOUSE_ID=xxxxx \
INTEGRATION_TEST=x && gradlew clean test
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
