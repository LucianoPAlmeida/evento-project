package br.ucb.projeto.model.beans;

import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.model.enuns.LocalEvento;
@Entity
@Table(name = "tb_workshops")
@XmlRootElement
@DiscriminatorValue("Workshop")
public class WorkShop extends Evento{
	private static final long serialVersionUID = 8441081049529303120L;
	public WorkShop() {
	}
	public WorkShop(Evento evento){
		this(evento.getTitle(), evento.getSummary(),evento.getPhoto(), evento.getData(), evento.getLocal());
		setId(evento.getId());
	}
	public WorkShop(String title, String summary,ImagePath photo,
			GregorianCalendar data){
		super(title, summary, photo, data);
	}
	
	public WorkShop(String title, String summary,ImagePath photo,
			GregorianCalendar data,LocalEvento local){
		super(title, summary, photo, data,local);
	}
	@Override
	public EventType getTipo() {
		return EventType.WORKSHOP;
	}

}
