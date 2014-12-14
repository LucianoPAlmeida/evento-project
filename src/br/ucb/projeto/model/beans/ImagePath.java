package br.ucb.projeto.model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
@Entity
@Table(name = "tb_paths")
@XmlRootElement
public class ImagePath implements Serializable{

	private static final long serialVersionUID = -2529224282388347852L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = true)
	private String path;
	
	public ImagePath(){
		
	}
	public ImagePath(String path){
		setPath(path);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlTransient
	public String getPath() {
		return path;
	}
	@XmlElement
	public String getSimplePath(){
		if(getPath() != null){
			String strArray[] = getPath().replace('\\','/').split("/");
			String str = null;
			if(strArray.length > 2){
				str = strArray[strArray.length-2].replace("/","")+"/"+strArray[strArray.length-1];
			}
			return str;
		}
		return null;
	}
	public void setPath(String path) {
		this.path = path;
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
		if(obj instanceof ImagePath){
			ImagePath imgPath = (ImagePath)obj;
			return getId().equals(imgPath.getId());
		}
		return false;
	}
	@Override
	public String toString() {
		return "ImagePath [id=" + getId() + ", path=" + getPath() + "]";
	}
	
}
