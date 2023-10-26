package example.springrest;

import example.springrest.errors.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Address> getAddressByIdResponse;

    @MockBean
    private GetAddressByCepService service;


    @Test
    @DisplayName("It should return 200 http status code when receive valid cep")
    void getAddress() throws Exception {
        String validCep = "70000000";

        Address mockAddress = new Address("70000000", "Esplanada dos Ministerios", "Bloco A",
                "Zona Civico-Administrativa", "Brasilia", "DF");

        Mockito.when(service.execute(any())).thenReturn(mockAddress);

        var response = mvc
                .perform(get("/address/{cep}", validCep))
                .andReturn().getResponse();

        var responseExpectation = getAddressByIdResponse.write(mockAddress).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(responseExpectation);
    }

    @Test
    @DisplayName("It should return 400 http status code when receive invalid cep")
    void getAddress_NotFound() throws Exception {

        String invalidCep = "70000000";

        Mockito.when(service.execute(invalidCep))
                .thenThrow(new NotFoundException("Error"));

        var response = mvc
                .perform(get("/address/{cep}", invalidCep))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}
