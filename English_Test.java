package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\Users\\hblee.DI-SOLUTION\\Desktop\\EnglishWordTest\\단어.csv";
        File csv = new File(path);
        BufferedReader br = null;
        BufferedReader dap = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        ArrayList<List<String>> list = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine.add(lineArr[0]);
                aLine.add(lineArr[1]);
                list.add(aLine);
            }
            boolean[] flag = new boolean[list.size()];
            boolean[] failCheck = new boolean[list.size()];
            int cnt = 0;
            int success = 0;
            int fail = 0;

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
            System.out.println();
            for (int i = 0; i < list.size(); i++){
                if(failCheck[i] == true){
                    System.out.print(list.get(i).get(0) + " - ");
                    System.out.println(list.get(i).get(1));
                }
            }
            System.out.println();
            System.out.println("점수 : "+success + " / " + list.size()  + "   틀린 개수 : " + fail + " / " + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
