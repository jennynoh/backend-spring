package net.kdigital.board.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
	/* 호출ed by BoardService 
	 * 1. 서버에 디렉토리가 없으면 디렉토리 생성
	 * 2. 원본 파일명을 꺼내서 저장 파일명을 새롭게 생성 (랜덤값 or milisecond...)
	 * 3. 디렉토리에 파일 저장 작업 
	 * 4. DB에 저장할 수 있도록 저장파일명 반환 
	 */
	public static String saveFile(MultipartFile uploadFile, String uploadPath) {
		// 1) 파일이 첨부되면 디렉토리가 있는지 확인 
		if(!uploadFile.isEmpty()) {
			File path = new File(uploadPath);
			// 디렉토리 없으면 생성 
			if(!path.isDirectory()) {
				path.mkdirs();
			}
		}
		
		// 2) savedFileName 생성 
		// 원본파일명 추출
		String originalFileName = uploadFile.getOriginalFilename();
		
		// 랜덤값 발생 
		String uuid = UUID.randomUUID().toString();
		
//		String savedFileName = originalFileName.split(".")[0]+uuid+originalFileName.split(".")[1];
		
		// other ver. 
		String filename;
		String ext;
		String savedFileName;
		int position = originalFileName.lastIndexOf("."); // 확장자가 없으면 -1 반환 
		
		if(position == -1) ext="";
		else ext="."+originalFileName.substring(position+1);
		filename = originalFileName.substring(0,position);
		savedFileName = filename+"_"+uuid+ext;
		
		
		// 3) 서버 저장공간에 파일 저장 
		File serverFile = null;
		// 파일이 저장될 경로가 객체로 생성됨 
		serverFile = new File(uploadPath + "/" + savedFileName);
		
		try {
			uploadFile.transferTo(serverFile);
		} catch (Exception e) {
			savedFileName = null;
			e.printStackTrace();
		}
		
		
		return savedFileName; // 저장파일명 반환 
	}
	
	public static boolean deleteFile(String fullPath) {
		boolean result = false;          
		
		File delFile = new File(fullPath);
		if(delFile.isFile()) {
			result = delFile.delete();
		}
		return result;
	}
	

	
}



