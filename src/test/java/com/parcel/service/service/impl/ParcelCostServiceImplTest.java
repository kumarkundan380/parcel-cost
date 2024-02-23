package com.parcel.service.service.impl;

import com.parcel.enums.ParcelType;
import com.parcel.exception.OverWeightException;
import com.parcel.exception.ParcelCostException;
import com.parcel.request.ParcelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ParcelCostServiceImplTest {

    @InjectMocks
    private ParcelCostServiceImpl parcelCostService;

    private ParcelRequest parcelRequest;

    @BeforeEach
    public void init() {
        parcelRequest = new ParcelRequest();
    }

    @Test
    void testThrowsOverWeightParcelException() {
        parcelRequest.setWeight(new BigDecimal("51.00"));
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertThrows(OverWeightException.class, () -> parcelCostService.calculateCost(parcelRequest));
    }

    @Test
    void testCalculateCostForHeavyParcel() {
        parcelRequest.setWeight(new BigDecimal("11.00"));
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertEquals(ParcelType.HEAVY_PARCEL.getValue(),parcelCostService.calculateCost(parcelRequest).getParcelType());
    }

    @Test
    void testCalculateCostForSmallParcel() {
        parcelRequest.setWeight(BigDecimal.ONE);
        parcelRequest.setHeight(BigDecimal.ONE);
        parcelRequest.setWidth(BigDecimal.ONE);
        parcelRequest.setLength(BigDecimal.ONE);
        assertEquals(ParcelType.SMALL_PARCEL.getValue(),parcelCostService.calculateCost(parcelRequest).getParcelType());
    }

    @Test
    void testCalculateCostForMediumParcel() {
        parcelRequest.setWeight(BigDecimal.ONE);
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertEquals(ParcelType.MEDIUM_PARCEL.getValue(),parcelCostService.calculateCost(parcelRequest).getParcelType());
    }

    @Test
    void testCalculateCostForLargeParcel() {
        parcelRequest.setWeight(BigDecimal.ONE);
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("150.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertEquals(ParcelType.LARGE_PARCEL.getValue(),parcelCostService.calculateCost(parcelRequest).getParcelType());
    }

    @Test
    void testThrowsWeightFieldMissingException() {
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertThrows(ParcelCostException.class, () -> parcelCostService.calculateCost(parcelRequest));
    }

    @Test
    void testThrowsWidthFieldMissingException() {
        parcelRequest.setWeight(new BigDecimal("10.34"));
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertThrows(ParcelCostException.class, () -> parcelCostService.calculateCost(parcelRequest));
    }

    @Test
    void testThrowsHeightFieldMissingException() {
        parcelRequest.setWeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        parcelRequest.setLength(new BigDecimal("10.78"));
        assertThrows(ParcelCostException.class, () -> parcelCostService.calculateCost(parcelRequest));
    }

    @Test
    void testThrowsLengthFieldMissingException() {
        parcelRequest.setWeight(new BigDecimal("10.34"));
        parcelRequest.setHeight(new BigDecimal("10.34"));
        parcelRequest.setWidth(new BigDecimal("15.34"));
        assertThrows(ParcelCostException.class, () -> parcelCostService.calculateCost(parcelRequest));
    }
}