package br.com.cmdev.javaeeparteiii.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.cmdev.javaeeparteiii.model.Promo;
import br.com.cmdev.javaeeparteiii.utils.websockets.PromoEndpoint;

@Model
public class AdminPromoBean {

	private Promo promo = new Promo();
	
	@Inject
	private PromoEndpoint promos;

	public void enviar() {
		promos.send(promo);
	}

	public Promo getPromo() {
		return promo;
	}
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	
	
}
