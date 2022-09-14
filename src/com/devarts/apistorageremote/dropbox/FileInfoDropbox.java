package com.devarts.apistorageremote.dropbox;

import java.awt.HeadlessException;
import java.util.Locale;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxHost;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.Metadata;

public class FileInfoDropbox {



	public String[] fileinfo (String remotepath) {
	
	Metadata metadata = null;
	//System.out.println("entrou");	
    String argAuthFile = "authdropbox.json";
    //String localPath = "upload/teste2.txt";
   //String dropboxPath = "/draco/teste2.txt";

    // Read auth info file.
    DbxAuthInfo authInfo = null;
    try {
        authInfo = DbxAuthInfo.Reader.readFromFile(argAuthFile);
    } catch (JsonReader.FileLoadException ex) {
        System.err.println("Error loading <auth-file>: " + ex.getMessage());
        System.exit(1);
    }	
    // Create a DbxClientV2, which is what you use to make API calls.
    DbxRequestConfig requestConfig = new DbxRequestConfig("examples-upload-file");
	
	try{
	
	//DbxHost DEFAULT = new DbxHost("api.dropboxapi.com","content.dropboxapi.com","www.dropbox.com","notify.dropboxapi.com");
	
//		DbxClientV2 cli = new DbxClientV2(config, "<REDACTED>", DbxHost.DEFAULT);
    DbxClientV2 dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());    
		
	metadata = dbxClient.files().getMetadata(remotepath);
	
	//dbxClient.files().delete(remotepath);
	
	System.out.println(metadata.toString());
	
	}catch(DbxException | HeadlessException erro){
	
		System.out.println( erro);
	
	}
	return null;
	}
}