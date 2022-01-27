package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static BufferedReader br = null;
    static String path = "C:\\Users\\hblee.DI-SOLUTION\\Desktop\\EnglishWordTest\\2022_01_26.csv";
    static File csv = new File(path);
    static BufferedReader dap = new BufferedReader(new InputStreamReader(System.in));
    static String line = "";
    static ArrayList<List<String>> list = new ArrayList();
    static int success = 0;
    static int fail = 0;
    static int cnt = 0;
    static boolean[] flag ;
    static boolean[] failCheck ;

    public static void main(String[] args) {
        //level1 - 순차 출제
        //level1();
        //level2 - 순서 섞기
        //level2();
        //level3 - 순서 섞기 / 단어 해석 랜덤출제
        level3();
    }

    public static void level1() {
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { 
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); 
                aLine.add(lineArr[0]);
                aLine.add(lineArr[1]);
                list.add(aLine);
            }
            failCheck = new boolean[list.size()];

            int i = 0;
            while (true){
                cnt++;
                System.out.println(list.get(i).get(0));
                String dapStr = dap.readLine();

                if(list.get(i).get(1).trim().equals(dapStr)){
                    System.out.println("---------------------------정답");
                    success++;
                }else{
                    System.out.println("---------------------------틀렸습니다.");
                    System.out.println("정답 : " + list.get(i).get(1).trim());
                    System.out.println("정답으로 인정하시겠습니까? 정답인정 : 1     오답인정 : 2");
                    String check = dap.readLine();
                    if(check.equals("1")){
                        System.out.println("---------------------------정답");
                        success++;
                    }else{
                        System.out.println("---------------------------틀렸습니다.");
                        fail++;
                        failCheck[i] = true;
                    }
                }
                if(cnt == list.size())break;
                System.out.println("진행률 : "+cnt + " / " + list.size());
                System.out.println();
                i++;
            }
            end();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void level2(){
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); 
                aLine.add(lineArr[0]);
                aLine.add(lineArr[1]);
                list.add(aLine);
            }
            failCheck = new boolean[list.size()];
            flag = new boolean[list.size()];

            while (true){

                int i = new Random().nextInt(list.size());

                if(flag[i] == false){
                    flag[i]=true;
                    cnt++;
                }else{
                    continue;
                }

                System.out.println(list.get(i).get(0));
                String dapStr = dap.readLine();

                if(list.get(i).get(1).trim().equals(dapStr)){
                    System.out.println("---------------------------정답");
                    success++;
                }else{
                    System.out.println("---------------------------틀렸습니다.");
                    System.out.println("정답 : " + list.get(i).get(1).trim());
                    System.out.println("정답으로 인정하시겠습니까? 정답인정 : 1     오답인정 : 2");
                    String check = dap.readLine();
                    if(check.equals("1")){
                        System.out.println("---------------------------정답");
                        success++;
                    }else{
                        System.out.println("---------------------------틀렸습니다.");
                        fail++;
                        failCheck[i] = true;
                    }
                }
                if(cnt == list.size())break;
                System.out.println("진행률 : "+cnt + " / " + list.size());
                System.out.println();
            }
            end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void level3(){
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { 
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); 
                aLine.add(lineArr[0]);
                aLine.add(lineArr[1]);
                list.add(aLine);
            }
            failCheck = new boolean[list.size()];
            flag = new boolean[list.size()];

            while (true){
                int r = new Random().nextInt(2);
                int r2;
                if(r == 0 ){
                    r2 = 1;
                }else{
                    r2 = 0;
                }

                int i = new Random().nextInt(list.size());

                if(flag[i] == false){
                    flag[i]=true;
                    cnt++;
                }else{
                    continue;
                }

                System.out.println(list.get(i).get(r).trim());
                String dapStr = dap.readLine();

                if(list.get(i).get(r2).trim().equals(dapStr)){
                    System.out.println("---------------------------정답");
                    success++;
                }else{
                    System.out.println("---------------------------틀렸습니다.");
                    System.out.println("정답 : " + list.get(i).get(r2).trim());
                    System.out.println("정답으로 인정하시겠습니까? 정답인정 : 1     오답인정 : 2");
                    String check = dap.readLine();
                    if(check.equals("1")){
                        System.out.println("---------------------------정답");
                        success++;
                    }else{
                        System.out.println("---------------------------틀렸습니다.");
                        fail++;
                        failCheck[i] = true;
                    }
                }
                if(cnt == list.size())break;
                System.out.println("진행률 : "+cnt + " / " + list.size());
                System.out.println();
            }
            end();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void end(){
        System.out.println();
        for (int i = 0; i < list.size(); i++){
            if(failCheck[i] == true){
                System.out.print(list.get(i).get(0) + " - ");
                System.out.println(list.get(i).get(1));
            }
        }
        System.out.println();
        System.out.println("점수 : "+success + " / " + list.size()  + "   틀린 개수 : " + fail + " / " + list.size());
    }
}
