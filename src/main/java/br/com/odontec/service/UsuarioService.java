package br.com.odontec.service;

import br.com.odontec.model.Usuario;
import br.com.odontec.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Usuario> autenticar(String login, String senha) {
        ValidacaoService.textoObrigatorio(login, "Login");
        ValidacaoService.textoObrigatorio(senha, "Senha");
        return repository.autenticar(login.trim(), senha);
    }
}
