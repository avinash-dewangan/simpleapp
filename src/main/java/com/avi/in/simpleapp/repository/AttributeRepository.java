package com.avi.in.simpleapp.repository;

import com.avi.in.simpleapp.entity.Attribute;
import com.avi.in.simpleapp.entity.InputDataTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
