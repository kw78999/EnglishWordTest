package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\hblee.DI-SOLUTION\\Desktop\\단어.csv";
        File csv = new File(path);
        BufferedReader br = null;
        BufferedReader dap = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { 
                String[] lineArr = line.split(","); 
                System.out.println(lineArr[0]);
                String dapStr = dap.readLine();
                if(lineArr[1].trim().equals(dapStr)){
                    System.out.println("---------------------------정답");
                    System.out.println();
                }else{
                    System.out.println("---------------------------틀렸습니다.");
                    System.out.println("정답 : " + lineArr[1].trim());
                    System.out.println("정답으로 인정하시겠습니까? 정답인정 : 1     오답인정 : 2");
                    String check = dap.readLine();
                    if(check.equals("1")){
                        System.out.println("---------------------------정답");
                    }else{
                        System.out.println("---------------------------틀렸습니다.");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
