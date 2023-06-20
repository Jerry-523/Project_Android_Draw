package com.poo.projecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawingView drawingView;
    private Button circleButton;
    private Button rectangleButton;
    private Button triangleButton;
    private Button freeDrawButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawing_view);

        circleButton = findViewById(R.id.circle_button);
        rectangleButton = findViewById(R.id.rectangle_button);
        triangleButton = findViewById(R.id.triangle_button);
        freeDrawButton = findViewById(R.id.free_draw_button);
        clearButton = findViewById(R.id.clear_button);

        circleButton.setOnClickListener(this);
        rectangleButton.setOnClickListener(this);
        triangleButton.setOnClickListener(this);
        freeDrawButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == circleButton) {
            drawingView.setCurrentShape(ShapeType.CIRCLE);
        } else if (v == rectangleButton) {
            drawingView.setCurrentShape(ShapeType.RECTANGLE);
        } else if (v == triangleButton) {
            drawingView.setCurrentShape(ShapeType.TRIANGLE);
        } else if (v == freeDrawButton) {
            drawingView.setCurrentShape(ShapeType.FREE_DRAW);
        } else if (v == clearButton) {
            drawingView.clear();
        }
    }
}
