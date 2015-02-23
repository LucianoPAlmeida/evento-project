package br.ucb.projeto.model.beans;

import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.model.enuns.LocalEvento;
import br.ucb.projeto.model.enuns.PeriodoEvento;
@Entity
@Table(name = "tb_workshops")
@XmlRootElement
@DiscriminatorValue("Workshop")
public class WorkShop extends Evento{
	private static final long serialVersionUID = 8441081049529303120L;
	@ManyToOne
	@JoinColumn(name = "id_convidado")
	private Palestrante convidado;
	public WorkShop() {
	}
	public WorkShop(Evento evento){
		this(evento.getTitle(), evento.getSummary(),evento.getPhoto(), evento.getData(), evento.getLocal(),evento.getPeriodo(),null);
		setId(evento.getId());
	}
	public WorkShop(String title, String summary,ImagePath photo,
			GregorianCalendar data,LocalEvento local,PeriodoEvento periodo,Palestrante convidado){
		super(title, summary, photo, data,local,periodo);
		setConvidado(convidado);
	}
	
	public Palestrante getConvidado() {
		return convidado;
	}
	public void setConvidado(Palestrante convidado) {
		this.convidado = convidado;
	}
	@Override
	public EventType getTipo() {
		return EventType.WORKSHOP;
	}

}
