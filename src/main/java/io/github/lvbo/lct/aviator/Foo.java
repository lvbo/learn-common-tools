package io.github.lvbo.lct.aviator;

import java.util.Date;

/**
 * @author lvbo created on 2023-03-25 16:14
 */
public class Foo {

    private int i;
    private float f;
    private Date date;

    public Foo(int i, float f, Date date) {
        super();
        this.i = i;
        this.f = f;
        this.date = date;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
