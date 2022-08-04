# TransactionRestApi
Transaction REST API with CRUD operations in Spring Boot.
Don't forget to work on "jdbc:mysql://localhost:3306/transactions". You can change this configuration on application.yaml for your local MySQL server.

This service has 7 endpoints.
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/50373019/182846723-933217b6-02e9-41e8-a20e-803f6be1c878.png">
To add a transaction to DB, use the POST method and fill the below body:
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/50373019/182846913-db26ca8b-6d85-49ad-a303-df0955e99a29.png">
You can add any key-value map into the transactionDataEntityMap.
In my example let's add a few transaction data.

<img width="675" alt="image" src="https://user-images.githubusercontent.com/50373019/182847399-954c7136-a7d3-489d-b4be-ea5cf3525bf8.png">

Response Body:

<img width="1408" alt="image" src="https://user-images.githubusercontent.com/50373019/182847448-dd5b8c31-1a4f-42a6-a3b1-e3340b1a774b.png">

For getting one special transaction, give the id name to the below URL:

<img width="1408" alt="image" src="https://user-images.githubusercontent.com/50373019/182847637-2fb0cc4c-3d2b-4fe1-a6ef-482da2bbe133.png">
<img width="882" alt="image" src="https://user-images.githubusercontent.com/50373019/182847663-f4ec2268-4cb9-43f9-aac6-fbfa0ed3f574.png">
For getting whole transactions:

<img width="1426" alt="image" src="https://user-images.githubusercontent.com/50373019/182847877-3afd6471-2b97-4d21-a87f-e9692442d649.png">
<img width="691" alt="image" src="https://user-images.githubusercontent.com/50373019/182848028-864c50b4-7e97-4c33-8c0b-ef6e6d186710.png">
For actor-specific transaction search use the below endpoint:
<img width="1417" alt="image" src="https://user-images.githubusercontent.com/50373019/182848582-fbab2f54-fb89-46d2-869e-c873a4384f27.png">
It will return the list of transactions given the actor's payments.
For type-specific transaction search use the below endpoint:
<img width="1419" alt="image" src="https://user-images.githubusercontent.com/50373019/182848782-c16d9ce4-aa3f-429f-b8ba-c0b2175d767a.png">
It will return the list of transactions that the given type has.
To update a transaction, use the below endpoint with the transaction id you want to change.
<img width="1413" alt="image" src="https://user-images.githubusercontent.com/50373019/182848993-cbc78151-b3c2-4858-8c43-8d4538d5f29d.png">
It will return the updated transaction as a response body.
For deleting a transaction, use the below endpoint:
<img width="1425" alt="image" src="https://user-images.githubusercontent.com/50373019/182849561-f8f23f84-d67b-4a33-a75e-5c431362ebe0.png">
It will return the deleted transaction as a response body.

Exceptions:
If no transaction is found on DB, the below exception is thrown.
<img width="1422" alt="image" src="https://user-images.githubusercontent.com/50373019/182849336-9e699e57-0f30-42fa-8336-d93a4c88f946.png">
If the size of the String body's longer than 255, it throws the DataIntegrityViolationException.

Cache:
Redisson is used for caching mechanisms.
Update and delete operations are evicted using @CacheEvict annotation and get operations are cached using @Cacheable annotation.

Test:
Tests are not implemented since the lack of time, but I can implement them if it is asked.
Good luck!
