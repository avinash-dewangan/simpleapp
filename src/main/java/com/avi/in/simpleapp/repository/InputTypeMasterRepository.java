package com.avi.in.simpleapp.repository;

import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import com.avi.in.simpleapp.entity.InputTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputTypeMasterRepository extends JpaRepository<InputTypeMaster, Long> {
}
