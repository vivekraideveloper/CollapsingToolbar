package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

public class CardData {

    String heading;
    String sub;
    String desc;
    int pos;

    //String image;
    public CardData(String heading, String sub, String desc) {
        this.heading = heading;
        this.sub = sub;
        this.desc = desc;
        this.pos=pos;
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

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

}
