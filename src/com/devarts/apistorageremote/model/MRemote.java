package com.devarts.apistorageremote.model;

import java.awt.HeadlessException;
import java.io.IOException;

import com.devarts.apistorageremote.dropbox.AuthDropbox;
import com.devarts.apistorageremote.dropbox.DeleteDropbox;
import com.devarts.apistorageremote.dropbox.DownloadFileDropbox;
import com.devarts.apistorageremote.dropbox.FileInfoDropbox;
import com.devarts.apistorageremote.dropbox.ListFoldersDropbox;
import com.devarts.apistorageremote.dropbox.UploadDropbox;
import com.devarts.apistorageremote.gdrive.uploadBasic;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.DeleteErrorException;
import com.dropbox.core.v2.files.ListFolderErrorException;

public class MRemote { 
	String provider=null;
	
	public void setProvider(String str) {
		if ((str=="GDRIVE") || (str=="ONEDRIVE") || (str=="DROPBOX")) {
			provider=str;
		}
		else { 
			System.err.println("Provedor tem que ser: GDRIVE,ONEDRIVE ou DROPBOX");
		}
	}
	
	public String getProvider() {
		return provider;
	}
	

	public String AuthUrl() throws IOException{
		String result = null;
		MDropbox doc = new MDropbox();
		result = doc.AuthUrl();
		return result;
	}
	
	public void RevokeAuth() { 
		
		
	}
	
	public void AuthProcessing(String authcode) { 
		
	}
	
	public boolean UploadFile(String localfilepath,String remotefilepath) throws IOException {
		if (provider=="DROPBOX") { 
			UploadDropbox uploadfile = new UploadDropbox();
			uploadfile.upload(localfilepath,remotefilepath);
		}
		else {System.out.println("Não foi setado provedor");}
		return true;
	}
	
	public void DeleteFile(String remotefilepath) {
//		System.out.println("entrouremote");	
		if (provider=="DROPBOX") { 
			//System.out.println("entroudelete");				
			DeleteDropbox deletefile = new DeleteDropbox();
			deletefile.delete(remotefilepath);		
		}
	}
	
	public String[] FileInfo(String remotefilepath) {
		String[] result = {"name:arthur","size:1kb"};
		FileInfoDropbox fileinfo = new FileInfoDropbox();
		fileinfo.fileinfo(remotefilepath);
		return result;
	}
	
	public void DownloadFile(String remotefilepath,String localfilepath) { 
		DownloadFileDropbox downfile = new DownloadFileDropbox();
		downfile.download(remotefilepath,localfilepath);		
	}
	
	public String[] FileList() { 
		String[] list = {"file1","file2"};
		ListFoldersDropbox listfiles = new ListFoldersDropbox();
		listfiles.syncFiles();
		return list;
	}
	
/*	
	Obter URL de Autorização;
	Revogar Autorização;
	Processar Autorização;
	Autenticação valida;
	Enviar arquivo;
	Deletar arquivo;
	Obter informações do arquivo;
	Baixar arquivo;
	Listar arquivos.
*/

	
	public String getToken() {
		String result = null;
		uploadBasic upload = new uploadBasic();
		try {
			result =  upload.uploadBasic();					
		}
		catch (Exception e) {
			// TODO: handle exception
			result = e.getMessage();
		}
		return result;
	}
	
	public void dropbox() throws IOException { 
		AuthDropbox auth = new AuthDropbox();
		auth.autentica("dropbox.json","../testeapi/authdropbox.json","pkce");
		try {
			System.out.println("TESTE");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		UploadDropbox up = new UploadDropbox();
		up.upload("upload/teste2.txt","/draco/teste2.txt");
	}
	
}