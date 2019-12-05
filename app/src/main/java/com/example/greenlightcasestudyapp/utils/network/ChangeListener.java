package com.example.greenlightcasestudyapp.utils.network;

public class ChangeListener {
    boolean boo = false;

    public ChangeListener(boolean b){
        boo = b;
    }

    private listener l = null;

    public interface listener{
        void onChange(boolean b);
    }

    //set Change Listener
    public void setChangeListener(listener mListener){
        l = mListener;
    }

    public void somethingChanged(){
        if(l != null){
            l.onChange(boo);
        }
    }
}
