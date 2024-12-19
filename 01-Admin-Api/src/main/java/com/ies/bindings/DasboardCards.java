package com.ies.bindings;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DasboardCards {

    private Long plansCnt;
    private Long approvedCnt;
    private Long deniedCnt;
    private Double beniftAmtGiven;

    private UserAccForm user;
}
