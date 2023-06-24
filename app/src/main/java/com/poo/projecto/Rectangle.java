package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.List;

public class Rectangle extends Shape {
    private RectF rect;
    private Paint paint;

    public Rectangle(PointF startPoint, float width, float height, Paint paint) {
        float left = Math.min(startPoint.x, startPoint.x + width);
        float right = Math.max(startPoint.x, startPoint.x + width);
        float top = Math.min(startPoint.y, startPoint.y + height);
        float bottom = Math.max(startPoint.y, startPoint.y + height);

        rect = new RectF(left, top, right, bottom);
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        float left = Math.min(startPoint.x, endPointX);
        float right = Math.max(startPoint.x, endPointX);
        float top = Math.min(startPoint.y, endPointY);
        float bottom = Math.max(startPoint.y, endPointY);

        rect.set(left, top, right, bottom);
    }

    @Override
    public char getType() {
        return 0;
    }

    @Override
    public List<Coordinate> getCoordinates() {
        return null;
    }
}

