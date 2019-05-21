package xls;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Data;
import model.Parameters;
import tool.XLSUtils;

public class GerarPlanilhaData  extends GerarPlanilha{







	public String gerarPlanilha(List<Data> list_Data){
		String nomeFile = System.currentTimeMillis() + "";
		try {
			
			Parameters.getInstance().setLog("starting building file.....");
			
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Inspenção");

			createStyles();

			montarCabecalho();

			for (Data data : list_Data) {



				montarCorpo(++rowNumber, data);


			}



			XLSUtils.getInstance().resizeColumns(sheet, "A", "D");

			emitirExcel(workbook, nomeFile);



		} catch (Exception e) {
			e.printStackTrace();
			Parameters.getInstance().setLog("error building file....." + e.getMessage());
			
		}


		return nomeFile;
	}

	private void montarCabecalho(){

		int columnNum = 0;

		Row rowTitulo_ONe = sheet.createRow(1);
	
		//XLSUtils.getInstance().setValorCell(rowTitulo_ONe, columnNum++, "Data Search - " + data_search   ,normalStyle);


		columnNum = 0;
		Row rowTitulo = sheet.createRow(2);
		setCellCabacalho(rowTitulo, columnNum++, "Adress");
		setCellCabacalho(rowTitulo, columnNum++, "Owner");
		setCellCabacalho(rowTitulo, columnNum++, "Page");
		setCellCabacalho(rowTitulo, columnNum++, "Sequence");

		rowNumber = 3;
	}
	
	
	private void montarCorpo(int intRow , Data data){
		int coluna = 0;
		Row row = sheet.createRow(intRow);

		XLSUtils.getInstance().setValorCell(row, coluna++, data.getAdrees(), normalStyle);
		XLSUtils.getInstance().setValorCell(row, coluna++, data.getOwner_one(), normalStyle);
		XLSUtils.getInstance().setValorCell(row, coluna++,data.getPagina(), normalStyle);
		XLSUtils.getInstance().setValorCell(row, coluna++, data.getSequencia(), normalStyle);
	}

	private void setCellCabacalho(Row rowTitulo,int columnNum,String value){
		Cell cell = rowTitulo.createCell(columnNum);
		cell.setCellValue(value);
		cell.setCellStyle(titleStyle);
	}

	@Override
	public String gerarRelatorio() {
		// TODO Auto-generated method stub
		return null;
	}

	public String emitirExcel(Workbook workbook, String nomeFile) throws Exception{

		limparDiretorio();
		String path =  Parameters.getInstance().getPath_xls();


		File f = new File( path + File.separator);
		if ( ! f.exists() )
			f.mkdirs();

		if ( workbook instanceof HSSFWorkbook ){
			nomeFile +=   ".xls";
		}else if ( workbook instanceof XSSFWorkbook ){
			nomeFile +=   ".xlsx";
		}else if ( workbook instanceof SXSSFWorkbook ){
			nomeFile +=   ".xlsx";
		}

		File caminho = new File(f.getPath() + File.separator + nomeFile);
		FileOutputStream output = new FileOutputStream(caminho);
		workbook.write( output );
		output.close();
		
		Parameters.getInstance().setLog("finish building worksheet > " + nomeFile );
		
		return nomeFile;
	}
	private void limparDiretorio(){
//		File f = new File(ContextUtils.getInstance().getPathFilesAttacheds());
//
//		if(f.isDirectory()){
//			File[] files =  f.listFiles();
//			for (File file : files) {
//				file.delete();
//			}
//		}

	}


}
