package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Pessoa;
import com.aquiles.helpdesk.repositories.PessoaRepository;
import com.aquiles.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> usuario = pessoaRepository.findByEmail(email);
        if(usuario.isPresent()){
            return new UserSS(
                    usuario.get().getId(),
                    usuario.get().getEmail(),
                    usuario.get().getSenha(),
                    usuario.get().getPerfis());
        }

        throw new UsernameNotFoundException(email);
    }
}
