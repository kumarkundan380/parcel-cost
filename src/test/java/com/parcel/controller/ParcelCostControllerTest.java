package com.parcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcel.enums.ParcelType;
import com.parcel.enums.ResponseStatus;
import com.parcel.exception.OverWeightException;
import com.parcel.exception.ParcelCostException;
import com.parcel.request.ParcelRequest;
import com.parcel.response.ParcelCostResponse;
import com.parcel.service.ParcelCostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcelCostController.class)
class ParcelCostControllerTest {

    @MockBean
    private ParcelCostService parcelCostService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testParcelCostForSuccessResponse() throws Exception {
        ParcelRequest parcelRequest = new ParcelRequest(BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
        ParcelCostResponse parcelCostResponse = new ParcelCostResponse(ParcelType.SMALL_PARCEL.getValue(),BigDecimal.TEN);
        when(parcelCostService.calculateCost(any())).thenReturn(parcelCostResponse);
        mockMvc.perform( post("/api/v1/parcel-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parcelRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseStatus.SUCCESS.getValue()))
                .andExpect(jsonPath("$.isError").value(Boolean.FALSE))
                .andExpect(jsonPath("$.response.parcelType").value(ParcelType.SMALL_PARCEL.getValue()))
                .andExpect(jsonPath("$.response.cost").value(BigDecimal.TEN));
    }

    @Test
    void testParcelCostForRejectResponse() throws Exception {
        ParcelRequest parcelRequest = new ParcelRequest(new BigDecimal("55.0"),BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
        when(parcelCostService.calculateCost(any())).thenThrow(new OverWeightException("Weight cannot be more than 50kg"));
        mockMvc.perform( post("/api/v1/parcel-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parcelRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(ResponseStatus.REJECT.getValue()))
                .andExpect(jsonPath("$.isError").value(Boolean.TRUE))
                .andExpect(jsonPath("$.response").value("Weight cannot be more than 50kg"));
    }

    @Test
    void testParcelCostForErrorResponse() throws Exception {
        ParcelRequest parcelRequest = new ParcelRequest(null,BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
        when(parcelCostService.calculateCost(any())).thenThrow(new ParcelCostException("Weight cannot be empty"));
        mockMvc.perform( post("/api/v1/parcel-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parcelRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(ResponseStatus.ERROR.getValue()))
                .andExpect(jsonPath("$.isError").value(Boolean.TRUE))
                .andExpect(jsonPath("$.response").value("Weight cannot be empty"));
    }



}