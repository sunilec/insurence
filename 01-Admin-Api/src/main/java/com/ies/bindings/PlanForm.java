package com.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlanForm {


    private String planCategory;
    private String planName;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
}
