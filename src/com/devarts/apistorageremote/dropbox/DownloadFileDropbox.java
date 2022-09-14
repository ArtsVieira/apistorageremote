package com.devarts.apistorageremote.dropbox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

public class DownloadFileDropbox {
	public void download(String remotefilepath,String localpath) {
		
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
	    DbxClientV2 dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost()); 	    
		try
	    {
	        //output file for download --> storage location on local system to download file
	        OutputStream downloadFile = new FileOutputStream(localpath);
	        try
	        {
	        FileMetadata metadata = dbxClient.files().downloadBuilder(remotefilepath)
	                .download(downloadFile);
	        }
	        finally
	        {
	            downloadFile.close();
	        }
	    }
	    //exception handled
	    catch (DbxException e)
	    {
	        //error downloading file
	        System.out.println("Unable to download file to local system\n Error: " + e);
	    }
	    catch (IOException e)
	    {
	        //error downloading file
	    	System.out.println("Unable to download file to local system\n Error: " + e);
	    }
		
	}
}
