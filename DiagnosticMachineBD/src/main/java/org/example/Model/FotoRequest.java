package org.example.Model;

public class FotoRequest {
    private String imgURL;

    public FotoRequest(){}

    public FotoRequest(String imgURL) {
        this.imgURL = imgURL;
    }

    // Getters e setters
    public String getImgURL() { return imgURL; }
    public void setImgURL(String imgURL) { this.imgURL = imgURL; }

}

