package com.zhangpan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelUtils {
	
    public static final short DEFAULT_FONT_SIZE = 15;
    
    /**
     * 导出Excel 2003(.xls)格式 ，大量数据
     * @param headMap 标题
     * @param list 数据行
     * @param out 输出流
     */
    public static void export03Excel(Map<String, Object> headMap,List<Map<String, Object>> list, OutputStream out) {
        Workbook workbook = new HSSFWorkbook();
        exportExcel(workbook, headMap, list, out);
    }
    
    /**
     * 导出Excel 2007(.xlsx)格式 ，大量数据
     * @param headMap 标题
     * @param list 数据行
     * @param out 输出流
     */
    public static void export07Excel(Map<String, Object> headMap,List<Map<String, Object>> list, OutputStream out) {
        Workbook workbook = new SXSSFWorkbook(1000);//缓存
        exportExcel(workbook, headMap, list, out);
    }
    
    /**
     * @param titleMap
     * @param list
     * @param pageSize
     * @param out 输出流
     */
    private static void exportExcel(Workbook workbook,Map<String, Object> titleMap,List<Map<String, Object>> list, OutputStream out) {
        try {
            Sheet sheet = workbook.createSheet();
            int rowIndex = 0;
            //标题行
            if(titleMap != null) {
                Row titleRow = sheet.createRow(rowIndex++);
                CellStyle headCellStyle=createStyle(workbook,true);
                setCell(titleRow, headCellStyle, titleMap);
            }
            //数据行
            for (Map<String, Object> map : list) {
                Row dataRow = sheet.createRow(rowIndex);
                CellStyle dataCellStyle=createStyle(workbook,false);
                setCell(dataRow, dataCellStyle, map);
                rowIndex++;
            }
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setCell(Row row,CellStyle cellStyle,Map<String,Object> map){
		int colIndex=0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
        	Cell cell=row.createCell(colIndex,CellType.STRING);
        	cell.setCellValue(value.toString());
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
    public static CellStyle createStyle(Workbook workBook, boolean isHeader) {
        CellStyle cellStyle = workBook.createCellStyle();
        
        Font font = workBook.createFont();
        if (isHeader) {
            font.setFontName("黑体");
            font.setFontHeightInPoints(DEFAULT_FONT_SIZE);
        }
        cellStyle.setFont(font); //字体
        cellStyle.setWrapText(false); //自动换行
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //水平
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直
        return cellStyle;
    }
	
	/**
	 * 读取excel
	 * @param filePath
	 * @throws Exception
	 */
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
        CellType type=cell.getCellTypeEnum();
        switch (type) {
        case NUMERIC:
            if(DateUtil.isCellDateFormatted(cell)){
                cellValue=cell.getDateCellValue();
            }else{
                cellValue=cell.getNumericCellValue();
            }
            break;
        case STRING:
            cellValue=cell.getStringCellValue();
            break;
        case BOOLEAN:
            cellValue=cell.getBooleanCellValue();
            break;
        case FORMULA:
            cellValue=cell.getCellFormula();
            break;
        default:cellValue="";
            break;
        }
        return cellValue;
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
	public static void main(String[] args) throws Exception {
        String createFilePath="f:\\create.xlsx";
        readExcel(createFilePath);
        
        Map<String,Object> headMap=new HashMap<String,Object>();
        headMap.put("name", "姓名");
        headMap.put("age", "年龄");
        headMap.put("sex", "性别");
        
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 1; i <= 1; i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("name", "张三"+i);
            map.put("num", i);
            map.put("price", 2.35d);
            map.put("date", new Date());
            map.put("date2", System.currentTimeMillis());
            map.put("flag", true);
            list.add(map);
        }
        FileOutputStream out = new FileOutputStream(createFilePath);
        long begin=System.currentTimeMillis();
        export07Excel(null, list, out);
        System.out.println("生成成功，共用时"+(System.currentTimeMillis()-begin)+"ms");
    }
}
