package com.example.geocoding.Dto;

import com.example.geocoding.Model.Store;
import lombok.Data;

@Data
public class SaveStoreDto {
    private Long companyId;
    private Store store;
}
