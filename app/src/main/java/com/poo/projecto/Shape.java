package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

public abstract class Shape {
    protected PointF startPoint;
    protected Paint paint;

    public Shape(PointF startPoint) {
        this.startPoint = startPoint;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFF0000FF); // Azul
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
    }

    protected Shape() {
    }

    public abstract void draw(Canvas canvas);

    public abstract void resize(PointF startPoint, float x, float y);

    public abstract char getType();

    public abstract List<Coordinate> getCoordinates();
}

