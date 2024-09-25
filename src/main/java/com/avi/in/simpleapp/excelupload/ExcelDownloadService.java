package com.avi.in.simpleapp.excelupload;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelDownloadService {

    @Autowired
    private UserUploadExcelDataRepository userUploadExcelDataRepository;


    // Download Excel
    public ByteArrayInputStream downloadUserDataAsExcel() throws IOException {
        List<UserUploadDataExcel> users = userUploadExcelDataRepository.findAll();

        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Name", "Email", "Phone"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Populate the sheet with data
        int rowNum = 1;
        for (UserUploadDataExcel user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
            row.createCell(3).setCellValue(user.getPhone());
        }

        // Resize columns to fit the content
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook data to an InputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Download Excel with Dynamic field
    public ByteArrayInputStream exportUsersToExcel() throws IOException {
        // Fetch data from the database
        List<UserUploadDataExcel> users = userUploadExcelDataRepository.findAll();

        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Dynamically create header row using reflection
        Row headerRow = sheet.createRow(0);
        Field[] fields = UserUploadDataExcel.class.getDeclaredFields();

        int colIndex = 0;
        for (Field field : fields) {
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(field.getName()); // Set field name as the header
        }

        // Populate the sheet with user data
        int rowNum = 1;
        for (UserUploadDataExcel user : users) {
            Row row = sheet.createRow(rowNum++);
            colIndex = 0;
            for (Field field : fields) {
                field.setAccessible(true); // Access private fields
                try {
                    Object value = field.get(user); // Get the value of the field for the current user
                    if (value != null) {
                        row.createCell(colIndex++).setCellValue(value.toString()); // Write the value into the cell
                    } else {
                        row.createCell(colIndex++).setCellValue(""); // If null, leave it empty
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Resize columns to fit the content
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook data to an InputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Download Excel with Custom Field
    public ByteArrayInputStream exportCustomUserDataToExcel(String name) throws IOException {
        // Fetch data using a custom query
        List<Object[]> customData = userUploadExcelDataRepository.findCustomUserData(name);

        // If no data is present, return an empty file
        if (customData.isEmpty()) {
            return new ByteArrayInputStream(new byte[0]);
        }

        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("CustomUserData");

        // Dynamically create header row based on the first Object[] array (assuming uniform structure)
        Row headerRow = sheet.createRow(0);

        // Assuming the first result defines the structure, dynamically get the number of columns
        Object[] firstRow = customData.get(0);
        int columnCount = firstRow.length;

        // Dynamically set header names (assuming the order from your query result corresponds to field names)
        String[] dynamicHeaders = {"ID", "Name", "Email", "Phone"};  // Adjust this if needed
        for (int i = 0; i < columnCount; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(dynamicHeaders[i]);
        }

        // Populate the sheet with custom data from the query
        int rowNum = 1;
        for (Object[] rowData : customData) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                if (rowData[i] != null) {
                    row.createCell(i).setCellValue(rowData[i].toString());
                } else {
                    row.createCell(i).setCellValue("");
                }
            }
        }

        // Resize columns to fit content
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook data to an InputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Download Excel with first Validate
    public List<String> uploadAndValidateExcel(MultipartFile file) throws IOException {
        List<String> errors = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Read and validate data row by row
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1, skipping headers
            Row row = sheet.getRow(i);
            if (row == null) continue;

            // Validate the data in each cell (assuming ID, Name, Email)
            Cell idCell = row.getCell(0);
            Cell nameCell = row.getCell(1);
            Cell emailCell = row.getCell(2);


            // Validation: Check if ID is numeric and non-null
            if (idCell == null || idCell.getCellType() != CellType.NUMERIC) {
                errors.add("Row " + (i + 1) + "ColumnIndex" +idCell.getColumnIndex() +  ": Invalid or missing ID");
            }

            // Validation: Check if name is non-empty
            if (nameCell == null || nameCell.getCellType() != CellType.STRING || nameCell.getStringCellValue().isEmpty()) {
                errors.add("Row " + (i + 1) + "ColumnIndex" +nameCell.getColumnIndex() + ": Missing or invalid Name");
            }

            // Validation: Check if email is a valid string (this can be extended for email format)
            if (emailCell == null || emailCell.getCellType() != CellType.STRING || !isValidEmail(emailCell.getStringCellValue())) {
                errors.add("Row " + (i + 1) + "ColumnIndex" +emailCell.getColumnIndex() +": Invalid Email format");
            }
        }

        workbook.close();
        return errors;
    }


    // Simple email format validation
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    // Optionally, you can return the file with error highlights
    public ByteArrayInputStream markExcelErrors(MultipartFile file, List<String> errors) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (String error : errors) {
            // Parse the row and column from the error string and highlight the cell
            String[] parts = error.split(":");
            int rowIndex = Integer.parseInt(parts[0].replace("Row ", "").trim()) - 1;
            Row row = sheet.getRow(rowIndex);

            // For simplicity, mark the entire row red if there's any error
            if (row != null) {
                CellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                for (Cell cell : row) {
                    if(cell.getColumnIndex()==rowIndex)
                    cell.setCellStyle(style); // Highlight cell
                }
            }
        }

        // Write the modified workbook to ByteArrayOutputStream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }


    // parseExcel check numeric value and convert big data if converted by excel scientific number
    private List<UserUploadDataExcel> parseExcelFile(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<UserUploadDataExcel> users = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                UserUploadDataExcel user = new UserUploadDataExcel();
                user.setName(currentRow.getCell(0).getStringCellValue());
                user.setEmail(currentRow.getCell(1).getStringCellValue());

                // Handle phone number (convert numeric to string and prevent scientific notation)
                Cell phoneCell = currentRow.getCell(2);
                if (phoneCell.getCellType() == CellType.NUMERIC) {
                    // Use BigDecimal to avoid scientific notation
                    BigDecimal phoneNumber = new BigDecimal(phoneCell.getNumericCellValue());
                    user.setPhone(phoneNumber.toPlainString());
                } else if (phoneCell.getCellType() == CellType.STRING) {
                    user.setPhone(phoneCell.getStringCellValue());
                }

                users.add(user);
            }

            workbook.close();
            return users;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }


    // save the excel data
    public void saveExcelData(MultipartFile file) {
        try {
            List<UserUploadDataExcel> users = parseExcelFile(file.getInputStream());
            userUploadExcelDataRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

}
