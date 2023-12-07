package dentall.rest;

import dentall.domain.Address;
import dentall.service.AddressService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@PreAuthorize("hasRole('ROLE_SMJESTAJNI') or hasRole('ROLE_KORISNICKI') or hasRole('ROLE_PRIJEVOZNI')")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public List<Address> listAddress(){
        return addressService.listAll();
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return addressService.findById(id).orElseThrow(() -> new ItemNotFoundException("Address with id '" + id + "' not found"));
    }

    @PostMapping("")
    public Address createAddress(@RequestBody Address address) {
        return addressService.createAddress(address.getCity(), address.getStreet(), address.getNumber());
    }
}
