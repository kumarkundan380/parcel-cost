package com.parcel.service.service.impl;

import com.parcel.constants.ParcelConstant;
import com.parcel.enums.ParcelCost;
import com.parcel.enums.ParcelType;
import com.parcel.exception.OverWeightException;
import com.parcel.request.ParcelRequest;
import com.parcel.response.ParcelCostResponse;
import com.parcel.service.ParcelCostService;
import com.parcel.validation.ParcelRequestValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class ParcelCostServiceImpl implements ParcelCostService {
    @Override
    public ParcelCostResponse calculateCost(ParcelRequest parcelRequest) {
        log.info("calculateCost method invoking...");
        ParcelRequestValidation.validateParcelRequest(parcelRequest);
        BigDecimal cost;
        String parcelType;
        BigDecimal volume = parcelRequest.getHeight().multiply(parcelRequest.getWidth()).multiply(parcelRequest.getLength());
        if(parcelRequest.getWeight().compareTo(ParcelConstant.REJECTED_WEIGHT) > 0) {
            log.error("Weight cannot be more than 50kg");
            throw new OverWeightException("Weight cannot be more than 50kg");
        } else if(parcelRequest.getWeight().compareTo(ParcelConstant.HEAVY_WEIGHT) > 0 ) {
            cost = parcelRequest.getWeight().multiply(ParcelCost.HEAVY_PARCEL.getCost());
            parcelType = ParcelType.HEAVY_PARCEL.getValue();
        } else if(volume.compareTo(ParcelConstant.SMALL_WEIGHT) < 0 ) {
            cost = volume.multiply(ParcelCost.SMALL_PARCEL.getCost());
            parcelType = ParcelType.SMALL_PARCEL.getValue();
        } else if (volume.compareTo(ParcelConstant.MEDIUM_WEIGHT) < 0 ) {
            cost = volume.multiply(ParcelCost.MEDIUM_PARCEL.getCost());
            parcelType = ParcelType.MEDIUM_PARCEL.getValue();
        } else {
            cost = volume.multiply(ParcelCost.LARGE_PARCEL.getCost());
            parcelType = ParcelType.LARGE_PARCEL.getValue();
        }
        cost = cost.setScale(2, RoundingMode.HALF_UP);
        return new ParcelCostResponse(parcelType,cost);
    }
}
