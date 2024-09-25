package com.avi.in.simpleapp.excelupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private ExcelDownloadService excelDownloadService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload an Excel file!");
        }

        excelDownloadService.saveExcelData(file);
        return ResponseEntity.ok("File uploaded and data saved successfully.");
    }
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadExcelFile() throws IOException {
        ByteArrayInputStream excelData = excelDownloadService.downloadUserDataAsExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(excelData));
    }
    @GetMapping("/download_df")
    public ResponseEntity<InputStreamResource> downloadExcelFileDynamicField() throws IOException {
        ByteArrayInputStream excelData = excelDownloadService.downloadUserDataAsExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(excelData));
    }
    // Upload Excel file and validate
    @PostMapping("/uploadV")
    public ResponseEntity<?> uploadAndValidateExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<String> errors = excelDownloadService.uploadAndValidateExcel(file);

        if (errors.isEmpty()) {
            return ResponseEntity.ok("File uploaded successfully without errors");
        } else {
            // Return the error list or send back the file with highlighted errors
            ByteArrayInputStream errorFile = excelDownloadService.markExcelErrors(file, errors);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=error_report.xlsx");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(errorFile));
        }
    }

}