package com.goku.interfaces.out.proxy.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WideNet {
    private int status;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;
}
