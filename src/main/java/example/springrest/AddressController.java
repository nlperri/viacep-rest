package example.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    GetAddressByCepService service;

    @GetMapping("/{cep}")
    public ResponseEntity<Address> get(@PathVariable String cep) {
        var address = service.execute(cep);

        return ResponseEntity.ok(address);
    }

}
