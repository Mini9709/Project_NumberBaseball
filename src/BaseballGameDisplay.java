import exceptionClass.BadInputException;

import java.util.Scanner;

public class BaseballGameDisplay {
    private int choice, tryCount;
    public void start() throws Exception{
        Scanner sc = new Scanner(System.in);

        System.out.println("Number Baseball Game");
        System.out.println("메뉴를 선택하세요\n 1. 게임 시작");

        try {                       // 게임 메뉴 선택 실패 시 오류 메시지 출력
            choice = sc.nextInt();
        } catch (Exception e) {
            throw new BadInputException("잘못된 입력입니다.");
        }

        if (choice == 1){       // 첫번째 메뉴 : 게임 시작
            BaseballGame baseballGame = new BaseballGame(3);
            tryCount = baseballGame.play();
        }

        else{               // 다른 메뉴 추후에 구현
            System.out.println("종료합니다");
        }
    }
}
