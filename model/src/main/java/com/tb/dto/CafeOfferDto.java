package com.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CafeOfferDto {
    
    @Getter
    @Setter
    private String serviceName;
    @Getter
    @Setter
    private Integer cost;
    @Getter
    @Setter
    private Long cafeId;

}
