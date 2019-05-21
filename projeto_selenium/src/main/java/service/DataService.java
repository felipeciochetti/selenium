package service;

import java.util.ArrayList;
import java.util.List;

import model.Data;
import xls.GerarPlanilha;
import xls.GerarPlanilhaData;

public class DataService {
	
	String data_search;
	List<Data> list_Data = new ArrayList<Data>();
	
	public DataService(String data_search) {
		this.data_search =  data_search;
	}
	
	public void add_Data(String adrees , String owner_one,String pagina,String sequencia){
		
		Data data = new Data();
		data.setAdrees(adrees);
		data.setOwner_one(owner_one);
		data.setPagina(pagina);
		data.setSequencia(sequencia);
		
		
		list_Data.add(data);
		
	}

	public List<Data> getList_Data() {
		return list_Data;
	}

	public void gerarPlanilha() {
		GerarPlanilhaData gerar = new GerarPlanilhaData();
		gerar.gerarPlanilha(list_Data);
		
		
	}
	

}
