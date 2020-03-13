package com.marcosribeiro.resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/upload")
public class UploadResource {

	private static String UPLOADED_FOLDER = "/home/marcos/workspace/ionic_angular/cursomc-frontend/src/assets/clients_images";

	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public String uploadFile(@RequestBody String value, @PathVariable String id) throws IOException {
			byte[] imageByte = Base64.getDecoder().decode(value);
			String path = UPLOADED_FOLDER + "/client_" + id + ".png";
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(imageByte);
			outputStream.flush();
			outputStream.close();
			System.out.println("Passou!");
			return "Success";
	}

}
