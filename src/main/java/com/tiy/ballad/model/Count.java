package com.tiy.ballad.model;

/**
 * Created by josh on 5/8/17.
 */
public class Count {
    private Integer count;

    public Count() {
    }

    public Count(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        this.count ++;
        if(this.count > 3){
            this.count =1;

        }
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
