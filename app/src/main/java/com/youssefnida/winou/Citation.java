package com.youssefnida.winou;

/**
 * Created by youssefNIDA on 03/04/2017.
 */

public class Citation {

    String citation_text;

    public Citation(){}

    public Citation(String citation_text){
        this.citation_text = citation_text;
    }

    public String getCitation_text() {
        return citation_text;
    }

    public void setCitation_text(String citation_text) {
        this.citation_text = citation_text;
    }
}
