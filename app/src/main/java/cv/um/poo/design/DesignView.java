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

import cv.um.poo.design.Figure;

public class DesignView extends View {

    private List<Figure> figures;
    private Figure currentFigure;
    private FigureType currentFigureType;

    private enum FigureType {
        CIRCLE, SQUARE, TRIANGLE, POINT
    }

    public DesignView(Context context, AttributeSet attrs) {
        super(context, attrs);
        figures = new ArrayList<>();
        currentFigure = null;
        currentFigureType = null;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
        invalidate();
    }

    public void setCurrentFigureType(FigureType figureType) {
        currentFigureType = figureType;
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
                canvas.drawPath(path, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentFigure = new Figure();
                currentFigure.addPoint(new PointF(x, y));
                break;
            case MotionEvent.ACTION_MOVE:
                currentFigure.addPoint(new PointF(x, y));
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                figures.add(currentFigure);
                currentFigure = null;
                invalidate();
                break;
        }

        return true;
    }

    public void generateCircle() {
        setCurrentFigureType(FigureType.CIRCLE);
        figures.add(generateFigure(FigureType.CIRCLE));
        invalidate();
    }

    public void generateSquare() {
        setCurrentFigureType(FigureType.SQUARE);
        figures.add(generateFigure(FigureType.SQUARE));
        invalidate();
    }

    public void generateTriangle() {
        setCurrentFigureType(FigureType.TRIANGLE);
        figures.add(generateFigure(FigureType.TRIANGLE));
        invalidate();
    }

    public void generatePoint() {
        setCurrentFigureType(FigureType.POINT);
        figures.add(generateFigure(FigureType.POINT));
        invalidate();
    }

    // Lógica para gerar as coordenadas do ponto, círculo, quadrado ou triângulo
    // Adicione a lógica adequada aqui
    private Figure generateFigure(FigureType figureType) {
        Figure figure = new Figure();
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float radius = Math.min(getWidth(), getHeight()) / 4;

        switch (figureType) {
            case POINT:
                figure.addPoint(new PointF(centerX, centerY));
                break;
            case CIRCLE:
                int numPoints = 360;
                for (int i = 0; i < numPoints; i++) {
                    float angle = (float) (i * 2 * Math.PI / numPoints);
                    float x = centerX + radius * (float) Math.cos(angle);
                    float y = centerY + radius * (float) Math.sin(angle);
                    figure.addPoint(new PointF(x, y));
                }
                break;
            case SQUARE:
                figure.addPoint(new PointF(centerX - radius, centerY - radius));
                figure.addPoint(new PointF(centerX + radius, centerY - radius));
                figure.addPoint(new PointF(centerX + radius, centerY + radius));
                figure.addPoint(new PointF(centerX - radius, centerY + radius));
                figure.addPoint(new PointF(centerX - radius, centerY - radius));
                break;
            case TRIANGLE:
                figure.addPoint(new PointF(centerX, centerY - radius));
                figure.addPoint(new PointF(centerX + radius, centerY + radius));
                figure.addPoint(new PointF(centerX - radius, centerY + radius));
                figure.addPoint(new PointF(centerX, centerY - radius));
                break;
        }

        return figure;
    }
}
