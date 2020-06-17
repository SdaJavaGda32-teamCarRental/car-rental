package pl.sdacademy.carrental.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.carrental.domain.Address;
import pl.sdacademy.carrental.domain.Client;
import pl.sdacademy.carrental.model.ClientForm;
import pl.sdacademy.carrental.repositories.AddressRepository;
import pl.sdacademy.carrental.repositories.ClientRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void createClient(final ClientForm clientForm) {

        final Address address = createAddressFromClientForm(clientForm);

        final Client client = Client.builder()
                .firstName(clientForm.getFirstName())
                .lastName(clientForm.getLastName())
                .email(clientForm.getEmail())
                .address(address)
                .phoneNumber(clientForm.getPhoneNumber())
                .build();
        clientRepository.save(client);
    }

    public void delete(final Long id) {
        final Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
    }

    public void updateClient(final Long id, final ClientForm clientForm) {

        final Address address = createAddressFromClientForm(clientForm);

        final Client client = clientRepository.findById(id).orElseThrow();
        client.setFirstName(clientForm.getFirstName());
        client.setLastName(clientForm.getLastName());
        client.setAddress(address);
        clientRepository.save(client);
    }



    private Address createAddressFromClientForm(final ClientForm clientForm){
        final Address address = Address.builder()
                .city(clientForm.getCity())
                .street(clientForm.getStreet())
                .building(clientForm.getBuilding())
                .apartment(clientForm.getApartment())
                .zipCode(clientForm.getZipCode())
                .build();
        return addressRepository.save(address);
    }

    public ClientForm getById(final Long id) throws IOException {
        final Client client = clientRepository.findById(id).orElseThrow(IOException::new);
        final Address address = client.getAddress();
        return ClientForm.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .city(address.getCity())
                .street(address.getStreet())
                .building(address.getBuilding())
                .apartment(address.getApartment())
                .zipCode(address.getZipCode())
                .phoneNumber(client.getPhoneNumber())
                .build();

    }

}
