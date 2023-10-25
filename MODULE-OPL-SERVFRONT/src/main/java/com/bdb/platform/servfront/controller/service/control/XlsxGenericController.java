package com.bdb.platform.servfront.controller.service.control;

import com.bdb.opaloshare.persistence.model.jsonschema.XLSX.XLSXSheetModel;
import com.bdb.opaloshare.util.CustomMediaType;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2020 Banco de Bogotá. All Rights Reserved.
 * <p>
 * OPALOBDB was developed by Team Deceval BDB
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * proprietary and confidential. For use this code you need to contact to
 * Banco de Bogotá and request exclusive use permission.
 * <p>
 * This file was write by Jose Buelvas <jbuelva@bancodebogota.com.co>.
 */
@RestController
@CommonsLog
@RequestMapping("file/excel")
@CrossOrigin(value = "*", maxAge = 0)
public class XlsxGenericController {

    @Value("${spring.application.logo}")
    private String logoPath;

    /**
     * Generate XLSX File (Creates a table report).
     * Get Dynamic data and return Excel File in xlsx format.
     *
     * @param response Excel file response
     * @throws IOException
     */
    @PostMapping(value = "generation")
    @ResponseBody
    public void gen(HttpServletResponse response, @Valid @RequestBody XLSXSheetModel data) throws IOException, GeneralSecurityException, InvalidFormatException {

        // Document title
        String title = data
                .getTitle()
                .toLowerCase()
                .replace(" ", "-")
                .replace(".", "-");
        title = title + "." + new SimpleDateFormat("yyyyMMdd").format(new Date());

        log.info("Making XLSX File with name " + title + ".xlsx");

        // Password
        POIFSFileSystem fileSystem = new POIFSFileSystem();
        EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
        Encryptor encryptor = info.getEncryptor();
        encryptor.confirmPassword(data.getPassword());

        // Create book
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create sheet 1
        XSSFSheet sheet = workbook.createSheet(title.split("\\-")[0]);

        // Set header style
        setHeaderStyle(sheet, data.getHeaders().size() + 2);

        // Set logo
        setBdBLogo(sheet);

        // Set header title
        setMergedTitle(sheet, data.getTitle().toUpperCase(), data.getHeaders().size());

        // Set data
        setSheetData(sheet, data.getHeaders(), data.getCells());

        // Set footer
        setFooterData(sheet, data.getAuthor().toUpperCase(), data.getCells().size());

        // Make file
        log.info("Downloading file...");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%1$s.xlsx", title));
        response.setHeader(HttpHeaders.CONTENT_TYPE, CustomMediaType.VND_MS_EXCEL_VALUE);

        OutputStream outputStream = encryptor.getDataStream(fileSystem);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        OutputStream os = response.getOutputStream();
        fileSystem.writeFilesystem(os);
        os.close();
        fileSystem.close();
    }

    private void setFooterData(XSSFSheet sheet, String author, int dataLength) {
        int xStartIn = dataLength + 6;

        // Create footer title style
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(new XSSFColor(new Color(15, 68, 162)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setColor(new XSSFColor(new Color(255, 255, 255)));
        font.setBold(true);
        style.setFont(font);

        // Merge cells
        sheet.addMergedRegion(new CellRangeAddress(xStartIn, xStartIn, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(xStartIn, xStartIn, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(xStartIn + 1, xStartIn + 1, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(xStartIn + 1, xStartIn + 1, 4, 6));

        // Author row
        XSSFRow authorRow = sheet.createRow(xStartIn);
        XSSFCell authorCell1 = authorRow.createCell(2);
        XSSFCell authorCell2 = authorRow.createCell(4);
        authorCell1.setCellValue("USUARIO GENERADOR");
        authorCell1.setCellStyle(style);
        authorCell2.setCellValue(author);

        // Date row
        XSSFRow dateRow = sheet.createRow(xStartIn + 1);
        XSSFCell dateCell1 = dateRow.createCell(2);
        XSSFCell dateCell2 = dateRow.createCell(4);
        dateCell1.setCellValue("FECHA DE GENERACIÓN");
        dateCell1.setCellStyle(style);
        dateCell2.setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
    }

    /**
     * Set sheet data.
     *
     * @param sheet Currrent sheet
     * @param data  Data to add
     */
    private void setSheetData(XSSFSheet sheet, List<String> titles, List<List<String>> data) {
        log.info("Rows to add = " + data.size());
        log.info("Adding data to worksheet...");

        // Starting headers
        XSSFRow rowHeaders = sheet.createRow(3);

        // Create headers by column
        for (int columnIndex = 0; columnIndex < titles.size(); columnIndex++) {
            XSSFCell cellTitle = rowHeaders.createCell(columnIndex + 2);

            // Create title style
            XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setFillForegroundColor(new XSSFColor(new Color(15, 68, 162)));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFFont font = sheet.getWorkbook().createFont();
            font.setColor(new XSSFColor(new Color(255, 255, 255)));
            font.setBold(true);
            style.setFont(font);

            // Set title text
            cellTitle.setCellValue(titles.get(columnIndex).toUpperCase());
            cellTitle.setCellStyle(style);
        }

        // Starting rows for data
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            XSSFRow row = sheet.createRow(rowIndex + 4);

            // Starting columns
            for (int columnIndex = 0; columnIndex < data.get(rowIndex).size(); columnIndex++) {
                XSSFCell cell = row.createCell(columnIndex + 2);
                cell.setCellValue(data.get(rowIndex).get(columnIndex));

                sheet.autoSizeColumn(columnIndex + 2, false);
            }
        }
    }

    /**
     * Merged title.
     *
     * @param sheet
     * @param title
     */
    private void setMergedTitle(XSSFSheet sheet, String title, int columnLength) {

        // Minimum length
        if (columnLength < 8) {
            columnLength = 9;
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, columnLength - 1));

        // Get cell
        XSSFCell cell = sheet.getRow(0).getCell(4);

        // Text Title Style
        XSSFCellStyle style = cell.getCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setColor(new XSSFColor(new Color(255, 255, 255)));
        font.setBold(true);
        font.setFontHeight((short) (12 * 20));
        style.setFont(font);

        cell.setCellValue(title);
        cell.setCellStyle(style);
    }

    /**
     * Header style.
     *
     * @param sheet
     */
    private void setHeaderStyle(XSSFSheet sheet, int columnLength) {
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new Color(15, 68, 162)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Minimum length
        if (columnLength < 8) {
            columnLength = 9;
        }

        int y = 2, x = columnLength - 1;

        // Cells range to apply style
        for (int i = 0; i < y; i++) {
            XSSFRow row = sheet.createRow(i);

            for (int j = 0; j < x; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * Set Banco de Bogotá Logotype
     *
     * @param sheet Worksheet
     * @throws IOException If element has any problem
     */
    private void setBdBLogo(XSSFSheet sheet) throws IOException {
        Resource resource = new ClassPathResource(logoPath);
        InputStream stream = resource.getInputStream();
        CreationHelper helper = sheet.getWorkbook().getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

        int index = sheet.getWorkbook().addPicture(IOUtils.toByteArray(stream), Workbook.PICTURE_TYPE_PNG);

        anchor.setCol1(0);
        anchor.setRow1(0);
        anchor.setRow2(2);
        anchor.setCol2(4);

        Picture picture = drawing.createPicture(anchor, index);
        picture.resize(1, 0.95);
    }
}
