package com.parcel.service;

import com.parcel.request.ParcelRequest;
import com.parcel.response.ParcelCostResponse;

public interface ParcelCostService {
    ParcelCostResponse calculateCost(ParcelRequest parcelRequest);
}
