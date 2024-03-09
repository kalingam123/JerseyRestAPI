package com.kali.DemoRest;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("aliens")
public class AlienResource {
	
	AlienRepository alienRepository = new AlienRepository();
	MysqlRepository mysqlRepository=new MysqlRepository();
	
	@GET
	@Path("repo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alien> getAliens() {
		System.out.println("calling getAlien()...JSON..Repo.!");
		//return alienRepository.getAliens();
		return mysqlRepository.getAliens();
	}
	
	@GET
	//@Path("alien/102")
	@Path("alien/{id}")  //{id}---> Place Holder
	@Produces(MediaType.APPLICATION_JSON)
	public Alien getAlien(@PathParam("id") int id) {
		//return alienRepository.getAlien(id);
		return mysqlRepository.getAlien(id);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alien> getAlienss() {
		System.out.println("calling getAlien()...JSON..");
		Alien a1=new Alien();
		a1.setId(101);
		a1.setName("Ram");
		a1.setPoints(50);
		
		Alien a2=new Alien();
		a2.setId(102);
		a2.setName("kalingam");
		a2.setPoints(50);
		
		List<Alien> aliens=Arrays.asList(a1,a2);
		
		return aliens;
	}
	@POST
	@Path("alien")
	// its user send any formate XML or JSON
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a) {
		System.out.println(a);
		//alienRepository.create(a);
		mysqlRepository.create(a);
		return a;
	}
	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien updateAlien(Alien a) {
		System.out.println(a);
		//alienRepository.create(a);
		if(mysqlRepository.getAlien(a.getId()).getId()==0) {
			mysqlRepository.create(a);
		}else {
			mysqlRepository.update(a);
		}
		return a;
	}
	
	@DELETE
	@Path("alien/{id}")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Alien deleteAlien(@PathParam("id") int id) {
		Alien a=mysqlRepository.getAlien(id);
		if(a.getId()!=0)
			mysqlRepository.delete(id);
		
		
		return a;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GET
	@Path("alienn")
	@Produces(MediaType.APPLICATION_XML)
	public Alien getAlien2() {
		System.out.println("Calling Alien()..XML...");
		Alien a = new Alien();
		a.setName("Vinu");
		a.setPoints(25);
		return a;
	}

}
