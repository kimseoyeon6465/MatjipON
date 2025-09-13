package matjipON;

public class KioskImage {
// 맛집 아이디 넘겨서 처리 맛집 아이디는 kiosk.java에서 넘어옴
	
    public static String getFolderNameByMatjipId(int matjipId) {
        switch (matjipId) {
            case 101: return "Astore";
            case 102: return "Bstore";
            case 103: return "Cstore";
            case 104: return "Dstore";
            case 105: return "Estore";
            default: return "Astore"; // 기본값 처리
        }
    }

    public static String[] getImagePaths(int matjipId) {
        String folderName = getFolderNameByMatjipId(matjipId);
        String basePath = "src/" + folderName + "/";
        int imageCount = 8;
        String[] paths = new String[imageCount];

        for (int i = 0; i < imageCount; i++) {
            paths[i] = basePath + (i + 1) + ".jpg";
        }
        return paths;
    }
}


       
