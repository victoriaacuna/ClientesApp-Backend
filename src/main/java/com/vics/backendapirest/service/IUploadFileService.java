package com.vics.backendapirest.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource uploadImage(String fileName) throws MalformedURLException;
	
	public String copyImage(MultipartFile file) throws IOException;
	
	public boolean deleteImage(String fileName);
	
	public Path getPath(String fileName);
}
