package com.grasset.fernando.tp3_oo_lependu;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

/**
 * Created by eleves on 2018-08-29.
 */

public class GameThread extends Thread {

    private static String serverIP = "10.0.2.2";
    private static final int PORT = 9090;

    private GameActivity gameActivity;
    private BlockingQueue<String> blockingQueue;
    private TextView textView;
    private static Integer countFails = 0;

    public GameThread(GameActivity gameActivity, BlockingQueue<String> blockingQueue) {
        this.textView = textView;
        this.blockingQueue = blockingQueue;
        this.gameActivity = gameActivity;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(serverIP, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("AFTER WHILE");

            while (true) {
                String mask = in.readLine();
                gameActivity.setText(textView, mask);

                String letter = blockingQueue.take();
                out.println(letter);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}