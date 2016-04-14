# microservice-chassis

https://www.safaribooksonline.com/library/view/developing-ops-friendly-microservices/9781771375504/part18.html

## config
Microservice.run() - int port = injector.getInstance(Config.class).getInt("microservice.port") reads from reference.conf

ConfigurationResource.getResource() - config.getString("sample.config") reads from application.conf

## Test Run

### default
1, Launch MyMicroservice at MyMicroservice.main()
2, Open browser and go to http://localhost:8080/ - output: { sample.config: "hello", microservice.name: "test-microservice"}

### add env variables
1, Environment variables: SAMPLE_CONFIG=goodbye;PORT=8081
2, Open browser and go to http://localhost:8081/ - output: { sample.config: "goodbye", microservice.name: "test-microservice"}
