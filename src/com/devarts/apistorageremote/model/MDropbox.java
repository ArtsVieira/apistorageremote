package com.devarts.apistorageremote.model;

import java.io.IOException;

import com.devarts.apistorageremote.dropbox.AuthDropbox;

public class MDropbox {
	
	public String AuthUrl() throws IOException { 
		AuthDropbox auth = new AuthDropbox();
		auth.autentica("dropbox.json","../testeapi/authdropbox.json","pkce");
		try {
			System.out.println("TESTE");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "string";
	}
	
	public void RevokeAuth() { 
		
		
	}
	
	public void AuthProcessing(String authcode) { 
		
	}
	
	public boolean UploadFile(String localfilepath,String remotefilepath) {
		return false;
	}
	
	public void DeleteFile(String remotefilepath) { 
	}
	
	public String[] FileInfo(String remotefilepath) {
		String[] result = {"name:arthur","size:1kb"};
		return result;
	}
	
	public Boolean DownloadFile(String remotefilepath,String localfilepath) { 
		
		return true;
	}
	
	public String[] FileList() { 
		String[] list = {"file1","file2"};
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
}
