package br.ucb.projeto.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Evento;

@FacesConverter("eventoIdConverter")
public class EventoIdConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null){
			return new 	EventoDAO().find(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && value instanceof Evento){
			Evento e = (Evento)value;
			return e.getId().toString();
		}
		return null;
	}

}
