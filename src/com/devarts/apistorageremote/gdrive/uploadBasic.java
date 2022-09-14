package com.devarts.apistorageremote.gdrive;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.*;
//import com.google.api.client.googleapis.extensions.appengine.auth.oauth2
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sqladmin.SQLAdminScopes;

// ...


/* Class to demonstrate use of Drive insert file API */
public class uploadBasic {

  /**
   * Upload new file.
   *
   * @return Inserted file metadata if successful, {@code null} otherwise.
   * @throws IOException if service account credentials file not found.
   */
  public static String uploadBasic() throws IOException {
    // Load pre-authorized user credentials from the environment.
    // TODO(developer) - See https://developers.google.com/identity for
    // guides on implementing OAuth2 for your application.
	  String jsoncred = "client_secret_520353913104-lml25kog9ltd2b6ld6e6ph6ag68svada.apps.googleusercontent.com.json";
	  
/*
	  GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(jsoncred))
			    .createScoped(Collections.singleton(SQLAdminScopes.SQLSERVICE_ADMIN));
*/
	  System.out.println("entrou");
	  GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(jsoncred));
	  System.out.println("entrou2");
  	  credential.refreshToken();
	    try {
		  String token = credential.getAccessToken();	  
	        return token;
	      } catch (Exception e) {
	        // TODO(developer) - handle error appropriately
	        System.err.println("Unable to upload file: " + e.getMessage());
	        throw e;
	      }
  }
} 
	    

	  
/*
	  // Build a new authorized API client service.
    Drive service = new Drive.Builder(new NetHttpTransport(),
        GsonFactory.getDefaultInstance(),
        credential)
        .setApplicationName("Drive samples")
        .build();
    // Upload file photo.jpg on drive.
    File fileMetadata = new File();
    fileMetadata.setName("photo.jpg");
    // File's content.
    java.io.File filePath = new java.io.File("files/photo.jpg");
    // Specify media type and file-path for file.
    FileContent mediaContent = new FileContent("image/jpeg", filePath);
    try {
      File file = service.files().create(fileMetadata, mediaContent)
          .setFields("id")
          .execute();
      System.out.println("File ID: " + file.getId());
      return file.getId();
    } catch (GoogleJsonResponseException e) {
      // TODO(developer) - handle error appropriately
      System.err.println("Unable to upload file: " + e.getDetails());
      throw e;
    }
  }

}
*/
