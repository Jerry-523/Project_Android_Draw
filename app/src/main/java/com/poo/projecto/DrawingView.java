package com.poo.projecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

public class DrawingView extends View {

    private Paint paint;
    private LinkedList<Shape> shapes;
    private PointF startPoint;
    private ShapeType currentShape;
    private Shape currentDrawingShape;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    private void setup() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        shapes = new LinkedList<>();
        currentShape = ShapeType.LINE;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Shape shape : shapes) {
            shape.draw(canvas);
        }

        if (currentDrawingShape != null) {
            currentDrawingShape.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startPoint = new PointF(x, y);
                createShape(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                resizeShape(x, y);
                break;
            case MotionEvent.ACTION_UP:
                resizeShape(x, y);
                shapes.add(currentDrawingShape);
                currentDrawingShape = null;
                break;
        }

        invalidate();
        return true;
    }

    private void createShape(float endPointX, float endPointY) {
        switch (currentShape) {
            case CIRCLE:
                currentDrawingShape = new Circle(startPoint, 0, paint);
                break;
            case RECTANGLE:
                currentDrawingShape = new Rectangle(startPoint, endPointX, endPointY, paint);
                break;
            case LINE:
                currentDrawingShape = new Line(startPoint, new PointF(endPointX, endPointY), paint);
                break;
            case PIXEL:
                currentDrawingShape = new Pixel(startPoint, paint);
                break;
        }
    }

    private void resizeShape(float endPointX, float endPointY) {
        if (currentDrawingShape != null) {
            currentDrawingShape.resize(startPoint, endPointX, endPointY);
        }
    }

    public void setCurrentShape(ShapeType shapeType) {
        currentShape = shapeType;
    }

    public void clear() {
        shapes.clear();
        invalidate();
    }

    public LinkedList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(LinkedList<Shape> shapes) {
        this.shapes = shapes;
        invalidate();
    }
}


