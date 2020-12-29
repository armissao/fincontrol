package br.com.finentrepise.fincontrol.model.service;

import java.util.List;

import br.com.finentrepise.fincontrol.model.entity.Lancamento;
import br.com.finentrepise.fincontrol.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamento);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);
	
}
