package example.springrest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class InMemoryAddressRepository implements AddressRepository{
    Collection<Address> items = new ArrayList<>();

    public InMemoryAddressRepository() {
        items.add(new Address("70000000", "Esplanada dos Ministérios", "Bloco A", "Zona Cívico-Administrativa", "Brasília", "DF"));
        items.add(new Address("01311000", "Avenida Paulista", "Conjunto Nacional", "Bela Vista", "São Paulo", "SP"));
        items.add(new Address("20000000", "Avenida Rio Branco", "Centro", "Centro", "Rio de Janeiro", "RJ"));
        items.add(new Address("70070900", "Setor de Autarquias Sul", "Quadra 3", "Zona Cívico-Administrativa", "Brasília", "DF"));
        items.add(new Address("60175000", "Avenida Beira Mar", "Mucuripe", "Mucuripe", "Fortaleza", "CE"));
    }

    @Override
    public Address findAddressByCep(String cep) {
        for (Address address : items) {
            if(address.getCep().equals(cep)) {
                return address;
            }
        }
        return null;
    }
}
