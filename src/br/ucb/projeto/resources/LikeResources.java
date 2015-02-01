package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.LikeDAO;
import br.ucb.projeto.model.beans.Like;

@Path("/likes")
public class LikeResources {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/evento")
	public List<Like> getEventLikes(@QueryParam("id")String id){
		try{
			return new LikeDAO().likesByEvento(Integer.parseInt(id));
		}catch(NumberFormatException e){
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user")
	public List<Like> getUserLikes(@QueryParam("id")String id){
		try{
			return new LikeDAO().likesByUser(Integer.parseInt(id));
		}catch(NumberFormatException e){
			return null;
		}
	}
	@POST
	@Path("/giveLike")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void giveLike(@FormParam("idEvento")Integer idEvento,@FormParam("idUser")Integer idUser){
		System.out.println("giveLike [idEvento = "+idEvento+",idUser = "+idUser+"]");
		Like like = new Like();
		like.setIdEvento(idEvento);
		like.setIdUser(idUser);
		new LikeDAO().add(like);
	}
	@GET
	@Path("/quantidade/user")
	@Produces(MediaType.TEXT_PLAIN)
	public String quantidadeLikesUser(@QueryParam("userId")Integer id){
		System.out.println(id);
		List<Like> list = new LikeDAO().likesByUser(id);
		if(list == null){
			return "0";
		}
		return String.format("%d",list.size());
	}
	@GET
	@Path("/quantidade/evento")
	@Produces(MediaType.TEXT_PLAIN)
	public String quantidadeLikesEvento(@QueryParam("eventoId")Integer id){
		System.out.println(id);
		List<Like> list = new LikeDAO().likesByEvento(id);
		if(list == null){
			return "0";
		}
		return String.format("%d",list.size());
	}
	@POST
	@Path("/deleteLike")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void deleteLike(@FormParam("idEvento")Integer idEvento,@FormParam("idUser")Integer idUser){
		System.out.println("deleteLike [idEvento = "+idEvento+",idUser = "+idUser+"]");
		new LikeDAO().delete(idUser, idEvento);
	}
	
}
