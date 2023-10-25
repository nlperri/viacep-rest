package example.springrest;

import example.springrest.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAddressByCepService {

    @Autowired
    InMemoryAddressRepository repository;

    public Address execute(String cep) {
        var address = repository.findAddressByCep(cep);

        if(address == null) {
            throw new NotFoundException("Endereço não encontrado.");
        }

        return address;
    }
}
