package com.noname.qn.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.noname.qn.service.gui.Focusable;

import java.util.ArrayList;
import java.util.List;

public class FocusableTable extends Table {
    private List<Focusable> focusables = new ArrayList<>();
    public Focusable focused;


    public FocusableTable( String title) {
        this(title,200);
    }
    public FocusableTable( String title, int titleHeight) {
        top();
        setFillParent(true);
        add(new Label(title, Fonts.getUnFocusStyle())).height(titleHeight);
    }

    public Cell<Actor> addLabel(String text, ClickListener event, boolean newLine){
        FocusableLabel l = new FocusableLabel(text);
        buildCommon(l,newLine);
        l.addListener(event);
        return add(l);
    }
    public Cell<Actor> addLabel(String text, ClickListener event){
        return this.addLabel(text,event,true);
    }

    public Cell<Actor> addImageButton(String focusFilePath,String unFocusFilePath, ClickListener event, boolean newLine){
        FocusableImageButton i = new FocusableImageButton(focusFilePath,unFocusFilePath);
        buildCommon(i,newLine);
        i.addListener(event);
        return add(i);
    }
    public Cell<Actor> addImageButton(String focusFilePath,String unFocusFilePath, ClickListener event){
        return this.addImageButton(focusFilePath,unFocusFilePath,event,true);
    }

    private void buildCommon(Focusable f,boolean newLine){
        if(newLine)
            row();
        focusables.add(f);
        if(getCells().size==1)
            setFocus(f);
    }

    public void setFocus(Focusable actor){
        focused = actor;
        for (Focusable l :focusables){
            l.setFocus(l.equals(actor));
        }
    }

    public void setNextFocus(){
        int i = focusables.indexOf(focused);
        if(i!=focusables.size()-1)
            setFocus(focusables.get(i+1));
    }

    public void setPreviousFocus(){
        int i = focusables.indexOf(focused);
        if(i>0)
            setFocus(focusables.get(i-1));
    }
}