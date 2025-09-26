package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpec {
    
    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
            
    public static ResponseSpecification createdResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
            
    public static ResponseSpecification badRequestResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();
            
    public static ResponseSpecification unauthorizedResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .build();
            
    public static ResponseSpecification notFoundResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .build();

    public static ResponseSpecification noContentResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .build();

}
