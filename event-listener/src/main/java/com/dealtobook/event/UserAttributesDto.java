package com.dealtobook.event;

import java.util.List;

public class UserAttributesDto {

    private List<String> locale;
    private List<String> title;
    private List<String> tierCategory;

    public UserAttributesDto(List<String> locale, List<String> title, List<String> tierCategory) {
        this.locale = locale;
        this.title = title;
        this.tierCategory = tierCategory;
    }

    public List<String> getLocale() {
        return locale;
    }

    public void setLocale(List<String> locale) {
        this.locale = locale;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getTierCategory() {
        return tierCategory;
    }

    public void setTierCategory(List<String> tierCategory) {
        this.tierCategory = tierCategory;
    }
}