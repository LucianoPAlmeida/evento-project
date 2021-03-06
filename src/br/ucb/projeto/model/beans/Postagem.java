package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.ucb.projeto.util.DateUtil;
@Entity
@Table(name = "tb_postagens")
@NamedQueries({
	@NamedQuery(name = "allPostagens",query = "select a from Postagem a")
})
@XmlRootElement
public class Postagem implements Serializable{
	
	private static final long serialVersionUID = -392495796361211470L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	@Column(length = 2000)
	private String texto;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	public Postagem() {
		setData(new GregorianCalendar());
	}
	public Postagem(String titulo, String texto, Calendar data) {
		this();
		setTitulo(titulo);
		setTexto(texto);
		setData(data);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@XmlTransient
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	@XmlElement
	public String getDataString(){
		if(getData() == null ) return null;
		int mes = getData().get(Calendar.MONTH)+1;
		return DateUtil.stringIntComZerosEsquerda(getData().get(Calendar.DAY_OF_MONTH))+"/"+DateUtil.stringIntComZerosEsquerda(mes)+"/"+getData().get(Calendar.YEAR);
	}
	@XmlElement
	public String getHoraString(){
		if(getData() == null) return null;
		return DateUtil.stringIntComZerosEsquerda(getData().get(Calendar.HOUR_OF_DAY))+":"+DateUtil.stringIntComZerosEsquerda(getData().get(Calendar.MINUTE));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
		result = prime * result + ((getTexto() == null) ? 0 : getTexto().hashCode());
		result = prime * result + ((getTitulo() == null) ? 0 : getTitulo().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Postagem){
			Postagem p = (Postagem)obj;
			return (getTitulo().equalsIgnoreCase(p.getTitulo()) && getTexto().equalsIgnoreCase(p.getTexto()) && getData().equals(p.getData()));
		}
		return false;
	}
	@Override
	public String toString() {
		return "Postagem [id=" + getId() + ", titulo=" + getTitulo() + ", texto=" + getTexto()
				+ ", data=" + getData() + "]";
	}
	
	
}
