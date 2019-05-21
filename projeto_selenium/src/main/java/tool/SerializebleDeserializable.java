package tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.piccolo.xml.EntityManager;

import model.Parameters;

public class SerializebleDeserializable {
	
	public SerializebleDeserializable() {

	}
	
	public void serializar(){
		
		
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(Parameters.getInstance().getPath_settings() +"settings.ser");
	
		
		/*
		 * A Classe ObjectOutputStream escreve os objetos no FileOutputStream
		 * */
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		
		/*
		 * Veja aqui a m�gica ocorrendo: Estamos gravando um objeto 
		 * do tipo Address no arquivo address.ser. Aten��o: O nosso 
		 * objeto Address que est� sendo gravado, j� � gravado de forma 
		 * serializada
		 * */
		oos.writeObject(Parameters.getInstance());
		
		oos.close();
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	
	public Parameters deserializar(String nomeArquivo){
			
		
		
			File f = new File(nomeArquivo + File.separator + "settings.ser") ;
			if(!f.exists()){
				Parameters p = new Parameters();
				p.setChrome(nomeArquivo +  File.separator +  "chromedriver.exe");
				p.setPath_settings(nomeArquivo);
				p.setPath_xls(nomeArquivo.replace("settings", "file"));
				p.setLogin("pehomebuyers@gmail.com");
				p.setPassword("Pehb1602!");
				 Parameters.setInstance(p);
				  return p;
			}
			
			
			/*
		    * Respons�vel por carregar o arquivo address.ser
		    * */
		   FileInputStream fin;
		try {
			fin = new FileInputStream(nomeArquivo + File.separator + "settings.ser");
	
		   
		   /*
		    * Respons�vel por ler o objeto referente ao arquivo
		    * */
		   ObjectInputStream ois = new ObjectInputStream(fin);
		   
		   /*
		    * Aqui a m�gica � feita, onde os bytes presentes no arquivo address.ser
		    * s�o convertidos em uma inst�ncia de Address.
		    * */
		   Parameters questao = (Parameters) ois.readObject();
		   ois.close();

		   Parameters.setInstance(questao);
		   
		   return questao;
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		   
	}
	

}
