package br.ucb.projeto.model.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

import br.ucb.projeto.model.enuns.EventType;
import br.ucb.projeto.model.enuns.LocalEvento;
import br.ucb.projeto.util.DateUtil;
@Entity
@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "tb_eventos")
@XmlRootElement
@XmlSeeAlso({Palestra.class, WorkShop.class})
@NamedQueries({
	@NamedQuery(name = "getAllEventos",query = "select a from Evento a"),
	@NamedQuery(name = "findByLocal",query = "select a from Evento a where a.local=:local")
})
@Cache(type = CacheType.NONE)
@DiscriminatorColumn(name= "type",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Evento")
public abstract class Evento implements Serializable{

	private static final long serialVersionUID = -4619336347660324525L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "title")
	private String title;
	@Column(name = "summary",length = 1000)
	private String summary;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_photo")
	private ImagePath photo;
	@Enumerated(EnumType.ORDINAL)
	private LocalEvento local;
	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar data;
	
	public Evento(){
		setPhoto(new ImagePath());
		setData(new GregorianCalendar());
	}
	public Evento(String title, String summary,ImagePath photo,
			GregorianCalendar data) {
		this();
		setId(null);
		setTitle(title);
		setSummary(summary);
		setPhoto(photo);
		setData(data);
	}
	public Evento(String title, String summary,ImagePath photo,
			GregorianCalendar data,LocalEvento local) {
		this(title, summary, photo, data);
		setLocal(local);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setPhoto(ImagePath photo) {
		this.photo = photo;
	}
	
	public LocalEvento getLocal() {
		return local;
	}
	public void setLocal(LocalEvento local) {
		this.local = local;
	}
	@XmlElement
	public abstract EventType getTipo();
	
	@XmlTransient
	public GregorianCalendar getData() {
		return data;
	}
	public void setData(GregorianCalendar data) {
		this.data = data;
	}
	@XmlElement
	public String getDiaEvento(){
		int mes = getData().get(Calendar.MONTH)+1;
		return DateUtil.stringIntComZerosEsquerda(getData().get(Calendar.DAY_OF_MONTH))+"/"+DateUtil.stringIntComZerosEsquerda(mes)+"/"+getData().get(Calendar.YEAR);
	}
	@XmlElement
	public String getHoraEvento(){
		int hora = getData().get(Calendar.HOUR_OF_DAY);
		int minuto = getData().get(Calendar.MINUTE);
		return DateUtil.stringIntComZerosEsquerda(hora) +":"+DateUtil.stringIntComZerosEsquerda(minuto);
	}
	public ImagePath getPhoto() {
		return photo;
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
		if(obj instanceof Evento){
			Evento e = (Evento)obj;
			return getId().equals(e.getId());
		}
		return false;
	}
	@Override
	public String toString() {
		return "Evento [id=" + getId() + ", title=" + getTitle() + ", summary=" + getSummary()
				+ ", photo=" + getPhoto() + ", data=" + getData() + ", tipo=" + getTipo()
				+ "]";
	}
	
}
