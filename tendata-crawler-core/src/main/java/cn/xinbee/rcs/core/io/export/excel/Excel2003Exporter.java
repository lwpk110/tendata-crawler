package cn.xinbee.rcs.core.io.export.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import cn.xinbee.rcs.core.io.BeanFieldResolver;
import cn.xinbee.rcs.core.io.FieldDescriptor;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xinbee.rcs.core.io.export.FileExporter;

public class Excel2003Exporter implements FileExporter {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(Excel2003Exporter.class);

	private final BeanFieldResolver beanFieldResolver;
	
	public Excel2003Exporter(BeanFieldResolver beanFieldResolver) {
		this.beanFieldResolver = beanFieldResolver;
	}
	
	@SuppressWarnings("resource")
    @Override
	public void export(OutputStream stream, Collection<?> dataCol, List<FieldDescriptor> fieldDescriptors) throws IOException{
	    LOGGER.debug("begin excel export...");
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell[] titleCells = new HSSFCell[fieldDescriptors.size()];
		CellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFillBackgroundColor(HSSFColor.YELLOW.index2);
		HSSFFont font = wb.createFont(); 
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
		font.setFontHeightInPoints((short)12); 
		titleStyle.setFont(font);
		for (int i = 0; i < fieldDescriptors.size(); i++) {
			titleCells[i] = titleRow.createCell(i);
			titleCells[i].setCellValue(new HSSFRichTextString(fieldDescriptors.get(i).getCaption()));
			titleCells[i].setCellStyle(titleStyle);
		}
		int j = 0;
		CellStyle dateFormatCellStyle = wb.createCellStyle();
		dateFormatCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		for (Object obj : dataCol) {
			HSSFRow row = sheet.createRow(j+1);
			for (int k = 0; k < fieldDescriptors.size(); k++) {
				HSSFCell cell = row.createCell(k);
				Object value = beanFieldResolver.resolveValue(obj, fieldDescriptors.get(k).getField());
				if(value instanceof String){  
                    cell.setCellValue(truncate(value.toString(), SpreadsheetVersion.EXCEL97.getMaxTextLength()));  
                }else if(value instanceof String[]){
                	cell.setCellValue(truncate(StringUtils.join((String[])value, "\n"), SpreadsheetVersion.EXCEL97.getMaxTextLength()));
                }else if(value instanceof  Double){  
                    cell.setCellValue((Double)value);  
                }else if(value instanceof Integer){  
                    cell.setCellValue((Integer)value);  
                }else if(value instanceof Float){  
                    cell.setCellValue((Float)value);  
                }else if(value instanceof Boolean){  
                    cell.setCellValue((Boolean)value);  
                }else if(value instanceof java.util.Date|value instanceof java.sql.Date){  
                    cell.setCellValue((Date)value);  
                    cell.setCellStyle(dateFormatCellStyle);
                } 
			}
			j++;
		}
		wb.write(stream);
	}
	
	@Override
	public void export(File file, Collection<?> dataCol, List<FieldDescriptor> fieldDescriptors) throws IOException{
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			export(outputStream, dataCol, fieldDescriptors);
		} 
		finally{
			if(outputStream != null) {
				outputStream.close();
			}
		}
	}
	
	private String truncate(String str, int maxLength){
        if(str.length() > maxLength){
            return StringUtils.substring(str, 0, maxLength -1);
        }
        return str;
    }
}
