package com.help.server.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.help.server.configurations.FileUploadConfiguration;
import com.help.server.util.FileUploadHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author houlongbin
 *
 */
@Service
public class FileUploadService {
	public  static class FileModel {
		public String getFileName() {
			return fileName;
		}

		@JsonProperty("file_name")
		public String fileName;
		public FileModel(String fileName) {
			this.fileName = fileName;
		}
	}
	@Autowired
	private FileUploadConfiguration fileUploaderConfiguration;

	private Logger log = Logger.getLogger(FileUploadService.class);

	public FileModel handleFileUpload( MultipartFile file) throws Exception {

		String name = file.getOriginalFilename();
		log.info("try load image " + name);

		String newPhotoName = FileUploadHelper.getUniqueName(name);
		log.info("generate new unique file name " + newPhotoName);

		if (file.isEmpty()) {
			log.error("file " + name + " is empty");
			throw new IllegalArgumentException("Uploaded file is empty");
		}

		if (!FileUploadHelper.isImageContentType(file.getContentType())) {
			log.error("No supported content type " + file.getContentType());
			MediaType mediaType = MediaType.parseMediaType(file.getContentType());
			throw new HttpMediaTypeNotSupportedException(mediaType, FileUploadHelper.getImageMediaTypes());
		}

		String path = fileUploaderConfiguration.getPathToUploadFolder() + newPhotoName;
		log.info("path to upload file - " + path);
		try {
			byte[] bytes = file.getBytes();

			BufferedOutputStream stream =
					new BufferedOutputStream(
							new FileOutputStream(new File(path)
							)
					);

			stream.write(bytes);
			stream.close();

			log.info("file successfully save by path - " + path);

			return new FileModel(newPhotoName);
		} catch (Exception e) {
			log.debug("error save file by path " + path, e);
			throw new Exception("No file was uploaded");
		}
	}

}
