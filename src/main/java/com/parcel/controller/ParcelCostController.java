package com.parcel.controller;

import com.parcel.enums.ResponseStatus;
import com.parcel.request.ParcelRequest;
import com.parcel.response.ParcelApiResponse;
import com.parcel.service.ParcelCostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parcel-cost")
@Slf4j
public class ParcelCostController {

    private final ParcelCostService parcelCostService;

    @Autowired
    public ParcelCostController(ParcelCostService parcelCostService) {
        this.parcelCostService = parcelCostService;
    }

    @PostMapping
    public ResponseEntity<?> parcelCost(@RequestBody ParcelRequest parcelRequest) {
        log.info("parcelCost method invoking...");
        return new ResponseEntity<>(ParcelApiResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .isError(Boolean.FALSE)
                .response(parcelCostService.calculateCost(parcelRequest))
                .build(),
                HttpStatus.OK);
    }

}
