import java.util.*;

public class GameRecord {
    private static Map<String, Integer> recordList = new HashMap<>();
    private boolean exitMenu = false;
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
            System.out.println("메뉴를 선택하세요\n 1. 순위 보기\n 2. 기록 삭제\n 3. 뒤로");
            String choice;

            choice = sc.next();

            switch (choice) {
                case "1" -> {
                    if (recordList.isEmpty()){
                        System.out.println("정보가 없습니다");
                    } else {
                        for (int i = 1; i < 4; i++) { System.out.println("Test"); } //순위 출력
                        // int, String 변수 3개 생성(1등,2등,3등)
                        // map value를 돌며 비교 후 저장
                        // 반복문이 종료 되면, 3등 까지 출력
                    }
                }
                case "2" -> clearRecord();
                case "3" -> exitMenu = true;
                default -> System.out.println("잘못된 입력입니다");
            }
        }
    }

    public void clearRecord(){
        recordList.clear();
    }
}
