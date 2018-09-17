package puzzle.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class game extends AppCompatActivity {

    ImageView[] imageViews;
    TextView textView;
    int[] stats;
    Button button;
    Random random = new Random();
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView = findViewById(R.id.textView3);
        imageViews = new ImageView[9];
        stats = new int[9];
        initImageViews();
        startGame();
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    protected void setImagesToViews(){
        for (int i = 0; i < 9; i++){
            String mDrawableName = "p" + (stats[i]+1);
            int resID = imageViews[i].getContext().getResources().getIdentifier(mDrawableName , "drawable", imageViews[i].getContext().getPackageName());
            imageViews[i].setImageResource(resID);
        }
        if (isWin()) imageViews[8].setImageResource(R.drawable.p10);
    }
    protected void updateScoreStatus(){
        if(isWin()){
            textView.setText("Вы выиграли!\nСчет: " + score);
        }
        else {
            textView.setText("Счет: " + score);
        }
    }
    protected void doTurn(int turn){
        if(canTurn(turn)){
            int black = whereBlack();
            stats[black] = stats[turn];
            stats[turn] = 8;
            setImagesToViews();
            score++;
            updateScoreStatus();
        }
    }
    protected void initImageViews(){
        imageViews[0] = findViewById(R.id.imageView);
        imageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(0);
            }
        });
        imageViews[1] = findViewById(R.id.imageView2);
        imageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(1);
            }
        });
        imageViews[2] = findViewById(R.id.imageView3);
        imageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(2);
            }
        });
        imageViews[3] = findViewById(R.id.imageView4);
        imageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(3);
            }
        });
        imageViews[4] = findViewById(R.id.imageView5);
        imageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(4);
            }
        });
        imageViews[5] = findViewById(R.id.imageView6);
        imageViews[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(5);
            }
        });
        imageViews[6] = findViewById(R.id.imageView7);
        imageViews[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(6);
            }
        });
        imageViews[7] = findViewById(R.id.imageView8);
        imageViews[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(7);
            }
        });
        imageViews[8] = findViewById(R.id.imageView9);
        imageViews[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isWin())doTurn(8);
            }
        });
    }

    protected boolean isWin(){
        boolean win = true;
        for(int i = 0; i<9; i++){
            if(stats[i] != i) win = false;
        }
        return win;
    }

    protected boolean canTurn(int turn){
        boolean res = false;
        switch (turn){
            case 0:
                if(stats[1]==8 || stats[3]==8) res = true;
                break;
            case 1:
                if(stats[0]==8 || stats[2]==8 || stats[4]==8) res = true;
                break;
            case 2:
                if(stats[1]==8 || stats[5]==8) res = true;
                break;
            case 3:
                if(stats[0]==8 || stats[6]==8 || stats[4]==8) res = true;
                break;
            case 4:
                if(stats[1]==8 || stats[3]==8 || stats[5]==8 || stats[7]==8) res = true;
                break;
            case 5:
                if(stats[2]==8 || stats[4]==8 || stats[8]==8) res = true;
                break;
            case 6:
                if(stats[3]==8 || stats[7]==8) res = true;
                break;
            case 7:
                if(stats[6]==8 || stats[4]==8 || stats[8]==8) res = true;
                break;
            case 8:
                if(stats[5]==8 || stats[7]==8) res = true;
                break;
        }
        return res;
    }
    protected void startGame(){
        for(int i=0; i<9; i++) stats[i] = i;
        for (int i=0; i < 300; i++) {
            int t = random.nextInt(9);
            doTurn(t);
        }
        score = 0;
        updateScoreStatus();
    }
    protected int whereBlack(){
        int out = 0;
        for(int i=0; i<9; i++){
            if(stats[i]==8) out = i;
        }
        return out;
    }
}
