package br.ucb.projeto.model.enuns;

public enum EventType {
	PALESTRA("Palestra"),WORKSHOP("Workshop");
	
	private String type;
	private EventType(String type){
		this.type = type;
	}
	@Override
	public String toString() {
		return this.type;
	}
	
}
