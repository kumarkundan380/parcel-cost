package com.parcel.validation;

import com.parcel.exception.ParcelCostException;
import com.parcel.request.ParcelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Slf4j
public class ParcelRequestValidation {

    public static void validateParcelRequest(ParcelRequest parcelRequest) {
        log.info("validateParcelRequest method invoking...");
        if(ObjectUtils.isEmpty(parcelRequest.getWeight())){
            log.error("Weight cannot be empty");
            throw new ParcelCostException("Weight cannot be empty");
        }
        if(ObjectUtils.isEmpty(parcelRequest.getHeight())){
            log.error("Height cannot be empty");
            throw new ParcelCostException("Height cannot be empty");
        }
        if(ObjectUtils.isEmpty(parcelRequest.getWidth())){
            log.error("Width cannot be empty");
            throw new ParcelCostException("Width cannot be empty");
        }
        if(ObjectUtils.isEmpty(parcelRequest.getLength())){
            log.error("Length cannot be empty");
            throw new ParcelCostException("Length cannot be empty");
        }
    }

}
