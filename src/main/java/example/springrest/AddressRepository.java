package example.springrest;

import java.util.Collection;
import java.util.List;

public interface AddressRepository {
    Address findAddressByCep(String cep);
    Collection<Address> fetchAll();

    void insert(Address address);

}
