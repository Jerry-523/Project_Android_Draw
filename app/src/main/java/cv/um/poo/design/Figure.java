package cv.um.poo.design;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class Figure {
    private List<PointF> points;

    public Figure() {
        points = new ArrayList<>();
    }

    public void addPoint(PointF point) {
        points.add(point);
    }

    public List<PointF> getPoints() {
        return points;
    }

    public static Figure fromString(String line) {
        String[] coordinates = line.split(",");
        if (coordinates.length % 2 != 0) {
            return null;
        }

        Figure figure = new Figure();
        for (int i = 0; i < coordinates.length; i += 2) {
            float x = Float.parseFloat(coordinates[i]);
            float y = Float.parseFloat(coordinates[i + 1]);
            figure.addPoint(new PointF(x, y));
        }

        return figure;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (PointF point : points) {
            stringBuilder.append(point.x).append(",").append(point.y).append(",");
        }
        return stringBuilder.toString();
    }
}
