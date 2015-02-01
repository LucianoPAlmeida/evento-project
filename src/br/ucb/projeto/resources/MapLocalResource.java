package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.MapPointDAO;
import br.ucb.projeto.model.beans.MapPoint;

@Path("/locais")
public class MapLocalResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MapPoint> getAll(){
		return new MapPointDAO().getAll();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public MapPoint find(@QueryParam("id")String id){
		return new MapPointDAO().find(Integer.parseInt(id));
	}
}
