package xls;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class GerarPlanilha {
	
	
	String where ;
	List<Serializable> parametros;
	
	public Workbook workbook;
	public Sheet sheet ;
	public CellStyle titleStyle;
	public CellStyle normalStyle;
	public CellStyle doubleStyle;
	public CellStyle doubleBoldStyle;
	public CellStyle doubleBoldRedStyle;
	public CellStyle totalDoubleStyle;
	public CellStyle totalNormalStyle;
	public CellStyle normalBoldStyle;
	public CellStyle normalBoldRedStyle;
	public Font BoldFont;
	public Font BoldRedFont;
	public int rowNumber = 2;
	public int collumNumber = 0;
	
	 
	 
	 public abstract String gerarRelatorio();
	 
	public  void createStyles(){

		BoldFont = workbook.createFont();
		BoldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		
		BoldRedFont = workbook.createFont();
		BoldRedFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		BoldRedFont.setColor(Font.COLOR_RED);

		titleStyle = workbook.createCellStyle();
		titleStyle.setFont(BoldFont);
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);
		titleStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
		titleStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
		titleStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);
		titleStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setWrapText(true);

		normalStyle = workbook.createCellStyle();
		normalStyle.setBorderRight(CellStyle.BORDER_THIN);
		normalStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		normalStyle.setBorderBottom(CellStyle.BORDER_THIN);
		normalStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		normalStyle.setBorderLeft(CellStyle.BORDER_THIN);
		normalStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		normalStyle.setBorderTop(CellStyle.BORDER_THIN);
		normalStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

		normalBoldStyle = workbook.createCellStyle();
		normalBoldStyle.cloneStyleFrom(normalStyle);
		normalBoldStyle.setFont(BoldFont);

		normalBoldRedStyle = workbook.createCellStyle();
		normalBoldRedStyle.cloneStyleFrom(normalBoldStyle);
		normalBoldRedStyle.setFont(BoldRedFont);

		doubleStyle = workbook.createCellStyle();
		doubleStyle.cloneStyleFrom(normalStyle);
		doubleStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

		doubleBoldStyle = workbook.createCellStyle();
		doubleBoldStyle.cloneStyleFrom(doubleStyle);
		doubleBoldStyle.setFont(BoldFont);

		doubleBoldRedStyle = workbook.createCellStyle();
		doubleBoldRedStyle.cloneStyleFrom(doubleBoldStyle);
		doubleBoldRedStyle.setFont(BoldRedFont);

		totalNormalStyle = workbook.createCellStyle();
		totalNormalStyle.cloneStyleFrom(titleStyle);
		totalNormalStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		totalNormalStyle.setAlignment(CellStyle.ALIGN_LEFT);

		totalDoubleStyle = workbook.createCellStyle();
		totalDoubleStyle.cloneStyleFrom(titleStyle);
		totalDoubleStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		totalDoubleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		totalDoubleStyle.setAlignment(CellStyle.ALIGN_RIGHT);
	}


}
