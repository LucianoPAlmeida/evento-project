package br.ucb.projeto.model.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ucb.projeto.model.enuns.TipoParceiro;
@Entity
@Table(name = "tb_parceiros")
@NamedQueries({
	@NamedQuery(name = "allParceiros",query = "select a from Parceiro a")
})
@XmlRootElement
public class Parceiro implements Serializable{
	
	private static final long serialVersionUID = 1662258273657717746L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private ImagePath logo;
	private TipoParceiro tipo;
	public Parceiro() {
		
	}
	
	
	public Parceiro(String nome, String descricao, ImagePath logo,TipoParceiro tipo) {
		setNome(nome);
		setDescricao(descricao);
		setLogo(logo);
		setTipo(tipo);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ImagePath getLogo() {
		return logo;
	}
	public void setLogo(ImagePath logo) {
		this.logo = logo;
	}
	public TipoParceiro getTipo() {
		return tipo;
	}
	public void setTipo(TipoParceiro tipo) {
		this.tipo = tipo;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
		result = prime * result + ((getNome() == null) ? 0 : getNome().hashCode());
		result = prime * result + ((getTipo() == null) ? 0 : getTipo().hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Parceiro){
			Parceiro p = (Parceiro)obj;
			return (getDescricao().equalsIgnoreCase(p.getDescricao()) && getLogo().equals(p.getLogo()) && getNome().equalsIgnoreCase(p.getNome()) && getTipo() == p.getTipo());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Parcerio [nome=" + getNome() + ", descricao=" + getDescricao()
				+ ", logo=" + getLogo() + ", tipo=" + getTipo()+ "]";
	}
	
}
