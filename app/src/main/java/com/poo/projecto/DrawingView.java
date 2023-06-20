package com.poo.projecto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawingView extends View {

    private Paint paint;
    private List<Shape> shapes;
    private Path currentPath;
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

        shapes = new ArrayList<>();
        currentPath = new Path();
        currentShape = ShapeType.FREE_DRAW;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Shape shape : shapes) {
            shape.draw(canvas);
        }

        if (currentShape == ShapeType.FREE_DRAW) {
            canvas.drawPath(currentPath, paint);
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
                if (currentShape == ShapeType.FREE_DRAW) {
                    currentPath.moveTo(x, y);
                } else {
                    createShape(x, y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentShape == ShapeType.FREE_DRAW) {
                    currentPath.lineTo(x, y);
                } else {
                    resizeShape(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentShape == ShapeType.FREE_DRAW) {
                    shapes.add(new FreeDraw(currentPath, paint));
                    currentPath.reset();
                } else {
                    resizeShape(x, y);
                    shapes.add(currentDrawingShape);
                    currentDrawingShape = null;
                }
                break;
        }

        invalidate();
        return true;
    }

    private void createShape(float endPointX, float endPointY) {
        float width = endPointX - startPoint.x;
        float height = endPointY - startPoint.y;

        switch (currentShape) {
            case CIRCLE:
                currentDrawingShape = new Circle(startPoint, 0, paint);
                break;
            case RECTANGLE:
                currentDrawingShape = new Rectangle(startPoint, width, height, paint);
                break;
            case TRIANGLE:
                currentDrawingShape = new Triangle(startPoint, width, height, paint);
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
        currentPath.reset();
        invalidate();
    }
}



