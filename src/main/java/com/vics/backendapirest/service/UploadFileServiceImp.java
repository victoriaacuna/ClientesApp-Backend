package com.vics.backendapirest.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImp implements IUploadFileService{

	
	private final Logger logger = LoggerFactory.getLogger(UploadFileServiceImp.class);
//	private final String ABSOLUTE_PATH = "/Users/victoriaacuna/Desktop/Spring-Angular/uploads";
	private final String ABSOLUTE_PATH = "uploads";
	
	@Override
	public Resource uploadImage(String fileName) throws MalformedURLException {
		
		Path route = this.getPath(fileName);
		
		logger.info(route.toString());
		
		Resource image = null;
		
		image = new UrlResource(route.toUri());
		
		System.out.print(image);
		
		if(!image.exists() && !image.isReadable()) {
			logger.error("No se pudo cargar la foto "+fileName);
		} 
		
		return image;
	}

	@Override
	public String copyImage(MultipartFile imageFile) throws IOException {
		
		String fileName = UUID.randomUUID().toString() +"_"+ imageFile.getOriginalFilename();
		Path route = this.getPath(fileName);
		
		logger.info(route.toString());
		Files.copy(imageFile.getInputStream(), route);
		return fileName;
		
	}

	@Override
	public boolean deleteImage(String fileName) {
		
		if(fileName!=null && fileName.length()>0) {
			Path previousRoute = this.getPath(fileName);
			File previousFile = previousRoute.toFile();
			if(previousFile.exists() && previousFile.canRead()) {
				previousFile.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path getPath(String fileName) {
		
		return Paths.get(ABSOLUTE_PATH).resolve(fileName).toAbsolutePath();
	}

}
