package br.com.finentrepise.fincontrol.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import br.com.finentrepise.fincontrol.model.enums.StatusLancamento;
import br.com.finentrepise.fincontrol.model.enums.TipoLancamento;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "lancamento")
@Builder
@Data
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "valor")
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioId;
	@Column(name = "data_cadastro")
	/*
	 * Convert Necessario para o LocalDate, Se fosse Date ou Calendar não seria
	 * necessario, so com @Temporal no caso de Date ou Calendar.
	 */
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipoLancamento;
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento statusLancamento;

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", usuarioId=" + usuarioId
				+ ", dataCadastro=" + dataCadastro + ", tipoLancamento=" + tipoLancamento + ", statusLancamento="
				+ statusLancamento + "]";
	}

}
