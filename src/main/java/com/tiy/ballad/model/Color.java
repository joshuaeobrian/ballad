package com.tiy.ballad.model;

/**
 * Created by josh on 5/12/17.
 */
public class Color {

    private Integer id;
    private String hexCode;
    private String colorName;

    public Color() {
    }

    public Color(Integer id, String hexCode, String colorName) {
        this.id = id;
        this.hexCode = hexCode;
        this.colorName = colorName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
