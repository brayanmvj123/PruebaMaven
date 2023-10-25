package com.bdb.opalomcrm.batchcontrollers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResultJSONAudit {
	
	private String requestAlSoap;
	private String responseDelSoap;
	private String status; 
	private String exception;
	
	
}
