package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static BufferedReader br = null;
    static String path = "C:\\Users\\hblee.DI-SOLUTION\\Desktop\\EnglishWordTest\\test.csv";
    static File csv = new File(path);
    static BufferedReader dap = new BufferedReader(new InputStreamReader(System.in));
    static String line = "";
    static ArrayList<List<String>> list = new ArrayList();
    static int success = 0;
    static int fail = 0;
    static int cnt = 0;
    static boolean[] distinctWordFlag ;
    static boolean[] failCheck ;
    static boolean[] successCheck ;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
            List<String> aLine = new ArrayList<String>();
            String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
            aLine.add(lineArr[0]);
            aLine.add(lineArr[1]);
            list.add(aLine);
        }
        failCheck = new boolean[list.size()];
        distinctWordFlag = new boolean[list.size()];
        successCheck = new boolean[list.size()];

        //level2 - 순서 섞기
        level2(list.size());
        //level3 - 순서 섞기 / 단어 해석 랜덤출제
        //level3();
    }


    public static void level2(int failSize){
        success=0;
        fail=0;
        cnt=0;
        for (int i = 0 ; i < distinctWordFlag.length; i++){
            distinctWordFlag[i] = false;
            failCheck[i] = false;
        }

        try {
            while (true){
                boolean whileCheck = true;
                int i = new Random().nextInt(list.size());

                if(distinctWordFlag[i] == false && successCheck[i] == false){
                    distinctWordFlag[i]=true;
                    cnt++;
                }else{
                    continue;
                }

                System.out.println(list.get(i).get(0));
                String dapStr = dap.readLine();

                if(list.get(i).get(1).trim().equals(dapStr)){
                    System.out.println("---------------------------정답");
                    success++;
                    successCheck[i] = true;
                }else{
                    System.out.println("---------------------------틀렸습니다.");
                    System.out.println("정답 : " + list.get(i).get(1).trim());
                    System.out.println("정답으로 인정하시겠습니까? 정답인정 : 1     오답인정 : 2");
                    String check = dap.readLine();
                    if(check.equals("1")){
                        System.out.println("---------------------------정답");
                        success++;
                        successCheck[i] = true;
                    }else{
                        System.out.println("---------------------------틀렸습니다.");
                        fail++;
                        failCheck[i] = true;
                    }
                }

                for (int k=0; k<list.size(); k++){
                    if(successCheck[k]==false && failCheck[k]==false){
                        whileCheck=false;
                        break;
                    }
                }
                if(whileCheck==true)break;
                System.out.println("진행률 : "+cnt + " / " + failSize);
                System.out.println();
            }
            boolean flag=true;
            for (int i = 0; i<successCheck.length; i++){
                if(successCheck[i]==false){
                    flag=false;
                }
            }
            int failSizeInteger=0;
            for (int i = 0; i<failCheck.length; i++){
                if(failCheck[i]==true){
                    failSizeInteger++;
                }
            }
            end(failSize);
            if(flag==false){
                System.out.println();
                System.out.println();
                System.out.println("준비되었으면 무엇이든 입력해주세요.");
                if(dap.readLine()!=null)
                    level2(failSizeInteger);
            }else{
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void level3(){
        try {

            while (true){
                int r = new Random().nextInt(2);
                int r2;
                if(r == 0 ){
                    r2 = 1;
                }else{
                    r2 = 0;
                }

                int i = new Random().nextInt(list.size());

                if(distinctWordFlag[i] == false){
                    distinctWordFlag[i]=true;
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
            end(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void end(int failSize){
        System.out.println();
        System.out.println("------Fail List 입니다.-------");
        for (int i = 0; i < list.size(); i++){
            if(failCheck[i] == true){
                System.out.print(list.get(i).get(0) + " - ");
                System.out.println(list.get(i).get(1));
            }
        }
        System.out.println();
        System.out.println("점수 : "+success + " / " + failSize  + "   틀린 개수 : " + fail + " / " + failSize);
    }
}
