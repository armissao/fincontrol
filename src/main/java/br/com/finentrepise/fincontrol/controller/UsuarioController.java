package br.com.finentrepise.fincontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finentrepise.fincontrol.exception.RegraDeNegocioException;
import br.com.finentrepise.fincontrol.model.entity.Usuario;
import br.com.finentrepise.fincontrol.model.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {

		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();

		try {

			Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraDeNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
}
