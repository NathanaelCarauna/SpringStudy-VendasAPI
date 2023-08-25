package io.gihub.nathanaelcarauna.api.controller;

import io.gihub.nathanaelcarauna.api.dto.CredetinalsDTO;
import io.gihub.nathanaelcarauna.api.dto.TokenDTO;
import io.gihub.nathanaelcarauna.domain.entity.Usuario;
import io.gihub.nathanaelcarauna.exception.SenhaInvalidaException;
import io.gihub.nathanaelcarauna.security.jwt.JwtService;
import io.gihub.nathanaelcarauna.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredetinalsDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder().login(credenciais.getLogin()).senha(credenciais.getSenha()).build();
            usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
