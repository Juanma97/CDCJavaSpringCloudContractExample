package producer;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import producer.ContractTest;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;

public class PackageTest extends ContractTest {

	@Test
	public void validate_shouldReturnUserDataWhenRequestParamIsOne() throws Exception {
		// given:
			MockMvcRequestSpecification request = given();

		// when:
			ResponseOptions response = given().spec(request)
					.get("/users/1");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
			assertThatJson(parsedJson).field("['age']").isEqualTo(22);
			assertThatJson(parsedJson).field("['name']").isEqualTo("Paco");
			assertThatJson(parsedJson).field("['city']").isEqualTo("Madrid");
	}

}
