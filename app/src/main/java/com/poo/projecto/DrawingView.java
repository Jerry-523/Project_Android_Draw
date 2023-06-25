package com.poo.projecto;


// DrawingView.java
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class DrawingView extends View {

    private Paint paint;
    private LinkedList<Shape> shapes;
    private PointF startPoint;
    private ShapeType currentShape;
    private Shape currentDrawingShape;
    private final String saveFileName = "drawing_save.dat";

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

    @SuppressLint("ClickableViewAccessibility")
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

    public void saveDrawing() {
        try {
            FileOutputStream fileOut = getContext().openFileOutput(saveFileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(shapes);
            objectOut.close();
            fileOut.close();
            Toast.makeText(getContext(), "Drawing saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to save drawing", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadDrawing() {
        try {
            FileInputStream fileIn = getContext().openFileInput(saveFileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            shapes = (LinkedList<Shape>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
            Toast.makeText(getContext(), "Drawing loaded successfully", Toast.LENGTH_SHORT).show();
            invalidate();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to load drawing", Toast.LENGTH_SHORT).show();
        }
    }
}

