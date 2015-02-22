package br.ucb.model.comparators;

import java.util.Calendar;
import java.util.Comparator;

import br.ucb.projeto.model.beans.Pessoa;

public class EventQueueComparator implements Comparator<Pessoa>{
	private Integer idEvento;
	public EventQueueComparator() {
	}
	public EventQueueComparator(Integer idEvento){
		setIdEvento(idEvento);
	}
	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	@Override
	public int compare(Pessoa o1, Pessoa o2) {
		Calendar data1 = o1.getDataIncricao(getIdEvento()); 
		Calendar data2 = o2.getDataIncricao(getIdEvento()); 
		if(data1 == null || data2 == null)
			return 0;
		return  data1.compareTo(data2);
	}
	
}
