package com.Process;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.NoMatchFoundException.NoMatchFoundException;
import com.Utils.VillageUtils;
import com.classes.Resident;
import java.sql.SQLException;
import java.util.List;

@Path("/Services")
public class VillageService {

    @GET
    @Path("/displayResidents")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayResidents() throws SQLException, NoMatchFoundException {
        List<Resident> list = VillageUtils.displayResidents();

        return Response.status(Response.Status.OK).entity(list).build();
    }

    @GET
    @Path("/searchByName/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@PathParam("param") String name) throws SQLException, NoMatchFoundException {
        List<Resident> list = VillageUtils.searchByName(name);

        return Response.status(Response.Status.OK).entity(list).build();
    }

    @GET
    @Path("/searchByPartial/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByPartialName(@PathParam("param") String name) throws SQLException, NoMatchFoundException {
        List<Resident> list = VillageUtils.searchByName(name);

        return Response.status(Response.Status.OK).entity(list).build();
    }

}
