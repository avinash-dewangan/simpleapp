package com.avi.in.simpleapp.repository;

import com.avi.in.simpleapp.entity.AttributeCategoryMaster;
import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputDataTypeMasterRepository extends JpaRepository<InputDataTypeMaster, Long> {
}
