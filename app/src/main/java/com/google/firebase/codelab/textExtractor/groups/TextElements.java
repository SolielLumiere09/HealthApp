package com.google.firebase.codelab.textExtractor.groups;

import android.graphics.Rect;


public class TextElements {
    private final String text;
    private final Rect frame;

    public TextElements(String text, Rect frame) {
        this.text = text;
        this.frame = frame;
    }


    public String getText() {
        return text;
    }

    public Rect getFrame() {
        return frame;
    }

}
