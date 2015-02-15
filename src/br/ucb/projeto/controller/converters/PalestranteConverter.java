package br.ucb.projeto.controller.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.projeto.model.DAOS.PalestranteDAO;
import br.ucb.projeto.model.beans.Palestrante;

@FacesConverter("palestranteConverter")
public class PalestranteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if(string != null){
			PalestranteDAO dao = new PalestranteDAO();
			return dao.find(Integer.parseInt(string));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && value instanceof Palestrante){
			Palestrante palestrante = (Palestrante)value;
			return palestrante.getId().toString();
		}
		return null;
	}
	
}
