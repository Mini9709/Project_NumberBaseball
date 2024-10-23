import exceptionClass.BadInputException;

import java.util.Scanner;

public class BaseballGameDisplay {
    private int choice, tryCount, difficulty;
    private String nickname;
    private boolean exit = false;

    public boolean getExit() {
        return exit;
    }

    public void start() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("<숫자 야구 게임>");
        System.out.println("메뉴를 선택하세요.\n 0. 난이도 설정\n 1. 게임 시작\n 2. 게임 기록\n 3. 종료");

        // 게임 메뉴 선택 실패 시 오류 메시지 출력
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            throw new BadInputException("잘못된 입력입니다.");
        }

        switch(choice) {
            // 0번째 메뉴 : 난이도 설정
            case 0 :
                System.out.println("설정 가능한 자릿수는 3~5 입니다.");
                System.out.print("설정하고자 하는 자릿수를 입력하세요 : ");
                try {
                    difficulty = sc.nextInt();
                } catch (Exception e) {
                    throw new BadInputException("잘못된 입력입니다. 숫자를 입력해주세요.\n");
                }

                if (difficulty < 3 || difficulty > 5) {
                    System.out.println("설정 가능한 자릿수가 아닙니다.\n");
                    break;

                } else {
                    System.out.println(difficulty + "자릿수 난이도로 설정되었습니다.");
                }

            // 첫번째 메뉴 : 게임 시작
            case 1 :
                System.out.println("게임을 시작합니다.");
                BaseballGame baseballGame = new BaseballGame(difficulty);
                tryCount = baseballGame.play();

                // 닉네임 설정 후 기록
                while (true) {
                    System.out.print("닉네임을 입력하세요 : ");
                    nickname = sc.next();
                    if (GameRecord.getRecordList().containsKey(nickname)) {
                        System.out.print("중복된 닉네임입니다. 다른 ");
                    } else {
                        GameRecord.setRecord(tryCount, nickname);
                        break;
                    }
                }

                // 순위 조정
                GameRecord.recordRank(tryCount, nickname);
                break;

            // 두번째 메뉴 : 게임 기록
            case 2 :
                GameRecord gameRecord = new GameRecord();
                gameRecord.printRecord();
                break;

            // 세번째 메뉴 : 종료
            case 3 :
                System.out.println("게임을 종료합니다."); exit = true;
                break;

            default :
                System.out.println("올바른 메뉴가 아닙니다. 다시 입력하세요.");
        }
    }
}
