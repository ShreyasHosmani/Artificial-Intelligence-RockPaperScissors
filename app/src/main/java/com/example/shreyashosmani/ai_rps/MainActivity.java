package com.example.shreyashosmani.ai_rps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.Math;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Weights
    private double paperChance = 33;
    private double rockChance = 33;

    //Thinking
    private double repeatStyleWin = 1;
    private double repeatStyleLose = 1;
    private double repeatStyleDraw = 1;
    private double alterStyleWin = 0;
    private double alterStyleLose = 0;
    private double alterStyleDraw = 0;
    private double rockCount = 0;
    private double paperCount = 0;
    private double scissorsCount = 0;

    //Personality
    private double confidence = 5;
    private double forgeLimit = 3;

    //Game
    private int random;
    private String userChoice;
    private String aiChoice;
    private String result;
    private double aiScore = 0;
    private double userScore = 0;

    //Memory
    private String historyChoice = "none";
    private double historyResult;

    private Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rock(View v){

        //Learning Start
        rockCount = rockCount + 1;
        userChoice = "rock";
        //Learning End

        //Luck Starts
        random = r.nextInt(102 - 1);
        //Luck Ends

        if (random < paperChance){

            //Game Consequences Start
            aiChoice = "paper";
            aiScore = aiScore + 1;
            confidence = confidence + 1;
            result = "You Lost";
            //Game Consequences End

            //Strategy Development Start
            historyResult = 0;
            if (historyChoice.equals(userChoice)){

                repeatStyleLose = repeatStyleLose + 1;

            }else {

                alterStyleLose = alterStyleLose + 1;

            }
            //Strategy Development End
            //Forgetting Starts
            //Forgetting Ends


        } else {

            if (random < (paperChance + rockChance)){

                aiChoice = "rock";
                result = "Draw";

                if (historyChoice.equals(userChoice)){

                    repeatStyleDraw = repeatStyleDraw + 1;

                }else {

                    alterStyleDraw = alterStyleDraw + 1;

                }
                //Forgetting Starts
                //Forgetting Ends


            }else {

                aiChoice = "scissors";
                userScore = userScore + 1;
                confidence = confidence - 2;
                result = "You Won!";

                historyResult = 1;

                if (historyChoice.equals(userChoice)){

                    repeatStyleWin = repeatStyleWin + 1;

                }else {

                    alterStyleWin = alterStyleWin + 1;

                }

            }

        }

        //Strategy Adjustment Start
        if (result.equals("You Lost!")){

            if (alterStyleLose > repeatStyleLose){

                rockChance = rockChance - (confidence/2);
                paperChance = paperChance - confidence;

            }else if (repeatStyleLose > alterStyleLose){

                rockChance = rockChance + (confidence/2);
                paperChance = paperChance + confidence;

            }

        }

        else {

            if (result.equals("You Won!")){

                if (repeatStyleWin > alterStyleWin){

                    rockChance = rockChance + (confidence/2);
                    paperChance = paperChance + confidence;

                }else if (alterStyleWin > repeatStyleWin){

                    rockChance = rockChance - (confidence/2);
                    paperChance = paperChance - confidence;

                }

            }

        }
        //Strategy Adjustment Ends

        //Diagnosis Starts
        ((TextView) findViewById(R.id.txtScore)).setText("You:" + userScore + "AI:" + aiScore);
        ((TextView) findViewById(R.id.txtResult)).setText(result + "AI chose:" + aiChoice + "r=" + random);

        ((TextView) findViewById(R.id.txtScissors)).setText("" + (100 - paperChance - rockChance));
        ((TextView) findViewById(R.id.txtPaper)).setText("" + paperChance);
        ((TextView) findViewById(R.id.txtRock)).setText("" + rockChance);

        //Forget and avoid extreme behavior
        if (alterStyleWin > repeatStyleWin + forgeLimit){
            alterStyleWin = alterStyleWin - 2;
        }

        if (repeatStyleWin > alterStyleWin + forgeLimit){
            repeatStyleWin = repeatStyleWin - 2;
        }

        if (alterStyleDraw > repeatStyleDraw + forgeLimit){
            alterStyleDraw = alterStyleDraw - 2;
        }

        if (repeatStyleDraw > alterStyleDraw + forgeLimit){
            repeatStyleDraw = repeatStyleDraw - 2;
        }

        if (alterStyleLose > repeatStyleLose + forgeLimit){
            alterStyleLose = alterStyleLose - 2;
        }

        if (repeatStyleLose > alterStyleLose + forgeLimit){
            repeatStyleLose = repeatStyleLose - 2;
        }

        if (paperChance > 70){
            paperChance = 50;
        }

        if (rockChance > 70){
            rockChance = 50;
        }

        if (paperChance < 10){
            paperChance = 15;
        }

        if (rockChance < 10){
            rockChance = 15;
        }

        if (confidence < 1){
            confidence = 2;
        }

                ((TextView) findViewById(R.id.txtRepeatWin)).setText("R:W" + repeatStyleWin);
                ((TextView) findViewById(R.id.txtRepeatLose)).setText("R:L" + repeatStyleLose);
                ((TextView) findViewById(R.id.txtAlertWin)).setText("A:W" + alterStyleWin);
                ((TextView) findViewById(R.id.txtAlertLose)).setText("A:L" + alterStyleLose);

                //Diagnosis end

        historyChoice = "rock";

    }


















    public void paper(View v){

        //Learning Start
        paperCount = paperCount + 1;
        userChoice = "paper";
        //Learning End

        //Luck Starts
        random = r.nextInt(102 - 1);
        //Luck Ends

        if (random < paperChance){

            //Game Consequences Start
            aiChoice = "paper";
            result = "Draw";
            //Game Consequences End

            //Strategy Development Start

            if (historyChoice.equals(userChoice)){

                repeatStyleDraw = repeatStyleDraw + 1;

            }else {

                alterStyleDraw = alterStyleDraw + 1;

            }
            //Strategy Development End
            //Forgetting Starts
            //Forgetting Ends


        } else {

            if (random < (paperChance + rockChance)){

                aiChoice = "rock";
                userScore = userScore + 1;
                result = "You Won!";

                historyResult = 1;

                if (historyChoice.equals(userChoice)){

                    repeatStyleWin = repeatStyleWin + 1;

                }else {

                    alterStyleWin = alterStyleWin + 1;

                }
                //Forgetting Starts
                //Forgetting Ends


            }else {

                aiChoice = "scissors";
                aiScore = aiScore + 1;
                result = "You Lost!";

                historyResult = 0;

                if (historyChoice.equals(userChoice)){

                    repeatStyleLose = repeatStyleLose + 1;

                }else {

                    alterStyleLose = alterStyleLose + 1;

                }

            }

        }

        //Strategy Adjustment Start
        if (result.equals("You Lost!")){

            if (alterStyleLose > repeatStyleLose){

                rockChance = rockChance + confidence;
                paperChance = paperChance + (confidence/2);

            }else if (repeatStyleLose > alterStyleLose){

                rockChance = rockChance - confidence;
                paperChance = paperChance - (confidence/2);

            }

        }

        else {

            if (result.equals("You Won!")){

                if (repeatStyleWin > alterStyleWin){

                    rockChance = rockChance - confidence;
                    paperChance = paperChance - (confidence/2);

                }else if (alterStyleWin > repeatStyleWin){

                    rockChance = rockChance - confidence;
                    paperChance = paperChance - (confidence/2);

                }

            }

        }
        //Strategy Adjustment Ends

        //Diagnosis Starts
        ((TextView) findViewById(R.id.txtScore)).setText("You:" + userScore + "AI:" + aiScore);
        ((TextView) findViewById(R.id.txtResult)).setText(result + "AI chose:" + aiChoice + "r=" + random);

        ((TextView) findViewById(R.id.txtScissors)).setText("" + (100 - paperChance - rockChance));
        ((TextView) findViewById(R.id.txtPaper)).setText("" + paperChance);
        ((TextView) findViewById(R.id.txtRock)).setText("" + rockChance);

        //Forget and avoid extreme behavior
        if (alterStyleWin > repeatStyleWin + forgeLimit){
            alterStyleWin = alterStyleWin - 2;
        }

        if (repeatStyleWin > alterStyleWin + forgeLimit){
            repeatStyleWin = repeatStyleWin - 2;
        }

        if (alterStyleDraw > repeatStyleDraw + forgeLimit){
            alterStyleDraw = alterStyleDraw - 2;
        }

        if (repeatStyleDraw > alterStyleDraw + forgeLimit){
            repeatStyleDraw = repeatStyleDraw - 2;
        }

        if (alterStyleLose > repeatStyleLose + forgeLimit){
            alterStyleLose = alterStyleLose - 2;
        }

        if (repeatStyleLose > alterStyleLose + forgeLimit){
            repeatStyleLose = repeatStyleLose - 2;
        }

        if (paperChance > 70){
            paperChance = 50;
        }

        if (rockChance > 70){
            rockChance = 50;
        }

        if (paperChance < 10){
            paperChance = 15;
        }

        if (rockChance < 10){
            rockChance = 15;
        }

        if (confidence < 1){
            confidence = 2;
        }

        ((TextView) findViewById(R.id.txtRepeatWin)).setText("R:W" + repeatStyleWin);
        ((TextView) findViewById(R.id.txtRepeatLose)).setText("R:L" + repeatStyleLose);
        ((TextView) findViewById(R.id.txtAlertWin)).setText("A:W" + alterStyleWin);
        ((TextView) findViewById(R.id.txtAlertLose)).setText("A:L" + alterStyleLose);

        //Diagnosis end

        historyChoice = "paper";

    }











    public void scissors(View v){

        //Learning Start
        scissorsCount = scissorsCount + 1;
        userChoice = "scissors";
        //Learning End

        //Luck Starts
        random = r.nextInt(102 - 1);
        //Luck Ends

        if (random < paperChance){

            //Game Consequences Start
            aiChoice = "paper";
            userScore = userScore + 1;
            result = "You Won";
            //Game Consequences End

            //Strategy Development Start
            historyResult = 1;
            if (historyChoice.equals(userChoice)){

                repeatStyleWin = repeatStyleWin + 1;

            }else {

                alterStyleWin = alterStyleWin + 1;

            }
            //Strategy Development End
            //Forgetting Starts
            //Forgetting Ends


        } else {

            if (random < (paperChance + rockChance)){

                aiChoice = "rock";
                aiScore = aiScore + 1;
                result = "You Lost!";

                historyResult = 0;

                if (historyChoice.equals(userChoice)){

                    repeatStyleLose = repeatStyleLose + 1;

                }else {

                    alterStyleLose = alterStyleLose + 1;

                }
                //Forgetting Starts
                //Forgetting Ends


            }else {

                aiChoice = "scissors";
                result = "Draw!";


                if (historyChoice.equals(userChoice)){

                    repeatStyleDraw = repeatStyleDraw + 1;

                }else {

                    alterStyleDraw = alterStyleDraw + 1;

                }

            }

        }

        //Strategy Adjustment Start
        if (result.equals("You Lost!")){

            if (alterStyleLose > repeatStyleLose){

                rockChance = rockChance - (confidence*(2/3));
                paperChance = paperChance + (confidence*(2/3));

            }else if (repeatStyleLose > alterStyleLose){

                rockChance = rockChance + (confidence*(2/3));
                paperChance = paperChance - (confidence*(2/3));

            }

        }

        else {

            if (result.equals("You Won!")){

                if (repeatStyleWin > alterStyleWin){

                    rockChance = rockChance + (confidence*(2/3));
                    paperChance = paperChance - (confidence*(2/3));

                }else if (alterStyleWin > repeatStyleWin){

                    rockChance = rockChance - (confidence*(2/3));
                    paperChance = paperChance + (confidence*(2/3));

                }

            }

        }
        //Strategy Adjustment Ends

        //Diagnosis Starts
        ((TextView) findViewById(R.id.txtScore)).setText("You:" + userScore + "AI:" + aiScore);
        ((TextView) findViewById(R.id.txtResult)).setText(result + "AI chose:" + aiChoice + "r=" + random);

        ((TextView) findViewById(R.id.txtScissors)).setText("" + (100 - paperChance - rockChance));
        ((TextView) findViewById(R.id.txtPaper)).setText("" + paperChance);
        ((TextView) findViewById(R.id.txtRock)).setText("" + rockChance);

        //Forget and avoid extreme behavior
        if (alterStyleWin > repeatStyleWin + forgeLimit){
            alterStyleWin = alterStyleWin - 2;
        }

        if (repeatStyleWin > alterStyleWin + forgeLimit){
            repeatStyleWin = repeatStyleWin - 2;
        }

        if (alterStyleDraw > repeatStyleDraw + forgeLimit){
            alterStyleDraw = alterStyleDraw - 2;
        }

        if (repeatStyleDraw > alterStyleDraw + forgeLimit){
            repeatStyleDraw = repeatStyleDraw - 2;
        }

        if (alterStyleLose > repeatStyleLose + forgeLimit){
            alterStyleLose = alterStyleLose - 2;
        }

        if (repeatStyleLose > alterStyleLose + forgeLimit){
            repeatStyleLose = repeatStyleLose - 2;
        }

        if (paperChance > 70){
            paperChance = 50;
        }

        if (rockChance > 70){
            rockChance = 50;
        }

        if (paperChance < 10){
            paperChance = 15;
        }

        if (rockChance < 10){
            rockChance = 15;
        }

        if (confidence < 1){
            confidence = 2;
        }

        ((TextView) findViewById(R.id.txtRepeatWin)).setText("R:W" + repeatStyleWin);
        ((TextView) findViewById(R.id.txtRepeatLose)).setText("R:L" + repeatStyleLose);
        ((TextView) findViewById(R.id.txtAlertWin)).setText("A:W" + alterStyleWin);
        ((TextView) findViewById(R.id.txtAlertLose)).setText("A:L" + alterStyleLose);

        //Diagnosis end

        historyChoice = "scissors";

    }

    public void show(View v){

        paperChance = 33;
        rockChance = 33;

        ((TextView) findViewById(R.id.txtScissors)).setText("" + (100 - paperChance - rockChance));
        ((TextView) findViewById(R.id.txtPaper)).setText("" + paperChance);
        ((TextView) findViewById(R.id.txtRock)).setText("" + rockChance);

    }

}
