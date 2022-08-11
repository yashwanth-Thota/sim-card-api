package com.simcardapi.controller;

import com.simcardapi.dao.SimCardDAO;
import com.simcardapi.entity.SimCard;
import com.simcardapi.service.SimCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
public class CardController {

    @Autowired
    SimCardService simCardService;

    @Operation(summary = "status check for the server(ping route)")
    @GetMapping("/")
    public ResponseEntity getCards(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "Get all sim cards added")
    @GetMapping("/listall")
    public ResponseEntity<List<SimCard>> getAll(){
        return new ResponseEntity(simCardService.getAll(),HttpStatus.OK);
    }

    @Operation(summary = "Add a new sim cards",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required=true,
                    content={@Content(mediaType = "application/json",
                            schema=@Schema(implementation = SimCardDAO.class))})
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adds new sim card",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/add")
    public ResponseEntity<String> addSimCard(@RequestBody SimCard simcardDetails){
        ResponseEntity<String> response;
        try{
            simCardService.addNewCard(simcardDetails);
            response=new ResponseEntity(simcardDetails.getFullName()+" added!!",HttpStatus.OK);
        }catch (Exception e){
            response=new ResponseEntity("Error occurred : "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Operation(summary = "Update an existing sim cards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateSimCard(@PathVariable("id") String id, @RequestBody SimCard simCardDetails){
        ResponseEntity<String> response;
        try{
            simCardService.updateCard(id,simCardDetails);
            response=new ResponseEntity(id+" updated succesfully!!",HttpStatus.OK);
        }catch (Exception e){
            response=new ResponseEntity("Error occurred : "+e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Operation(summary = "delete an existing sim cards")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSimCard(@PathVariable("id") String id){
        ResponseEntity<String> response;
        try{
            simCardService.delete(id);
            response=new ResponseEntity(id+" deleted succesfully!!",HttpStatus.OK);
        }catch (Exception e){
            response=new ResponseEntity("Error occurred : "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Operation(summary = "Get all sim cards expiring in next 30 days")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) })
    })
    @GetMapping("/to-renew")
    public ResponseEntity<List<SimCard>> findAllToRenew(){
        return new ResponseEntity(simCardService.getAllToRenew(),HttpStatus.OK);
    }

    @Operation(summary = "Renew an particular simcard",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required=true,
                    content={@Content(mediaType = "application/json", schema=@Schema(description = "plan name",implementation = String.class))}))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/renew/{id}")
    public ResponseEntity<String> renew(@PathVariable("id") String id,@RequestBody String plan){
        ResponseEntity<String> response;
        try{
            String requestId=simCardService.renew(id,plan);
            response=new ResponseEntity("Request to recharge id:"+id+" with plan "+plan+" is submitted. Waiting for provider to process.["+requestId+"]!!",HttpStatus.OK);
        }catch (Exception e){
            response=new ResponseEntity("Error occurred : "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
