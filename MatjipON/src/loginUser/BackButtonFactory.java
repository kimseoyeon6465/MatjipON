package loginUser;

//BackButtonFactory.java
import javax.swing.*;
import java.awt.event.*;

public class BackButtonFactory {
 public static JButton createBackButton(JFrame currentFrame, JFrame previousFrame) {
     JButton backButton = new JButton("← 뒤로가기");

     backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             previousFrame.setVisible(true);  // 이전 창 보여주기
             currentFrame.dispose();         // 현재 창 닫기
         }
     });

     return backButton;
 }
}