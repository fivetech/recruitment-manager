package com.tolga.model;

public enum AppStatus {
	ACCEPTED("ACCEPTED"), REJECTED("REJECTED"), INPROGRESS("INPROGRESS");
	 private final String val;

	AppStatus(String val) {
        this.val = val;
    }

    public String getStatus() {
        return val;
    }
}
