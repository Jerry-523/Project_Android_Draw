package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.List;

public class Line extends Shape {
    private PointF startPoint;
    private PointF endPoint;
    private Paint paint;

    public Line(PointF startPoint, PointF endPoint, Paint paint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(endPoint.x, endPoint.y);
        canvas.drawPath(path, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        this.startPoint = startPoint;
        this.endPoint = new PointF(endPointX, endPointY);
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