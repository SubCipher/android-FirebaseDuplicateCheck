package com.stepwisedesigns.workingwithdatasnapshots;

public class Category {

    private String name;
    private String imageLink;

    public Category(){ }

    public Category(String name, String url){
        name = name;
        imageLink = url;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
