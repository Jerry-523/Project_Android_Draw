package com.poo.projecto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.List;

public class FreeDraw extends Shape {
    private Path path;
    private Paint paint;

    public FreeDraw(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public void resize(PointF startPoint, float endPointX, float endPointY) {
        // Implemente o redimensionamento, se necessário, para a forma de desenho livre
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

