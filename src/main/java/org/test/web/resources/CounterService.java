package org.test.web.resources;

import org.test.web.domain.Counter;
import org.test.web.domain.Counters;

import javax.ws.rs.*;

@Path("counters")
public interface CounterService {

  @GET
  @Produces("application/json")
  @Path("/{name}")
  Counter get(@PathParam("name") String name);

  @GET
  @Produces("application/json")
  @Path("/")
  Counters getAll();

  @DELETE
  @Path("{name}")
  void delete(@PathParam("name") String name);

  @PUT
  @Path("{name}")
  void increment(@PathParam("name") String name);

}
