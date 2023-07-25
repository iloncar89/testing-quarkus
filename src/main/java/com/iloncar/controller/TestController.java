package com.iloncar.controller;

import com.iloncar.exception.ResourceNotFoundException;
import com.iloncar.domain.Person;
import com.iloncar.model.PersonModel;
import com.iloncar.payload.CalculateFibonacciResponse;
import com.iloncar.payload.GreetingRequest;
import com.iloncar.payload.GreetingResponse;
import com.iloncar.payload.PersonRequest;
import com.iloncar.payload.PersonResponse;
import com.iloncar.service.api.TestService;
import com.iloncar.utils.AppConstants;
import com.iloncar.utils.Mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/v1/test")
public class TestController {
  TestService testService;
  Mapper mapper;

  public TestController(TestService testService, Mapper mapper) {
    this.testService = testService;
    this.mapper = mapper;
  }

  @GET
  @Path("/case1")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello() {
    return Response.ok(new GreetingResponse(AppConstants.HELLO)).status(200).build();
  }

  @POST
  @Path("/case2")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response greeting(GreetingRequest request){
    return Response.ok(new GreetingResponse(AppConstants.HELLO + " " + request.getName())).status(200).build();
  }

  @GET
  @Path("/case3/{number}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response calculateFibonacci(@PathParam("number") long number){
    try{
      return Response.ok(new CalculateFibonacciResponse(testService.calculateFibonacci(number))).status(200).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @POST
  @Path("/case4")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createGetDeletePersonTestCase(PersonRequest personRequest) {
    try {
      PersonModel personModel = mapper.mapPersonRequestToPersonModel(personRequest);
      personModel = testService.createGetDeletePersonTestCase(personModel);
      PersonResponse personResponse = mapper.mapPersonModelToPersonResponse(personModel);
      return Response.ok(personResponse).status(200).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }

  @POST
  @Path("/case5")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createGetDeletePersonORMTestCase(PersonRequest personRequest) throws ResourceNotFoundException {
    try {
      Person person = mapper.mapPersonRequestToPerson(personRequest);
      person = testService.createGetDeletePersonORMTestCase(person);
      PersonResponse personResponse = mapper.mapPersonToPersonResponse(person);
      return Response.ok(personResponse).status(200).build();
    } catch (Exception e) {
      return Response.serverError().build();
    }
  }
}
