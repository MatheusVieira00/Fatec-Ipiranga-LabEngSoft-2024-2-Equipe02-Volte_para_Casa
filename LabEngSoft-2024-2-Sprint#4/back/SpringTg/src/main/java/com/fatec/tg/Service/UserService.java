package com.fatec.tg.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.RepresentanteEntity;
import com.fatec.tg.repository.RepresentanteRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RepresentanteRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("PASSOU AMEM");
    System.out.println("email: " + email);
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        RepresentanteEntity representante = repository.
                                                        findByEmail(email)
                                                        .orElseThrow(()-> new UsernameNotFoundException("Email nao encontrado") );
        

                                                        return User
                .builder()
                .username(representante.getEmail())
                .password(representante.getSenha())
                .roles("USER")
                .build()
               ;
    }
}
