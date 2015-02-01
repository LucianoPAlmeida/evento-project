package br.ucb.projeto.controller.managedBeans;

import java.io.File;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.ucb.projeto.model.DAOS.UsuarioDAO;
import br.ucb.projeto.model.beans.Usuario;
import br.ucb.projeto.model.factory.ManagerFactory;
import br.ucb.projeto.model.persistense.ImagePersistence;
@SessionScoped
@ManagedBean(name = "loginBean")
public class LoginManageBean {
	private String email;
	private String senha;
	public LoginManageBean(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(File.separator);
		new File(realPath+File.separatorChar+"images").mkdir();
		realPath = realPath+"images"+File.separatorChar;
		System.out.println(realPath);
		ImagePersistence fp = ImagePersistence.getInstance();
		ManagerFactory.getInstance();
		fp.setServerPath(realPath);
		fp.setTmpFileName(request.getRemoteAddr().replace('.','-').replace(":","-"));
		UsuarioDAO dao = new UsuarioDAO();
		if(!dao.emailExiste("admin@mail.com")){
			dao.add(new Usuario("admin", "admin@mail.com","admins", new GregorianCalendar(), true));
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String logar(){
		UsuarioDAO daoUser = new UsuarioDAO();
		Usuario user = daoUser.autenticarUsuario(getEmail(), getSenha());
		String ret = null;
		clear();
		if(user == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário ou senha incorretos!"));
		}else{
			HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.getSession().setAttribute("usuario", user);
			ret = "principal";
		}
		clear();
		return ret;
	}
	
	public String sair(){
		HttpServletRequest request =(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("usuario", null);
		request.getSession().invalidate();
		clear();
		return "login";
	}
	public void clear(){
		setEmail("");
		setSenha("");
	}
	
}
