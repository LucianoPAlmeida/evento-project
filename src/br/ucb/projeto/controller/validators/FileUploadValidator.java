package br.ucb.projeto.controller.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("fileValidator")
public class FileUploadValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		if(value != null && value instanceof Part){
			Part file = (Part)value;
			if(file.getSize() == 0)return;
			if(file.getSize() > 1024*1024){
				throw new ValidatorException(new FacesMessage("Tamanho máximo para imagem é de 1MB"));
			}
			else if (!file.getContentType().equalsIgnoreCase("image/png") && !file.getContentType().equalsIgnoreCase("image/jpeg")){
				throw new ValidatorException(new FacesMessage("São permitidas apenas imagens no formato png e jpeg"));
			}
		}
		
	}
	
}
