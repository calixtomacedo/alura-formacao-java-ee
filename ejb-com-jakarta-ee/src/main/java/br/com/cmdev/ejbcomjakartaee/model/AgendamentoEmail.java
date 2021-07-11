package br.com.cmdev.ejbcomjakartaee.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name = "TB_AGENDAMENTOEMAIL")
public class AgendamentoEmail implements Serializable {

	private static final long serialVersionUID = -6420743595737326073L;

	@Id
	@Column(name = "IDAGENDAMENTOEMAIL")
	@SequenceGenerator(name = "TB_AGENDAMENTOEMAIL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_AGENDAMENTOEMAIL_SEQ")
	private Long id;

	@NotBlank(message = "{agendamentoEmail.email.vazio}")
	@Email(message = "{agendamentoEmail.email.ivalido}")
	private String email;
	
	@NotBlank(message = "{agendamentoEmail.assunto.vazio}")
	private String assunto;
	
	@NotBlank(message = "{agendamentoEmail.mensagem.vazio}")
	private String mensagem;
	private Boolean isEnviado;
	private LocalDateTime dataAgendamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getIsEnviado() {
		return isEnviado;
	}

	public void setIsEnviado(Boolean isEnviado) {
		this.isEnviado = isEnviado;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
}