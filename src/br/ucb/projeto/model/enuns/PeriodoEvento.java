package br.ucb.projeto.model.enuns;

public enum PeriodoEvento {
	MANHA("Manh�"),TARDE("Tarde"),NOITE("Noite");
	
	private String value;
	
	private PeriodoEvento(String value){
		this.value =  value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
