package com.vics.backendapirest.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vics.backendapirest.entity.Client;
import com.vics.backendapirest.entity.Region;
import com.vics.backendapirest.service.IClientService;
import com.vics.backendapirest.service.IUploadFileService;

// Le da permiso a localhost:4200 a que solicite datos.
// Se pudiesen especificar los métodos permitidos (get, post, etc). En este caso, al no especificarlo, todos se permiten.
// También se pueden especificar las restricciones de los headers.
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	private final Logger logger = LoggerFactory.getLogger(ClientRestController.class);
	
	@GetMapping("/clients")
	public ResponseEntity<?> getAllClients(){
		Map<String, Object> response = new HashMap<>();
		try {
			return new ResponseEntity<List<Client>>(this.clientService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/clients/page/{page}")
	public ResponseEntity<?> getAllClients(@PathVariable Integer page){
		Map<String, Object> response = new HashMap<>();
		try {
			return new ResponseEntity<Page<Client>>(this.clientService.findAll(PageRequest.of(page, 4)), HttpStatus.OK);
		} catch (Exception e) {
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/client/{id}")
	public ResponseEntity<?> getClient(@PathVariable Long id) {
		
		Client clientFound = null;
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			clientFound = this.clientService.findById(id);
		} catch (Exception e) {
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(clientFound!=null) {
			return new ResponseEntity<Client>(clientFound, HttpStatus.OK);
		} else {
			response.put("Mensaje", "No existe ningún cliente con ese ID.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/clients")
	@ResponseStatus(HttpStatus.CREATED)
	// El BindingResult sabrá si hay algún error en la validación.
	public ResponseEntity<?> postClient(@Valid @RequestBody Client c, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		Client newClient = null;
		
		// Valida los campos.
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> 
						"El campo '" + err.getField() + "' " + err.getDefaultMessage()
					).collect(Collectors.toList());
			
			response.put("Mensaje", "No es válido. Revise todos los campos.");
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newClient = this.clientService.save(c);
			return new ResponseEntity<Client>(newClient, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			response.put("Mensaje", "Error al crear el cliente. Valide los campos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/client/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	// Es importante que el result vaya antes que el path variable
	public ResponseEntity<?> updateClient(@Valid @RequestBody Client c, BindingResult result, @PathVariable Long id) {
		Client client = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> 
						"El campo '" + err.getField() + "' " + err.getDefaultMessage()
					).collect(Collectors.toList());
			
			response.put("Mensaje", "No es válido. Revise todos los campos.");
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		
		
		try {
			client = this.clientService.findById(id);
		} catch (Exception e) {
			response.put("Mensaje", "El ID del cliente no existe.");
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(client != null) {
			
			client.setEmail(c.getEmail());
			client.setLast_name(c.getLast_name());
			client.setName(c.getName());
			client.setRegion(c.getRegion());
			client.setCreate_at(c.getCreate_at());
			
			try {
				return new ResponseEntity<Client>(this.clientService.save(client), HttpStatus.OK);
			} catch (Exception e) {
				response.put("Mensaje", "Error al actualizar el cliente. Valide los campos.");
				response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} else {
			response.put("Mensaje", "No existe ningún cliente con ese ID.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			// Elimina la foto del cliente.
			Client c = this.clientService.findById(id);
			String previousFileName = c.getImage();
			this.uploadService.deleteImage(previousFileName);
//			if(previousFileName!=null && previousFileName.length()>0) {
//				Path previousRoute = Paths.get("/Users/victoriaacuna/Desktop/Spring-Angular/uploads").resolve(previousFileName).toAbsolutePath();
//				File previousFile = previousRoute.toFile();
//				if(previousFile.exists() && previousFile.canRead()) {
//					previousFile.delete();
//				}
//			}
			
			
			this.clientService.delete(id);
		} catch (Exception e) {
			response.put("Mensaje", "Error al intentar localizar el cliente en la Base de Datos.");
			response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje", "Se eliminó el cliente con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
		
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/client/upload")
	public ResponseEntity<?> uploadImgae(@RequestParam("imageFile") MultipartFile imageFile, 
			@RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Client c = this.clientService.findById(id);
		
		if(!imageFile.isEmpty()) {
			
			String fileName = null;
			
			try { 
				
				fileName = this.uploadService.copyImage(imageFile);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				response.put("Mensaje", "Error en la subida de la imagen.");
				response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			System.out.print(fileName);
			
			String previousFileName = c.getImage();
			
			this.uploadService.deleteImage(previousFileName);
			
			c.setImage(fileName);
			this.clientService.save(c);
			response.put("client", c);
			response.put("Mensaje", "Has subido correctamente la imagen: " + fileName);
			
		}	
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);	
	}
	
//	@PostMapping("/client/upload")
//	public ResponseEntity<?> uploadImgae(@RequestParam("imageFile") MultipartFile imageFile, 
//			@RequestParam("id") Long id){
//		
//		Map<String, Object> response = new HashMap<>();
//		Client c = this.clientService.findById(id);
//		
//		if(!imageFile.isEmpty()) {
//			
//			String fileName = UUID.randomUUID().toString() +"_"+ imageFile.getOriginalFilename();
//			Path route = Paths.get("/Users/victoriaacuna/Desktop/Spring-Angular/uploads").resolve(fileName).toAbsolutePath();
//			
//			logger.info(route.toString());
//			
//			try {
//				
//				Files.copy(imageFile.getInputStream(), route);
//				
//				// Elimina la foto anterior.
//				String previousFileName = c.getImage();
//				if(previousFileName!=null && previousFileName.length()>0) {
//					Path previousRoute = Paths.get("/Users/victoriaacuna/Desktop/Spring-Angular/uploads").resolve(previousFileName).toAbsolutePath();
//					File previousFile = previousRoute.toFile();
//					if(previousFile.exists() && previousFile.canRead()) {
//						previousFile.delete();
//					}
//				}
//				
//				// Setea la imagen del cliente la nueva.
//				c.setImage(fileName);
//				this.clientService.save(c);
//				response.put("client", c);
//				response.put("Mensaje", "Has subido correctamente la imagen: " + fileName);
//				
//			} catch (IOException e) {
//				response.put("Mensaje", "Error en la subida de la imagen.");
//				e.printStackTrace();
//				response.put("Error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//		} else {
//			response.put("Mensaje", "El archivo está vacío.");
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
//	}
	
	// El :.+ indica que va a tener una extensión (JPG, PNG, etc).
	@GetMapping("/client/upload/img/{imageName:.+}")
	public ResponseEntity<Resource> seeImage(@PathVariable String imageName){
		
		Resource image = null;
		try {
			image = this.uploadService.uploadImage(imageName);
		} catch (Exception e) {
			
		}
		
		// Obliga a descargar la imagen.
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		image.getFilename() + "\"");
		return new ResponseEntity<Resource>(image, headers, HttpStatus.OK);
		
		
	}
	
//	// El :.+ indica que va a tener una extensión (JPG, PNG, etc).
//	@GetMapping("/client/upload/img/{imageName:.+}")
//	public ResponseEntity<Resource> seeImage(@PathVariable String imageName){
//		
//		Path route = Paths.get("/Users/victoriaacuna/Desktop/Spring-Angular/uploads").resolve(imageName).toAbsolutePath();
//		
//		logger.info(route.toString());
//		
//		Resource image = null;
//		
//		try {
//			
//			image = new UrlResource(route.toUri());
//			
//			if(image.exists() && image.isReadable()) {
//				
//				// Obliga a descargar la imagen.
//				HttpHeaders headers = new HttpHeaders();
//				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
//				image.getFilename() + "\"");
//				
//				return new ResponseEntity<Resource>(image, headers, HttpStatus.OK);
//				
//			} else {
//				
//				throw new RuntimeException("No se pudo cargar la foto "+imageName);
//			}
//			
//		} catch (MalformedURLException e) {
//			
//			e.printStackTrace();
//			return new ResponseEntity<Resource>(image, HttpStatus.BAD_REQUEST);
//		}
//		
//		
//	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("clients/regions")
	public List<Region> regionList(){
		return this.clientService.findAllRegions();
	}
	
	
}
