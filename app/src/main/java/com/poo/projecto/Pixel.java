package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.List;

public class Pixel extends Shape {
    private PointF point;
    private Paint paint;

    public Pixel(PointF point, Paint paint) {
        this.point = point;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPoint(point.x, point.y, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        // Atualizar a posição do pixel para o novo ponto
        this.point = new PointF(endPointX, endPointY);
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
