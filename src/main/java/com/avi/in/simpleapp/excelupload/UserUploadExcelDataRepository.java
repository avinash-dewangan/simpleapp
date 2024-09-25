package com.avi.in.simpleapp.excelupload;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserUploadExcelDataRepository extends JpaRepository<UserUploadDataExcel, Long> {

    // Example custom query that fetches only specific fields or complex data
    @Query("SELECT u.id, u.name, u.email FROM UserUploadDataExcel u WHERE u.name LIKE %:name%")
    List<Object[]> findCustomUserData(String name);
}
