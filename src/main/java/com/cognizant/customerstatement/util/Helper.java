package com.cognizant.customerstatement.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.cognizant.customerstatement.model.ErrorRecord;

@Slf4j
public class Helper {

	public static List<ErrorRecord> listOfError(Map<Long, String> errorRecordMap) {
		log.info("Entering into Helper <listOfError> "+errorRecordMap.toString());
		List<ErrorRecord> errorRecordList = new ArrayList<ErrorRecord>();
		for (Long key : errorRecordMap.keySet()) {
			errorRecordList.add(new ErrorRecord(key, errorRecordMap.get(key)));
		}
		log.info("Entering into Helper <listOfError> "+errorRecordList.toString());
		return errorRecordList;
	}

}
