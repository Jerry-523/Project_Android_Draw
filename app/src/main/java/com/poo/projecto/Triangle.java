package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.List;

public class Triangle extends Shape {
    private Path path;
    private Paint paint;

    public Triangle(PointF startPoint, float width, float height, Paint paint) {
        path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(startPoint.x + width, startPoint.y);
        path.lineTo(startPoint.x + (width / 2), startPoint.y + height);
        path.close();

        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        path.reset();
        path.moveTo(startPoint.x, startPoint.y);
        path.lineTo(endPointX, startPoint.y);
        path.lineTo((startPoint.x + endPointX) / 2, endPointY);
        path.close();
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

