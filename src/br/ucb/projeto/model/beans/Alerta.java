package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import br.ucb.projeto.util.DateUtil;
@Entity
@Table(name = "tb_alertas")
public class Alerta implements Serializable{
	
	private static final long serialVersionUID = -3178316612012862493L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String descricao;
	private Calendar data;
	
	public Alerta() {
		setData(new GregorianCalendar());
	}
	
	public Alerta(String titulo, String descricao, Calendar data) {
		setTitulo(titulo);
		setData(data);
		setDescricao(descricao);
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		int mes = getData().get(Calendar.MONTH)+1;
		return DateUtil.stringIntComZerosEsquerda(getData().get(Calendar.DAY_OF_MONTH))+"/"+DateUtil.stringIntComZerosEsquerda(mes)+"/"+getData().get(Calendar.YEAR);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
		result = prime * result
				+ ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((getTitulo() == null) ? 0 : getTitulo().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Alerta){
			Alerta alert = (Alerta)obj;
			return (getTitulo().equalsIgnoreCase(alert.getTitulo()) && getDescricao().equalsIgnoreCase(alert.getDescricao()) && getData().equals(alert.getData()));
		}
		return false;
	}
	@Override
	public String toString() {
		return "Alerta [titulo=" + getTitulo() + ", descricao=" + getDescricao()
				+ ", data=" + getData() + "]";
	}
}
