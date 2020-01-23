import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should return user data when request param is one"
    request{
        method GET()
        url("/users/1") {
            queryParameters {

            }
        }
    }
    response {
        body(
                name: "Paco",
                age: 22,
                city: "Madrid"
        )
        status 200
    }
}