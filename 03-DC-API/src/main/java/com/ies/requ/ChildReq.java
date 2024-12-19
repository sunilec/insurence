package com.ies.requ;

import java.util.List;


import com.ies.binding.Child;
import lombok.Data;

@Data
public class ChildReq {
	
	private Integer caseNo;
	
	private List<Child> child;
}
