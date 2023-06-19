package cv.um.poo.design;

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

public class DesignView extends View {

    private List<Figure> figures;
    private Figure currentFigure;
    private boolean resizing;
    private float scaleFactor;

    public DesignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        figures = new ArrayList<>();
        currentFigure = null;
        resizing = false;
        scaleFactor = 1.0f;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
        invalidate();
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Desenhar figuras existentes
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);

        for (Figure figure : figures) {
            List<PointF> points = figure.getPoints();
            if (points.size() > 0) {
                Path path = new Path();
                path.moveTo(points.get(0).x, points.get(0).y);
                for (int i = 1; i < points.size(); i++) {
                    path.lineTo(points.get(i).x, points.get(i).y);
                }
                path.close();
                canvas.drawPath(path, paint);
            }
        }

        // Desenhar figura em andamento
        if (currentFigure != null) {
            List<PointF> points = currentFigure.getPoints();
            if (points.size() > 0) {
                Path path = new Path();
                path.moveTo(points.get(0).x, points.get(0).y);
                for (int i = 1; i < points.size(); i++) {
                    path.lineTo(points.get(i).x, points.get(i).y);
                }
                path.close();
                canvas.drawPath(path, paint);
            }
        }

        // Desenhar retângulo de redimensionamento
        if (resizing && currentFigure != null && currentFigure.getPoints().size() == 2) {
            float startX = currentFigure.getPoints().get(0).x;
            float startY = currentFigure.getPoints().get(0).y;
            float endX = currentFigure.getPoints().get(1).x;
            float endY = currentFigure.getPoints().get(1).y;

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.LTGRAY);
            canvas.drawRect(startX, startY, endX, endY, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawRect(startX, startY, endX, endY, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!resizing) {
                    currentFigure = new Figure();
                    currentFigure.addPoint(new PointF(x, y));
                    resizing = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (resizing && currentFigure != null && currentFigure.getPoints().size() == 1) {
                    float startX = currentFigure.getPoints().get(0).x;
                    float startY = currentFigure.getPoints().get(0).y;
                    float width = x - startX;
                    float height = y - startY;

                    currentFigure.addPoint(new PointF(startX + width, startY + height));

                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (resizing && currentFigure != null && currentFigure.getPoints().size() == 2) {
                    figures.add(currentFigure);
                    currentFigure = null;
                    resizing = false;
                    invalidate();
                }
                break;
        }

        return true;
    }

    public void generateCircle() {
        if (!resizing) {
            currentFigure = new Circle();
            currentFigure.addPoint(new PointF());
            resizing = true;
            invalidate();
        }
    }

    public void generateSquare() {
        if (!resizing) {
            currentFigure = new Square();
            currentFigure.addPoint(new PointF());
            resizing = true;
            invalidate();
        }
    }

    public void generateTriangle() {
        if (!resizing) {
            currentFigure = new Triangle();
            currentFigure.addPoint(new PointF());
            resizing = true;
            invalidate();
        }
    }

    public void generatePoint() {
        if (!resizing) {
            currentFigure = new Point();
            currentFigure.addPoint(new PointF());
            resizing = true;
            invalidate();
        }
    }
}
