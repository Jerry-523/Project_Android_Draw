package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.List;

public class Circle extends Shape {
    private PointF center;
    private float radius;
    private Paint paint;

    public Circle(PointF center, float radius, Paint paint) {
        this.center = center;
        this.radius = radius;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        float dx = endPointX - startPoint.x;
        float dy = endPointY - startPoint.y;
        float newRadius = (float) Math.sqrt(dx * dx + dy * dy);

        radius = newRadius;
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

