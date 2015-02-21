package br.ucb.projeto.model.enuns;

public enum PeriodoEvento {
	MANHA("Manhã"),TARDE("Tarde"),NOITE("Noite");
	
	private String value;
	
	private PeriodoEvento(String value){
		this.value =  value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
