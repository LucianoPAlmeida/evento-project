package br.ucb.projeto.model.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "getAllPalestrantes",query = "select a from Palestrante a")
})
public class Palestrante implements Serializable{
	
	private static final long serialVersionUID = 6496684441879846240L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(length = 1000)
	private String summary;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_photo")
	private ImagePath photo;
	
	public Palestrante(){
		setPhoto(new ImagePath());
	}
	
	public Palestrante(String name, String summary, ImagePath photo) {
		setName(name);
		setSummary(summary);
		setPhoto(photo);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public ImagePath getPhoto() {
		return photo;
	}
	public void setPhoto(ImagePath photo) {
		this.photo = photo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Palestrante){
			Palestrante p = (Palestrante)obj;
			return getId().equals(p.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Palestrante [id=" + getId() + ", name=" + getName() + ", summary="
				+ getSummary() + ", photo=" + getPhoto()
				+ "]";
	}
}
