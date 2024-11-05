package com.raonsecure.sample.base.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SearchBy {
	// common
	ALL,
	NAME,

	// admin log
	ADMIN_ID,
	MENU_NAME,
	ADMIN_NAME,
	ADMIN_DEPARTMENT,

	// notice
	TITLE,
	CONTENT,
	// user
	HASHED_NID,
	NID,
	USER_NAME,
	// service log
	AGENCY_ID,
	API_NAME,

	// agency
	AGENCY_NAME,

	;
	@JsonCreator
	public static SearchBy fromValue(String value) {
		for (SearchBy searchBy : values()) {
			if (searchBy.name().equalsIgnoreCase(value)) {
				return searchBy;
			}
		}
		throw new IllegalArgumentException("Invalid SearchBy value: " + value);
	}
}
