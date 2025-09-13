package matjipChat;
import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import loginUser.Login_main;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class ChatBotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    // new style
    private JTextPane chatPane;
    private StyledDocument doc;
    private Style leftStyle;
    private Style rightStyle;
    
    
    
    
    public ChatBotGUI() {
        setTitle("자바 챗봇");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 채팅창
        /*
        chatArea = new JTextArea(); // 객체를 생성하는 것이 아닌 출력내용 기록물을 만들도록 함.
        chatArea.setEditable(false); // 안에 있는 채팅 내용을 수정할 수 없게 만듬. 내장 함수 사용
        chatArea.setLineWrap(true); // 한줄이 너무 길 경우 내려오게 만드는 내장 함수.
        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        */
        
        
       //new style 채팅창
        chatPane = new JTextPane(); //JTextPane 지정창
        chatPane.setEditable(false); // 안에 있는 채팅 내용을 수정할 수 없게 만듬. 내장 함수 사용
        chatPane.setFont(new Font("맑은 고딕", Font.PLAIN, 14)); // 폰트 구성
        doc = chatPane.getStyledDocument(); // chatPane 내장함수 사용
        
        leftStyle = doc.addStyle("left", null); //left 지정
        StyleConstants.setAlignment(leftStyle, StyleConstants.ALIGN_LEFT); //left 위지 지정
        StyleConstants.setFontSize(leftStyle, 14); // 폰트 사이즈 지정
        StyleConstants.setForeground(leftStyle, Color.BLUE); // 폰트 색깔 지정
        

        rightStyle = doc.addStyle("right", null); // right 지정
        StyleConstants.setAlignment(rightStyle, StyleConstants.ALIGN_RIGHT); // right 위치 지정
        StyleConstants.setFontSize(rightStyle, 14); // 오른쪽 폰트 사이즈 지정
        StyleConstants.setForeground(rightStyle, new Color(0, 128, 0)); // 초록색 폰트 사용

        add(new JScrollPane(chatPane), BorderLayout.CENTER); // 스크롤 추가 
        
        
        
        // 입력창 + 버튼
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField(); // 채팅창 선언
        sendButton = new JButton("전송"); //전송 버튼

        inputPanel.add(inputField, BorderLayout.CENTER); // 채팅 입력창 위치 지정
        inputPanel.add(sendButton, BorderLayout.EAST); // 전송 버튼 위치 지정
        add(inputPanel, BorderLayout.SOUTH); // 입력 panel 위치 지정

        // 이벤트 핸들링, 채팅 입력시 채팅 로그처럼 남기는 장치
        sendButton.addActionListener(e -> {
			try {
				sendMessage();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        inputField.addActionListener(e -> {
			try {
				sendMessage();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}); // Enter 키 처리

       
 //sendMessage()가 아니라, ChatBotGUI() 생성자 안에서 appendMessage(...)를 호출해야 프로그램 실행 즉시 인사 메시지가 출력됨
 // (추가) 창이 뜨자마자 인사말 보이게
        appendMessage("챗봇: 안녕하세요! MatjipOn 챗봇입니다.\n무엇을 도와드릴까요?\n궁금한 지역명이나 음식 키워드를 입력해보세요!\n예: \"맛집\", \"맛집 랭킹\", \"로그인\", \"지금 시간\" 등등\n지금 바로 도와드릴게요!", leftStyle);
         
        setVisible(true); // 시각화
    }

    private void sendMessage() throws ClassNotFoundException, SQLException {
        String input = inputField.getText().trim(); // 입력받은 걸 변수로 받음.
        if (input.isEmpty()) return; // 아무것도 입력안할시 return
        
        
	  	chatPane.setBackground(Color.WHITE);
        chatPane.setOpaque(true);
        
     // 사용자 말풍선 스타일 (오른쪽 정렬 + 배경 + 여백)
        rightStyle = doc.addStyle("right", null);  //Style 설정은 한 번만, 생성자에서!
        StyleConstants.setAlignment(rightStyle, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(rightStyle, 14);
        StyleConstants.setForeground(rightStyle, Color.BLACK);
        StyleConstants.setBackground(rightStyle,new Color(220, 248, 198)); // 연한 초록
        StyleConstants.setLeftIndent(rightStyle, 50);   // 왼쪽 여백
        StyleConstants.setRightIndent(rightStyle, 10);  // 오른쪽 여백
        StyleConstants.setSpaceAbove(rightStyle, 5);    // 위쪽 간격
        StyleConstants.setSpaceBelow(rightStyle, 5);    // 아래쪽 간격

        // 챗봇 말풍선 스타일 (왼쪽 정렬 + 배경 + 여백)
        leftStyle = doc.addStyle("left", null);
        StyleConstants.setAlignment(leftStyle, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontSize(leftStyle, 14);
        StyleConstants.setForeground(leftStyle, Color.BLACK);
        StyleConstants.setBackground(leftStyle, new Color(255, 223, 186)); // 회색 말풍선 느낌
        StyleConstants.setLeftIndent(leftStyle, 10);
        StyleConstants.setRightIndent(leftStyle, 50);
        StyleConstants.setSpaceAbove(leftStyle, 5);
        StyleConstants.setSpaceBelow(leftStyle, 5);
        
        appendMessage("나: " + input, rightStyle); // 오른쪽 정렬로 사용자 메시지 출력
        String response = getResponse(input);
        appendMessage("챗봇: " + response + "\n", leftStyle); // 왼쪽 정렬로 챗봇 응답 출력
        
   

        // 기존 스타일
        //chatArea.append("나: " + input + "\n"); //누가 보냈는지 서두에 표현
        //String response = getResponse(input);
        //chatArea.append("챗봇: " + response + "\n\n");
        // 기존스타일 끝
        inputField.setText(""); // 입력창을 초기화 시키기 위해 채팅 입력시 비워주는 역할
    }
    
 // 스타일에 따라 메시지 정렬 출력( 새로운 스타일 )
    private void appendMessage(String message, Style style) { 
        try {
            doc.insertString(doc.getLength(), message + "\n", style); // jtextpane에서 사용하는 styledDocument의 객체
            // insertString: 문서에 문자열을 삽입하는 함수.
          //doc.getLength : 문서의 마지막 위치를 의미 가장 끝에 추가, message를 출력하고 줄바꿈까지 함. Style : 왼쪽, 오른쪽 정렬
            doc.setParagraphAttributes(doc.getLength() - message.length(), message.length(), style, false); 
            // 문단의 속성을 설정하는 함수, 텍스트 구간 설정/메세지의 길이/ 스타일/ 기존스타일과 섞지 않고 이 스타일만 적용하겠다.(false)
            // true로 적용할시 새 스타일을 덮어 씌우는데 여기서는 스타일이 하나이므로 차이 없음.
            // doc.getLength() - messageLength : 글자수에 따라 문자 시작지점을 계산해야하므로 message입력길이와 전체 길이를 비교.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 챗봇의 답변이 달리는 블록
    private String getResponse(String input) throws ClassNotFoundException, SQLException { // 반응은 String 값으로 리턴하여 사용자가 입력한 input 에 따라 표현다도록 만듬.
        input = input.toLowerCase();
        
        if (input.contains("안녕")) { //특정 단어가 포함될시 답변을 출력하도록함.
            return "안녕하세요!";
        } else if (input.contains("이름")) { //단 앞에 있는 단어를 포함하고 뒤에 단어도 있다면 / 앞에 단어만 표현이됨 /이것은 보완사항 내 능력으로 불가
            return "저는 MatjipON 챗봇이에요.";
        } else if (input.contains("시간")) {
            return java.time.LocalTime.now().toString();
        } else if (input.contains("날씨")) {
            return "날씨는 직접 확인하셔야 해요 😅";
        } else if (input.contains("현황")) {
        	ChatBotStore.StoreInfo();
        	return "가게 정보창을 열겠습니다.";
        } else if (input.contains("맛집")) {
        	ChatBotStore.StoreInfo();
        	return "가게 정보창을 열겠습니다.";
        } else if (input.contains("로그인")) {			//(추가) 로그인 창 열고 챗봇 창 닫기
            try {
                Login_main frame = new Login_main();
                frame.setVisible(true);
                this.dispose(); 						// 현재 ChatBotGUI 창 닫기
            } catch (Exception e) {
                e.printStackTrace();
                return "로그인창 열기에 실패했습니다.";
            }

            return "로그인창을 열겠습니다.";
        }												//로그인 창 끝
        else {
            return "잘 이해하지 못했어요.";
        }
    }

    public static void main(String[] args) {
        new ChatBotGUI();
    }
}
