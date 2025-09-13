package loginUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	
	// 입력 문자열을 SHA-256으로 암호화하여 해시 값을 반환하는 메서드
	 public String encrypt(String text) throws NoSuchAlgorithmException {
		// SHA-256 알고리즘의 인스턴스 생성
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(text.getBytes()); // 입력 문자열의 바이트 배열을 업데이트하여 해시 값을 계산
	        								// 계산된 해시 값을 16진수 문자열로 변환하여 반환
	        return bytesToHex(md.digest());
	    }

	    private String bytesToHex(byte[] bytes) {		 // 바이트 배열을 16진수 문자열로 변환하는 메서드
	        StringBuilder builder = new StringBuilder();
	        for (byte b : bytes) {				// 바이트 값을 16진수 문자열로 변환하여 StringBuilder에 추가
	            builder.append(String.format("%02x", b));
	        }
	        return builder.toString();				 // StringBuilder의 내용을 문자열로 반환
	    }

	}