package pe.edu.upeu.consolidado.consolidado.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import pe.edu.upeu.consolidado.consolidado.entity.Detalle_PPP;
import pe.edu.upeu.consolidado.consolidado.service.DetallePPPService;

@RestController
@RequestMapping("/api/detalleppp")
@CrossOrigin(origins = "http://localhost:4200")
public class DetallePPPController {
	@Autowired
	private DetallePPPService detallePPPService;
	
	@GetMapping
	public ResponseEntity<List<Detalle_PPP>> readAll() {
		try {
			List<Detalle_PPP> detalle_PPPs = detallePPPService.readAll();
			if (detalle_PPPs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(detalle_PPPs, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<Detalle_PPP> createCategoria(@Valid @RequestBody Detalle_PPP det) {
		try {
			Detalle_PPP d = detallePPPService.create(det);
			return new ResponseEntity<>(d,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Detalle_PPP> read(@PathVariable("id") Long id) {
		try {
			Detalle_PPP d = detallePPPService.read(id).get();
			return new ResponseEntity<>(d,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Detalle_PPP> delete(@PathVariable("id") Long id) {
		try {
			detallePPPService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Detalle_PPP> update(@PathVariable("id") Long id, @Valid @RequestBody Detalle_PPP det) {
		//TODO: process PUT request
		Optional<Detalle_PPP> d = detallePPPService.read(id);
		if (d.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(detallePPPService.update(det),HttpStatus.OK);
		}
	}

}
