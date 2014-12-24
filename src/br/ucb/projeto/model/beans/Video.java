package br.ucb.projeto.model.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_videos")
@NamedQueries({
	@NamedQuery(name = "allVideos",query = "select a from Video a")
})
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String descricao;
	private String link;
	
	public Video() {
	}
	
	public Video(String titulo, String descricao, String link) {
		setTitulo(titulo);
		setDescricao(descricao);
		setLink(link);
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((getLink() == null) ? 0 : getLink().hashCode());
		result = prime * result + ((getTitulo() == null) ? 0 : getTitulo().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Video){
			Video v = (Video) obj;
			return (getDescricao().equalsIgnoreCase(v.getDescricao()) && getLink().equals(v.getLink()) && getTitulo().equalsIgnoreCase(v.getTitulo()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "Video [id=" + getId() + ", titulo=" + getTitulo() + ", descricao="
				+ getDescricao() + ", link=" + getLink() + "]";
	}
}
