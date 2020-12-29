package br.com.finentrepise.fincontrol.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UsuarioDTO {

	private String email;
	private String nome;
	private String senha;
}
