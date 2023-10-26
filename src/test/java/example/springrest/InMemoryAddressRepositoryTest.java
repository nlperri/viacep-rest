package example.springrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
public class InMemoryAddressRepositoryTest {

    @Autowired
    private InMemoryAddressRepository repository;


    @Test
    @DisplayName("It should find the address by cep")
    void findAddressByCep() {
        String cep = "70000000";
        Address expectedAddress = new Address("70000000", "Esplanada dos Ministerios", "Bloco A", "Zona Civico-Administrativa", "Brasilia", "DF");

        repository.insert(expectedAddress);

        Address foundAddress = repository.findAddressByCep(cep);

        assertEquals(expectedAddress, foundAddress);
    }

    @Test
    @DisplayName("It should return null for non-existing cep")
    void findAddressByCep_NotFound() {
        String nonExistingCep = "99999999";

        Address foundAddress = repository.findAddressByCep(nonExistingCep);

        assertNull(null, foundAddress);
    }

}
