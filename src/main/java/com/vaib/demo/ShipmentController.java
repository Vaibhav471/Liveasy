package com.vaib.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vaib.demo.bean.Shipment;

@RestController
public class ShipmentController {
	
	
	
	@Autowired
	ShipmentRepository srepo;

	@GetMapping("/")
	public String index() {
		
		return "Hello this is Index Page";
		
	}
	
	
	@PostMapping("load")
	public String save(@RequestBody Shipment ship){
		String message="";
		try {
			srepo.save(ship);
			message="loads details added successfully";
		}
		catch(Exception e){
			message=e.getMessage();
		}
		
		
		
		return message;
	}
	
	
	@GetMapping("load/{shipperId}")
	public List<Shipment> getLoad(@PathVariable String shipperId){
		
			return srepo.findAllByShipperId(shipperId);
			
	}
	
	@GetMapping("load/{id}")
	public Shipment getShipmentByLoadId(@PathVariable int id) {
		return srepo.findById(id).get();
	}
	
	@PutMapping("load/{Id}")
    public ResponseEntity<Shipment> updateLoad(@PathVariable(value = "id") Integer id, @RequestBody Shipment loadDetails) {
        Shipment updatedLoad = loadDetails;
        Shipment previousLoad = srepo.findById(id).get();
        
        previousLoad.setComment(updatedLoad.getComment());
        previousLoad.setLoadingPoint(updatedLoad.getLoadingPoint());
        previousLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        previousLoad.setProductType(updatedLoad.getProductType());
        previousLoad.setShipmentDate(updatedLoad.getShipmentDate());
        previousLoad.setShipperId(updatedLoad.getShipperId());
        previousLoad.setTruckType(updatedLoad.getTruckType());
        previousLoad.setUnloadingPoint(updatedLoad.getUnloadingPoint());
        previousLoad.setWeight(updatedLoad.getWeight());

        
        return ResponseEntity.ok(updatedLoad);
    }
	
	
	@DeleteMapping("load/{id}")
    public ResponseEntity<Void> deleteLoad(@PathVariable(value = "id") Integer loadId) {

		Shipment load = srepo.findById(loadId).get();
        srepo.delete(load);
		
		return ResponseEntity.noContent().build();
    }
	
	
	
	
}
