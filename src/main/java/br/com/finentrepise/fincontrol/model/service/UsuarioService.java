package br.com.finentrepise.fincontrol.model.service;

import br.com.finentrepise.fincontrol.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

}
