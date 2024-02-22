package com.parcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcel.enums.ResponseStatus;
import com.parcel.enums.Rules;
import com.parcel.request.ParcelRequest;
import com.parcel.response.ParcelCostResponse;
import com.parcel.service.ParcelCostService;
import com.parcel.service.service.impl.ParcelCostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcelCostController.class)
class ParcelCostControllerTest {

    @MockBean
    private ParcelCostService parcelCostService;

    @Autowired
    private MockMvc mvc;

    @Test
    void testParcelCostForSuccessResponse() throws Exception {
        ParcelRequest parcelRequest = new ParcelRequest(BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
        ParcelCostResponse parcelCostResponse = new ParcelCostResponse(Rules.SMALL_PARCEL.getValue(),BigDecimal.TEN);
        when(parcelCostService.calculateCost(parcelRequest)).thenReturn(parcelCostResponse);
        mvc.perform( post("/api/v1/parcel-cost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(parcelRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(ResponseStatus.SUCCESS.getValue()))
                .andExpect(jsonPath("$.isError").value(false));
               // .andExpect(jsonPath("$.response").value(new ObjectMapper().writeValueAsString(parcelCostResponse)));
    }


}