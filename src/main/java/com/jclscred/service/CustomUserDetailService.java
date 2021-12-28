package com.jclscred.service;

import com.jclscred.domain.Cliente;
import com.jclscred.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private ClienteRepository clienteRepository;

    @Autowired
    public CustomUserDetailService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = Optional.ofNullable(clienteRepository.findByEmail(email))
                .orElseThrow( () -> new UsernameNotFoundException("Cliente não encontrado!"));

//        System.out.println("E-Mail: "+ cliente.getEmail());

        List<GrantedAuthority> authoritiesListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new User(cliente.getEmail(), cliente.getSenha(), authoritiesListUser);
    }
}
