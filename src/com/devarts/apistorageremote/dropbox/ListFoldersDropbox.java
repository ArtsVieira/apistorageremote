package com.devarts.apistorageremote.dropbox;

import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserListFolderBuilder;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class ListFoldersDropbox {
	public String syncFiles()  {
	    String argAuthFile = "authdropbox.json";
		DbxAuthInfo authInfo = null;
	    try {
	        authInfo = DbxAuthInfo.Reader.readFromFile(argAuthFile);
	    } catch (JsonReader.FileLoadException ex) {
	        System.err.println("Error loading <auth-file>: " + ex.getMessage());
	        System.exit(1);
	       // return;
	    }	
	    // Create a DbxClientV2, which is what you use to make API calls.
	    DbxRequestConfig requestConfig = new DbxRequestConfig("examples-upload-file");
		
		try{
		
		//DbxHost DEFAULT = new DbxHost("api.dropboxapi.com","content.dropboxapi.com","www.dropbox.com","notify.dropboxapi.com");
		
//		DbxClientV2 cli = new DbxClientV2(config, "<REDACTED>", DbxHost.DEFAULT);
	    DbxClientV2 dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());  
		DbxUserListFolderBuilder listFolderBuilder = dbxClient.files().listFolderBuilder("");
		ListFolderResult result = listFolderBuilder.withRecursive(true).start();
			
		Logger log = Logger.getLogger("thread");
		log.setLevel(Level.INFO);

		while (true) {

			if (result != null) {
				for ( Metadata entry : result.getEntries()) {
					if (entry instanceof FileMetadata){
						log.info("Added file: "+entry.getPathLower());
					}
				}

				if (!result.getHasMore()) {
					log.info("GET LATEST CURSOR");
					return result.getCursor();
				}

				try {
					result = dbxClient.files().listFolderContinue(result.getCursor());
				} catch (DbxException e) {
					log.info ("Couldn't get listFolderContinue");
				}
			}
		}

	}catch(DbxException erro){
		
		System.out.println( erro);
	
	}
		return argAuthFile;	
}
}
