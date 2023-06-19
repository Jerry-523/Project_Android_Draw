package cv.um.poo.design;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
        // Implemente o código para salvar as figuras, se necessário
    }

    private void loadFigures() {
        // Implemente o código para carregar as figuras, se necessário
    }
}
