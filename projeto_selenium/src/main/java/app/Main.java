package app;

import java.io.File;

import tool.SerializebleDeserializable;

public class Main {

	private static final String path_One = "D:\\emulator\\settings"; 
	private static final String path_Two = "C:\\emulator\\settings"; 
	
	
	static {
		existFileParameter();
	}

	public static void main(String[] args) {

		//		Emulator emulator = new Emulator();
		//		emulator.start("92109");
		//	

		Screen screen = new Screen();
		screen.start( );


	}



	private static void existFileParameter() {
		if(new File(path_One).exists()){
			loadSettings(path_One);
		}else if( new File(path_Two).exists()){
			loadSettings(path_Two);
		}else{
			if(createFolderSettings()){
				existFileParameter();
			}
		}


	}


	private static void loadSettings(String path) {
		
		SerializebleDeserializable obj = new SerializebleDeserializable();
		obj.deserializar(path);
		
	}



	private static boolean createFolderSettings() {

		if(new File(path_One).mkdirs() && new File(path_One.replace("settings", "file")).mkdirs() ){
			return true;
		}else if( new File(path_Two).mkdirs()  && new File(path_Two.replace("settings", "file")).mkdirs()  ){
			return true;
		}
		return false;
	}



	private static void carregar() {
		if(new File(path_One).exists()){

		}else if( new File(path_Two).exists()){

		}

	}


}
