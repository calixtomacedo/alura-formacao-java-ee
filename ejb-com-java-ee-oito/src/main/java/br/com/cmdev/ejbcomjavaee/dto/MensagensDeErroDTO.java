package br.com.cmdev.ejbcomjavaee.dto;

import java.util.Date;
import java.util.List;

public class MensagensDeErroDTO {
	
	private List<String> mensagens;
	private Date dataErro;
	
	public List<String> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	public Date getDataErro() {
		return dataErro;
	}
	public void setDataErro(Date dataErro) {
		this.dataErro = dataErro;
	}
	

	public static MensagensDeErroDTO build(List<String> mensagens) {
		MensagensDeErroDTO mensagensDeErroDTO = new MensagensDeErroDTO();
		mensagensDeErroDTO.setMensagens(mensagens);
		mensagensDeErroDTO.setDataErro(new Date());
		return mensagensDeErroDTO;
	}
	
	
}
