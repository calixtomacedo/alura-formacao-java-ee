package br.com.cmdev.javaejsf2iii.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import br.com.cmdev.javaejsf2iii.dao.VendaDAO;
import br.com.cmdev.javaejsf2iii.model.Venda;

public class VendaBusiness implements Serializable {

	private static final long serialVersionUID = 3507511070893608908L;
	
	@Inject
	private VendaDAO vendaDAO;
	
	public void gravar(Venda venda) {
		venda.setDataVenda(LocalDateTime.now());
		vendaDAO.gravar(venda);
	}
	
	public List<Venda> listar(){
		return vendaDAO.listar();
	}

}
