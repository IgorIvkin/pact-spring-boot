# Pact "producer-consumer" setup on Spring Boot

This small project demonstrates how to work with **Pact**, a framework to
write contracting tests (CDC). It includes three main directories.

`pact-provider` - contains an example of provider microservice.
It returns the following bank product when you call it
by `http://localhost:8081/bankproducts/1`, all the other numbers will produce error 404.

    {
       "id":1,
       "title":"Personal bank card",
       "customerName":"John Ivkin",
       "createdDate":"2021-03-08T12:57:03.8015454"
    }

`pact-consumer` - contains a web-client microservice based on `RestTemplate` that takes a
bank product from the provider's service. But most importantly it contains a test
suite that will build a new Pact configuration file for you after tests are passed.

This Pact configuration file will be created in `pact-consumer\build\pacts\pact-consumer-pact-provider.json`.
Make sure you are running tests in `pact-consumer` before.

I put also the expected output to the dedicated directory `PACT_OUTPUT`. This file
was created automatically by Pact, I just copied it from the folder mentioned above.

## How to launch?

At the moment you only need to launch tests from `pact-consumer` because every call
to `pact-provider` service is mocking currently.

If you want to test pact-provider itself then make sure you've 
started `pact-provider` first. The easiest way to do that is to execute:

    cd pact-provider
    gradle build
    cd build/libs
    java -jar pact-provider-0.0.1-SNAPSHOT.jar

Or you can build and run it from your preferred IDE. Then you can comment 
@Pact-related annotations in the test file and they will go directly to `pact-provider`
instead of mock.


## Useful links

* [Official Pact documentation](https://docs.pact.io/)
* [Pact for JVM](https://docs.pact.io/implementation_guides/jvm/)