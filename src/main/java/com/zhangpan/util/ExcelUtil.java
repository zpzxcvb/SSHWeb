package com.zhangpan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.GroupLayout.Alignment;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ExcelUtil {
	
	public static String NO_DEFINE = "no_define";//未定义的字段
    public static String DEFAULT_DATE_PATTERN="yyyy-MM-dd";//默认日期格式
    public static int DEFAULT_COLOUMN_WIDTH = 17;

	public static void main(String[] args) throws Exception {
		String filePath="f:\\test.xlsx";
		String createFilePath="f:\\create.xlsx";
//		readExcel(filePath);
		
		/*FileOutputStream out1 = new FileOutputStream(createFilePath);
		List<Object[]> list1=new ArrayList<Object[]>();
		for (int i = 1; i < 12; i++) {
			Object[] o=new Object[]{"张三"+i,"0000000"+i,"abc"+i};
			list1.add(o);
		}
		export03Excel(new String[]{"姓名","年龄","性别"},list1,createFilePath,5,out1);*/
		
		Map<String,String> headMap=new HashMap<String,String>();
		headMap.put("name", "姓名");
		headMap.put("age", "年龄");
		headMap.put("sex", "性别");
		
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for (int i = 1; i <= 200000; i++) {
			Map<String,String> map=new HashMap<String,String>();
			map.put("name", "张三"+i);
			map.put("age", "0000"+i);
			map.put("sex", "M");
			list.add(map);
		}
		FileOutputStream out = new FileOutputStream(filePath);
		long begin=System.currentTimeMillis();
		export07Excel(headMap, list, 50000, out);
		System.out.println("生成成功，共用时"+(System.currentTimeMillis()-begin)+"ms");
	}
	
	/**
	 * @param title	表头	new String[]{"姓名","年龄","性别"}
	 * @param list	按表头顺序添加的记录集合	["张三","20","男"]
	 * @param filePath	生成文件路径
	 * @param pageSize	每页显示记录大小
	 * @throws Exception
	 */
	public static void export03Excel(String[] title,List<Object[]> list,String filePath,int pageSize, OutputStream out) throws Exception{
		Workbook workBook=new HSSFWorkbook();
		exportExcel(workBook, title, list, filePath, pageSize, out);
	}
	public static void export07Excel(String[] title,List<Object[]> list,String filePath,int pageSize, OutputStream out) throws Exception{
		Workbook workBook=new SXSSFWorkbook(1000);//缓存
		exportExcel(workBook, title, list, filePath, pageSize, out);
	}
	private static void exportExcel(Workbook workBook,String[] title,List<Object[]> list,String filePath,int pageSize, OutputStream out) throws Exception{
		Sheet sheet=null;
		int rowIndex=0;
		CellStyle headCellStyle=createStyle(workBook,true);
		CellStyle dataCellStyle=createStyle(workBook,false);
		for (int i = 0; i < list.size(); i++) {
			if(rowIndex==pageSize||rowIndex==0){
				sheet = workBook.createSheet();
				Row row=sheet.createRow(0);
				setCell(workBook, row, headCellStyle,title);
				rowIndex=1;
			}
			
			Row dataRow=sheet.createRow(rowIndex);
			Object[] o=list.get(i);
			setCell(workBook, dataRow, dataCellStyle,o);
			rowIndex++;
		}
		workBook.write(out);
		out.close();
		System.out.println("文件生成完成");
	}
	public static void setCell(Workbook workBook,Row row,CellStyle cellStyle,Object[] o){
		for (int j = 0; j < o.length; j++) {
			Cell cell=row.createCell(j,CellType.STRING);
			cell.setCellValue(o[j].toString());
			cell.setCellStyle(cellStyle);
		}
	}
	public static void setCell(Workbook workBook,Row row,CellStyle cellStyle,Map<String,String> map){
		int colIndex=0;
		
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	Cell cell=row.createCell(colIndex,CellType.STRING);
			cell.setCellValue(entry.getValue());
			cell.setCellStyle(cellStyle);
			colIndex++;
        }
	}
	/**
	 * 单元格格式，表头加粗
	 * @param workBook
	 * @param isHeader	是否是表头
	 * @return
	 */
	public static CellStyle createStyle(Workbook workBook,boolean isHeader){
	  Font font = workBook.createFont();
	  if(isHeader){
		  font.setFontName("黑体");
		  font.setFontHeightInPoints((short)12);
		  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	  }
	  
	  CellStyle cellStyle =workBook.createCellStyle();  
      cellStyle.setFont(font);
      cellStyle.setWrapText(false);
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
      cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      cellStyle.setBorderTop(BorderStyle.THIN);
      cellStyle.setBorderBottom(BorderStyle.THIN);
      cellStyle.setBorderLeft(BorderStyle.THIN);
      cellStyle.setBorderRight(BorderStyle.THIN);
      
      return cellStyle;
	}
	
	public static void readExcel(String filePath) throws Exception{
		File file=new File(filePath);
		Workbook workBook=WorkbookFactory.create(file);
		int sheetNum=workBook.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet sheet=workBook.getSheetAt(i);
			int rowNum=sheet.getPhysicalNumberOfRows();
			for (int j = 0; j < rowNum; j++) {
				Row row=sheet.getRow(j);
				int cellNum=row.getLastCellNum();
				for (int k = 0; k < cellNum; k++) {
					Cell cell=row.getCell(k);
					System.out.println(getCellValueByType(cell));
				}
			}
		}
	}
	
	private static Object getCellValueByType(Cell cell){
		Object cellValue="";
		int type=cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)){
				cellValue=cell.getDateCellValue();
			}else{
				cellValue=cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue=cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue=cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue=cell.getCellFormula();
			break;
		default:cellValue="";
			break;
		}
		return cellValue;
	}
	
	/**
	 * 导出Excel 97(.xls)格式 ，少量数据
	 * @param headMap
	 * @param list
	 * @param pageSize
	 * @param out 输出流
	 */
	private static void exportExcel(Workbook workbook,Map<String, String> headMap,List<Map<String, String>> list,int pageSize, OutputStream out) {
        Sheet sheet =null;
        int rowIndex = 0;
        CellStyle headCellStyle=createStyle(workbook,true);
		CellStyle dataCellStyle=createStyle(workbook,false);
        for (Map<String, String> map : list) {
        	if(rowIndex == pageSize || rowIndex == 0){
                sheet = workbook.createSheet();
                Row headerRow = sheet.createRow(0);
                setCell(workbook, headerRow, headCellStyle, headMap);
                rowIndex = 1;
            }
        	Row dataRow = sheet.createRow(rowIndex);
        	setCell(workbook, dataRow, dataCellStyle, map);
        	rowIndex++;
        }
        try {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 导出Excel 2007(.xls)格式 ，大量数据
	 * @param headMap
	 * @param list
	 * @param pageSize
	 * @param out 输出流
	 */
	public static void export03Excel(Map<String, String> headMap,List<Map<String, String>> list,int pageSize, OutputStream out) {
		Workbook workbook = new HSSFWorkbook();
		exportExcel(workbook, headMap, list, pageSize, out);
    }
	
	public static void export07Excel(Map<String, String> headMap,List<Map<String, String>> list,int pageSize, OutputStream out) {
        Workbook workbook = new SXSSFWorkbook(1000);//缓存
        exportExcel(workbook, headMap, list, pageSize, out);
    }
	
	//Web 导出excel
   /* public static void downloadExcelFile(String fileName,Map<String, String> headMap,List<Map<String, String>> list,int pageSize,HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            export07Excel(headMap, list, pageSize, outputStream);
            
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
