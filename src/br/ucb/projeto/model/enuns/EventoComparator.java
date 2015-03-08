package br.ucb.projeto.model.enuns;

import java.util.Comparator;

import br.ucb.projeto.model.beans.Evento;

public enum EventoComparator implements Comparator<Evento>{
	POR_TITLE{

		@Override
		public int compare(Evento o1, Evento o2) {
			return o1.getTitle().compareToIgnoreCase(o2.getTitle());
		}
		
	}
}
