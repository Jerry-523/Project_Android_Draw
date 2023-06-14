package cv.um.poo.design;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Figure> figures;
    private DesignView designView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        figures = new ArrayList<>();
        designView = findViewById(R.id.designView);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFigures();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFigures();
            }
        });

        Button loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFigures();
            }
        });

        Button circleButton = findViewById(R.id.circleButton);
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designView.generateCircle();
            }
        });

        Button squareButton = findViewById(R.id.squareButton);
        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designView.generateSquare();
            }
        });

        Button triangleButton = findViewById(R.id.triangleButton);
        triangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designView.generateTriangle();
            }
        });

        Button pointButton = findViewById(R.id.pointButton);
        pointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designView.generatePoint();
            }
        });
    }

    private void resetFigures() {
        figures.clear();
        designView.setFigures(figures);
    }

    private void saveFigures() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("design.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            // Escreve o número de figuras na primeira linha
            outputStreamWriter.write(figures.size() + "\n");

            // Escreve as características de cada figura nas linhas subsequentes
            for (Figure figure : figures) {
                outputStreamWriter.write(figure.toString() + "\n");
            }

            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFigures() {
        try {
            FileInputStream fileInputStream = openFileInput("design.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            if (line != null) {
                int numFigures = Integer.parseInt(line);

                figures.clear();

                for (int i = 0; i < numFigures; i++) {
                    line = bufferedReader.readLine();
                    Figure figure = Figure.fromString(line);
                    if (figure != null) {
                        figures.add(figure);
                    }
                }

                designView.setFigures(figures);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
