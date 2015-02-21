package br.ucb.projeto.model.beans;

import java.util.GregorianCalendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.model.enuns.LocalEvento;
import br.ucb.projeto.model.enuns.PeriodoEvento;


@Entity
@Table(name = "tb_palestras")
@XmlRootElement
@DiscriminatorValue("Palestra")
public class Palestra extends Evento{
	private static final long serialVersionUID = -3484866591100093218L;
	@ManyToOne
	@JoinColumn(name = "id_palestrante")
	private Palestrante palestrante;
	
	public Palestra() {
		
	}
	public Palestra(Evento evento){
		this(evento.getTitle(), evento.getSummary(),evento.getPhoto(), evento.getData(), evento.getLocal(),evento.getPeriodo(), null);
		setId(evento.getId());
	}
	public Palestra(String title, String summary,ImagePath photo,
			GregorianCalendar data,LocalEvento local,PeriodoEvento periodo,Palestrante palestrante){
		super(title, summary, photo, data,local,periodo);
		setPalestrante(palestrante);
	}
	@XmlElement
	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((getPalestrante() == null) ? 0 : getPalestrante().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return super.toString()+"Palestra [palestrante=" + getPalestrante() + "]";
	}

	@Override
	public EventType getTipo() {
		return EventType.PALESTRA;
	}
}
