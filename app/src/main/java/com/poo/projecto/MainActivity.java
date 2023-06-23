package com.poo.projecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

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

    private static final String FILE_NAME = "drawing.dat";

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
            saveDrawing();
        } else if (v == loadButton) {
            loadDrawing();
        }
    }

    private void saveDrawing() {
        LinkedList<Shape> shapes = drawingView.getShapes();

        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(shapes);
            oos.close();
            fos.close();
            showToast("Drawing saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to save drawing");
        }
    }

    private void loadDrawing() {
        LinkedList<Shape> shapes;

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            shapes = (LinkedList<Shape>) ois.readObject();
            ois.close();
            fis.close();

            if (shapes != null) {
                drawingView.setShapes(shapes);
                drawingView.invalidate();
                showToast("Drawing loaded successfully");
            } else {
                showToast("Failed to load drawing");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showToast("Failed to load drawing");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

