package com.logicMonitor.web.common;

public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox");

    public String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
