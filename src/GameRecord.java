import java.util.*;

public class GameRecord {
    private static Map<String, Integer> recordList = new LinkedHashMap<>();
    private boolean exitMenu = false;
    private static int firstScore = 99999;
    private static int secondScore = 99999;
    private static int thirdScore = 99999;
    private static String firstPlayer = "";
    private static String secondPlayer = "";
    private static String thirdPlayer = "";

    Scanner sc = new Scanner(System.in);

    public static void setRecord(int score, String nickname){
        recordList.put(nickname, score);
    }

    public static Map<String, Integer> getRecordList(){
        return recordList;
    }

    // 시행된 순서대로 게임 기록 출력 후 메뉴 선택
    public void printRecord(){
        System.out.println("< 게임 기록 보기 >");
        Iterator<String> iterator = recordList.keySet().iterator();

        for (int i = 0; i < recordList.size(); i++){
            String key = iterator.next();
            System.out.println(i+1 + "번째 게임 : " + key + " 의 점수 - " + recordList.get(key));
        }

        while(!exitMenu) {
            System.out.println("메뉴를 선택하세요.\n 1. 순위 보기\n 2. 기록 삭제\n 3. 뒤로");
            String choice;

            choice = sc.next();

            switch (choice) {
                // 1 : 순위 출력
                case "1" -> {
                    if (recordList.isEmpty()){
                        System.out.println("정보가 없습니다.");
                    } else {
                        if (firstPlayer.isEmpty() ){
                            System.out.println("1등 : 없음");
                        } else {
                            System.out.println("1등 : " + firstPlayer + " - " + firstScore);
                        }
                        if (secondPlayer.isEmpty() ){
                            System.out.println("2등 : 없음");
                        } else {
                            System.out.println("2등 : " + secondPlayer + " - " + secondScore);
                        }
                        if (thirdPlayer.isEmpty() ){
                            System.out.println("3등 : 없음");
                        } else {
                            System.out.println("3등 : " + thirdPlayer + " 의 점수 - " + thirdScore);
                        }
                    }
                }
                // 2 : 기록 삭제
                case "2" -> clearRecord();
                // 3 : 기록 삭제
                case "3" -> exitMenu = true;
                // 그 외의 문자열 입력 시 오류 메시지 출력
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 신규 유저의 기록을 받아 순위 조정
    public static void recordRank(int score, String nickname){
        if (score < thirdScore) {
            if (score < secondScore) {
                thirdScore = secondScore;
                thirdPlayer = secondPlayer;
                if (score < firstScore) {
                    secondScore = firstScore;
                    secondPlayer = firstPlayer;
                    firstScore = score;
                    firstPlayer = nickname;
                } else {
                    secondScore = score;
                    secondPlayer = nickname;
                }
            } else {
                thirdScore = score;
                thirdPlayer = nickname;
            }
        }
    }

    // 플레이 기록 및 순위 초기화
    public void clearRecord(){
        recordList.clear();
        firstScore = secondScore = thirdScore = 99999;
        firstPlayer = secondPlayer = thirdPlayer = "";
    }
}
