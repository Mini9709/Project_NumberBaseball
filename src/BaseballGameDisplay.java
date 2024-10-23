import exceptionClass.BadInputException;

import java.util.Scanner;

public class BaseballGameDisplay {
    private int choice, tryCount;
    private boolean exit = false;

    public boolean getExit() {
        return exit;
    }
    public void start() throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("Number Baseball Game");
        System.out.println("메뉴를 선택하세요\n 1. 게임 시작\n 2. 게임 기록\n 3. 종료");

        // 게임 메뉴 선택 실패 시 오류 메시지 출력
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            throw new BadInputException("잘못된 입력입니다.");
        }

        if (choice == 1){       // 첫번째 메뉴 : 게임 시작
            BaseballGame baseballGame = new BaseballGame(3);
            tryCount = baseballGame.play();

            while (true){       // 닉네임 설정 후 기록
                System.out.print("닉네임을 입력하세요 : ");
                String nickname = sc.next();
                if (GameRecord.getRecordList().containsKey(nickname)){
                    System.out.print("중복된 닉네임입니다.");
                } else {
                    GameRecord.setRecord(tryCount, nickname);
                    break;
                }
            }

        } else if (choice == 2){    // 두번째 메뉴 : 게임 기록
            GameRecord gameRecord = new GameRecord();
            gameRecord.printRecord();
        }

        else{
            System.out.println("게임을 종료합니다");
            exit = true;
        }
    }
}
