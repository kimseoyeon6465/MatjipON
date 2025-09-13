package matjipON;

import matjipDAO.KioskMenuNameDAO;

public class Kioskmenu {

    public static String[] getMenuByMatjipId(int matjipId) {
        try {
            KioskMenuNameDAO dao = new KioskMenuNameDAO();
            return dao.getMenuName(matjipId);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0]; // 예외 발생 시 빈 배열 반환
        }
    }
}
