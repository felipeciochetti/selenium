package tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Woodstox;

public class XLSUtils {
	private static XLSUtils instance = new XLSUtils();

	private DataFormatter stringDataFormatter;

	private XLSUtils() {
	}

	public static XLSUtils getInstance() {
		return instance;
	}

	public int getIndexCol(String letra) {

		int indexCol = 0;
		for (int i = 0; i < letra.length(); i++) {
			indexCol += (letra.charAt(i) - 64) * Math.pow(26, letra.length() - 1 - i);
		}
		indexCol--;
		return indexCol;

	}

	private DataFormatter getStringDataFormatter() {
		if (stringDataFormatter == null) {
			stringDataFormatter = new DataFormatter();
			stringDataFormatter.setDefaultNumberFormat(new DecimalFormat("#################"));
		}
		return stringDataFormatter;
	}

	public void setValorCell(Row row, String letraColuna, Object valor) {
		setValorCell(row, letraColuna, valor, null);
	}


	public void setValorCell(Row row, String letraColuna, Object valor,CellStyle cellStyle) {
		setValorCell(row, getIndexCol(letraColuna), valor, cellStyle);
	}
	public void setValorCell(Row row, int  letraColuna, Object valor,CellStyle cellStyle) {
		try {
			Cell cell = row.createCell(letraColuna);
			if(cellStyle != null){
				cell.setCellStyle(cellStyle);
			}else{
				
			}
			if (valor == null) {
				valor = new String("");
			} else {
				if (valor instanceof Date) {
					cell.setCellValue(((Date)valor));
				} else if (valor instanceof BigDecimal) {
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(((BigDecimal) valor).doubleValue());
				} else if (valor instanceof Integer){
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(new Double((Integer)valor));
				}else{
					cell.setCellValue(valor.toString());
				}
			}
			// cell.setCellValue(valor.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCellFormula(Row row, String letraColuna, String formula,CellStyle cellStyle) {
		try {
			Cell cell = row.createCell(getIndexCol(letraColuna));
			if(cellStyle != null){
				cell.setCellStyle(cellStyle);
			}
			
			if(formula != null && formula.length() > 0){
				cell.setCellFormula(formula);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String getStringCell(Row row, String letraColuna, StringBuffer msgErro, boolean brancoSeNull) {
		String cont = null;
		try {
			cont = row.getCell(getIndexCol(letraColuna)).getStringCellValue();
		} catch (Exception e) {
			try {
				cont = getStringDataFormatter().formatCellValue(row.getCell(getIndexCol(letraColuna)));
			} catch (Exception ex) {
			}
		}
		if (msgErro != null && (cont == null || cont.trim().length() == 0)) {
			msgErro.append("Linha " + (row.getRowNum() + 1) + " Coluna " + letraColuna + " n�o informado\r\n");
		}
		if (brancoSeNull && cont == null) {
			cont = "";
		}
		return cont;
	}

	public Date getDataCell(Row row, String letraColuna, StringBuffer msgErro) {
		Date data = null;
		try {
			data = row.getCell(getIndexCol(letraColuna)).getDateCellValue();
		} catch (Exception e) {
		}
		if (msgErro != null && (data == null)) {
			msgErro.append("Linha " + (row.getRowNum() + 1) + " Coluna " + letraColuna + " Data n�o informada ou inv�lida\r\n");
		}
		return data;
	}

	public BigDecimal getValorCell(Row row, String letraColuna, StringBuffer msgErro) {
		BigDecimal valor = null;
		try {
			valor = new BigDecimal(row.getCell(getIndexCol(letraColuna)).getNumericCellValue());
			if (valor != null) {
				valor = valor.setScale(4, RoundingMode.HALF_UP);
			}
		} catch (Exception e) {
		}
		if (msgErro != null && (valor == null)) {
			msgErro.append("Linha " + (row.getRowNum() + 1) + " Coluna " + letraColuna + " Valor n�o informado ou inv�lido\r\n");
		}
		return valor;
	}
	
	public void resizeColumns(Sheet sheet,String letraInicio,String letraFim){
		
		for(int i = getIndexCol(letraInicio) ;i <= getIndexCol(letraFim);i++ ){
			sheet.autoSizeColumn(i);
		}
		
	}
	
	public void addPicture(Workbook workbook,Sheet sheet,byte[] logoTipo,int col1,int col2,int row1,int row2){
		
		try{

			if(logoTipo == null){
				return;
			}
			
			int pictureureIdx = workbook.addPicture(logoTipo, Workbook.PICTURE_TYPE_PNG);

			CreationHelper helper = workbook.getCreationHelper();

			Drawing drawing = sheet.createDrawingPatriarch();

			ClientAnchor anchor = helper.createClientAnchor();

			anchor.setCol1(col1);
			anchor.setCol2(col2);
			anchor.setRow1(row1);
			anchor.setRow2(row2);

			Picture picture = drawing.createPicture(anchor, pictureureIdx);
			picture.resize();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
