package br.com.finentrepise.fincontrol.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.finentrepise.fincontrol.exception.RegraDeNegocioException;
import br.com.finentrepise.fincontrol.model.entity.Lancamento;
import br.com.finentrepise.fincontrol.model.enums.StatusLancamento;
import br.com.finentrepise.fincontrol.model.repository.LancamentoRepository;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private LancamentoRepository repository;

	public LancamentoServiceImpl(LancamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		validar(lancamento);
		Objects.requireNonNull(lancamento.getId());
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional( readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamento) {
		Example example = Example.of(lancamento,
				ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
		lancamento.setStatusLancamento(statusLancamento);
		atualizar(lancamento);
	}
	
	public void validar(Lancamento lancamento) {
		
		if(lancamento.getDescricao().isEmpty() || lancamento.getDescricao().equals("")) {
			throw new RegraDeNegocioException("Informa Descrição");
		}
		
		if(lancamento.getDataCadastro() == null) {
			throw new RegraDeNegocioException("Informa Data");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1 ) {
			throw new RegraDeNegocioException("Informa Valor");
		}
	}

}
