package com.btcturk.uitest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class SearchItem {
    private String title;
    private String link;
    private String description;
}
