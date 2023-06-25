package com.poo.projecto;

// MainActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawingView drawingView;
    private RadioGroup shapeRadioGroup;
    private RadioButton lineRadioButton;
    private RadioButton circleRadioButton;
    private RadioButton rectangleRadioButton;
    private RadioButton pixelRadioButton;
    private Button clearButton;
    private Button saveButton;
    private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawing_view);
        shapeRadioGroup = findViewById(R.id.radioGroup);
        lineRadioButton = findViewById(R.id.radioButtonLine);
        circleRadioButton = findViewById(R.id.radioButtonCircle);
        rectangleRadioButton = findViewById(R.id.radioButtonRectangle);
        pixelRadioButton = findViewById(R.id.radioButtonPixel);
        clearButton = findViewById(R.id.clear_button);
        saveButton = findViewById(R.id.btn_save);
        loadButton = findViewById(R.id.btn_load);

        shapeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonLine) {
                    drawingView.setCurrentShape(ShapeType.LINE);
                } else if (checkedId == R.id.radioButtonCircle) {
                    drawingView.setCurrentShape(ShapeType.CIRCLE);
                } else if (checkedId == R.id.radioButtonRectangle) {
                    drawingView.setCurrentShape(ShapeType.RECTANGLE);
                } else if (checkedId == R.id.radioButtonPixel) {
                    drawingView.setCurrentShape(ShapeType.PIXEL);
                }
            }
        });

        clearButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == clearButton) {
            drawingView.clear();
        } else if (v == saveButton) {
            drawingView.saveDrawing();
        } else if (v == loadButton) {
            drawingView.loadDrawing();
        }
    }
}

