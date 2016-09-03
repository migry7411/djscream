package net.migry.common;

import java.io.File;

public class FileService {
	//public static final int sizeLimit = 10 * 1024 * 1024;
	public static final String boardSavePath = "/upload/board";
	
	public static File getFile(String path, String filename) throws Exception {
		File file = new File(path, filename);
		
		if (file.exists()){
            for(int k=0; true; k++){
                //파일명 중복을 피하기 위한 일련 번호를 생성하여
                //파일명으로 조합
                file = new File(path, "("+k+")"+filename);
                
                //조합된 파일명이 존재하지 않는다면, 일련번호가
                //붙은 파일명 다시 생성
                if(!file.exists()){ //존재하지 않는 경우
                    filename = "("+k+")"+filename;
                    break;
                }
            }
        }
    	
		// 폴더가 없을경우 생성
		File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
		
    	return file;
	}
	
	public static boolean deleteFile(String folder, String fileName) {
        boolean ret = false;
        
        if(fileName != null) { // 기존에 파일이 존재하는 경우 삭제
            File file = new File(folder, fileName);
            ret = file.delete();
        }
        
        return ret;
    }
}
