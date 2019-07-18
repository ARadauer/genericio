package com.radauer.genericio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model
{
    @OrderedField(order = 1)
    private String v1;
    @OrderedField(order = 2)
    private String v2;
    @OrderedField(order = 3)
    private String v3;
    @OrderedField(order = 4)
    private String v4;
    @OrderedField(order = 5)
    private String v5;
    @OrderedField(order = 6)
    private String v6;
    @OrderedField(order = 7)
    private String v7;
    @OrderedField(order = 8)
    private String v8;
    @OrderedField(order = 9)
    private String v9;
    @OrderedField(order = 10)
    private String v10;

}
