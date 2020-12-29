package br.com.finentrepise.fincontrol.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.finentrepise.fincontrol.model.entity.Usuario;
import br.com.finentrepise.fincontrol.model.repository.UsuarioRepository;
import br.com.finentrepise.fincontrol.exception.*;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado!");
		} else if(usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha não corresponde!");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
			
		boolean existe = usuarioRepository.existsByEmail(email);
		
		if(existe) {
			throw new RegraDeNegocioException("E-mail Cadastrado!");
		}
	}

	
}
