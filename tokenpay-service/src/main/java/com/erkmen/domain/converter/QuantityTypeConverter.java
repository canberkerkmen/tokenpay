package com.erkmen.domain.converter;

import com.erkmen.domain.enums.QuantityType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class QuantityTypeConverter implements AttributeConverter<QuantityType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(QuantityType quantityType) {
        return quantityType != null ? quantityType.getCode() : null;
    }

    @Override
    public QuantityType convertToEntityAttribute(Integer code) {
        return code != null ? QuantityType.of(code) : null;
    }
}