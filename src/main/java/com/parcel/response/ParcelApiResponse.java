package com.parcel.response;

import com.parcel.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelApiResponse<T> {
    private ResponseStatus status;
    private Boolean isError;
    private T response;

}
