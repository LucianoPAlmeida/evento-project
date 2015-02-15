package br.ucb.projeto.model.beans;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeName;

import br.ucb.projeto.model.enuns.EventType;
@Entity
@Table(name = "tb_workshops")
@XmlRootElement
@JsonTypeName("workshop")
public class WorkShop extends Evento{
	private static final long serialVersionUID = 8441081049529303120L;
	public WorkShop() {
	}
	
	public WorkShop(String title, String summary,ImagePath photo,
			GregorianCalendar data){
		super(title, summary, photo, data);
	}
	@Override
	public EventType getTipo() {
		return EventType.WORKSHOP;
	}

}
