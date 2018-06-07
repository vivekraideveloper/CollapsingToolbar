package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

public class CardData {

    String heading;
    String sub;
    String desc;
    //String image;
    public CardData(String heading, String sub, String desc) {
        this.heading = heading;
        this.sub = sub;
        this.desc = desc;
        //this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public String getSub() {
        return sub;
    }

//    public String getImage() {
//        return image;
//    }

    public String getDesc() {
        return desc;
    }
}
