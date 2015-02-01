package br.ucb.projeto.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.ucb.projeto.model.DAOS.VideoDAO;
import br.ucb.projeto.model.beans.Video;

@Path("/videos")
public class VideoResource {
	private VideoDAO daoVideo;
	public VideoResource() {
		setDaoVideo(new VideoDAO());
	}
	
	public VideoDAO getDaoVideo() {
		return daoVideo;
	}


	public void setDaoVideo(VideoDAO daoVideo) {
		this.daoVideo = daoVideo;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Video> getAll(){
		return getDaoVideo().getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find")
	public Video find(@QueryParam("id")Integer id){
		return getDaoVideo().find(id);
	}
}
