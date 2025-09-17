package com.sist.ex0917_bbs.util;

import java.io.File;

public class FileRenameUtil {
    
    public static String checkSameFileName(String fileName, String path){
        //인자인 fileName에서 확장자를 뺀 파일명 가려내자!
        // 우선 "."의 위치를 알아내야 한다.
        int dotIndex = fileName.lastIndexOf("."); // test.txt -> 4

        String f_name = fileName.substring(0, dotIndex); // test
        String suffix = fileName.substring(dotIndex); // .txt

        // 전체경로(절대경로+파일명)
        // String saveFilePath = path +"/"+ fileName;
        String saveFilePath = 
            path + System.getProperty("file.separator") + fileName;

        // 위 경로를 가지고 전체경로로 활용하여 파일객체 생성
        File f = new File(saveFilePath);

        // 파일이 이미 있다면 파일명 뒤에 숫자를 붙이기 위해 변수를 하나 준비하자
        int idx = 1;

        // 동일한 이름의 파일이 존재하는지 체크
        while(f.exists()) {
            // 파일이 이미 존재하고 있는 파일일 때
            StringBuffer sb = new StringBuffer();
            sb.append(f_name); // test
            sb.append(idx++);
            sb.append(suffix); //.txt

            fileName = sb.toString(); // test1.txt

            // 변경된 파일명으로 다시 전체경로를 구성
            saveFilePath = 
                path + System.getProperty("file.separator") + fileName;
            
            // 변경된 파일명으로 파일객체 생성
            f = new File(saveFilePath);
        }

        return fileName;
    }
}
