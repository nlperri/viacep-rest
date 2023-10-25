package example.springrest;

public interface AddressRepository {
    Address findAddressByCep(String cep);

}
