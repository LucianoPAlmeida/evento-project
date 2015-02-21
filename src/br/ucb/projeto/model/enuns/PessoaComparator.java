package br.ucb.projeto.model.enuns;

import java.util.Comparator;

import br.ucb.projeto.model.beans.Pessoa;

public enum PessoaComparator implements Comparator<Pessoa>{
	POR_DATA_INSCRICAO{
		@Override
		public int compare(Pessoa o1, Pessoa o2) {
			return o1.getDataInscricao().compareTo(o2.getDataInscricao());
		}
		
	}
}
