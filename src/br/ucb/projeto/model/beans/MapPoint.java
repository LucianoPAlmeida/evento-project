package br.ucb.projeto.model.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_locais")
@NamedQueries({
	@NamedQuery(name = "allMapLocais",query = "select a from MapPoint a")
})
@XmlRootElement
public class MapPoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer x;
	private Integer y;
	private Integer idEvento;
	
	public MapPoint(){
		
	}
	
	public MapPoint(Integer x,Integer y){
		setX(x);
		setY(y);
	}

	public MapPoint(String points){
		String x = points.substring(points.indexOf("\"x\":\"")+5,points.indexOf("\","));
		String y = points.substring(points.indexOf("\"y\":\"")+5,points.indexOf("\"}"));
		setX(Integer.parseInt(x));
		setY(Integer.parseInt(y));
	}
	public Integer getId() {
		return id;
	}
	public String getIdString(){
		return getId().toString();
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	
}