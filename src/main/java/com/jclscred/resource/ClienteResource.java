package com.jclscred.resource;

import com.jclscred.domain.Cliente;
import com.jclscred.dto.ClienteDTO;
import com.jclscred.dto.ClienteNewDTO;
import com.jclscred.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private ClienteService service;

    @Autowired
    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Long id,
                                        @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println("Usuario logado: " + userDetails.getUsername());
        System.out.println("Roles Usuario logado: " + userDetails.getAuthorities());
        System.out.println("Roles Usuario logado: " + userDetails.getPassword());

        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objNewDTO) {
        Cliente obj = service.fromDto(objNewDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
        Cliente cliente = service.fromDto(clienteDTO);
        cliente.setId(id);
        cliente = service.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> listCliente = service.findAll();
        List<ClienteDTO> listClienteDTO = listCliente.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listClienteDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> listCliente = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listClienteDTO = listCliente.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listClienteDTO);
    }
}
