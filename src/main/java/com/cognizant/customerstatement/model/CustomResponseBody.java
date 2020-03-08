package com.cognizant.customerstatement.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class CustomResponseBody {

	private String result;

	private List<ErrorRecord> errorRecord;

	public void setErrorRecords(List<ErrorRecord> errorRecord) {
		this.errorRecord = errorRecord;
	}
}
