package br.ucb.projeto.model.beans;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.ucb.projeto.model.enuns.EventType;


@Entity
@Table(name = "tb_palestras")
@XmlRootElement
public class Palestra extends Evento{
	private static final long serialVersionUID = -3484866591100093218L;
	@ManyToOne
	@JoinColumn(name = "id_palestrante")
	private Palestrante palestrante;
	
	public Palestra() {
		
	}
	public Palestra(String title, String summary,ImagePath photo,
			GregorianCalendar data,Palestrante palestrante){
		super(title, summary, photo, data);
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
	public boolean equals(Object obj) {
		if(obj instanceof Palestra){
			Palestra p = (Palestra)obj;
			return (super.equals(obj) && p.equals(getPalestrante()));
		}
		return false;
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
