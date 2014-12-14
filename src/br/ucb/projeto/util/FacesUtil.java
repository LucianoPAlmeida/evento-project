package br.ucb.projeto.util;

import javax.faces.context.FacesContext;

public class FacesUtil {
	@SuppressWarnings("unchecked")
	public static <T> T getManagedBean(String name,Class<?> classe){
        FacesContext context = FacesContext.getCurrentInstance();
        return (T)context.getApplication().evaluateExpressionGet(context,"#{"+name+"}",classe);
    }
}
