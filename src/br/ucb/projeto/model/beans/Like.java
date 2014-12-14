package br.ucb.projeto.model.beans;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name = "tb_likes")
@NamedQueries({
	@NamedQuery(name = "allLikes",query = "select a from Like a"),
	@NamedQuery(name = "likesByUser",query = "select a from Like a where a.idUser=:id"),
	@NamedQuery(name = "likesByEvento",query = "select a from Like a where a.idEvento=:id")
})
@XmlRootElement
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer idUser;
	private Integer idEvento;
	@Temporal(TemporalType.TIMESTAMP) 
	private Calendar date;
	
	public Like(){
		setDate(new GregorianCalendar());
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getIdEvento() == null) ? 0 : getIdEvento().hashCode());
		result = prime * result + ((getIdUser() == null) ? 0 : getIdUser().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Like){
			Like like = (Like)obj;
			return(getIdEvento().equals(like.getIdEvento()) && getIdUser().equals(like.getIdUser()));
		}
		return false;
	}
	@Override
	public String toString() {
		return "Like [idUser=" + getIdUser() + ", idEvento=" + getIdEvento() + ", date="
				+ getDate() + "]";
	}
	
}
