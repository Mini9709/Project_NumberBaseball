import exceptionClass.BadInputException;

import java.util.*;

public class BaseballGame {
    private Set<Integer> baseballNumber = new LinkedHashSet<>();
    private int difficulty, playCount, strikeCount, ballCount;
    private static final String NUMBER_REG = "^[1-9]*$";

    Scanner sc = new Scanner(System.in);

    public BaseballGame(int difficulty) {
        this.difficulty = difficulty;
        while (baseballNumber.size() < difficulty) {
            int randomNumber = new Random().nextInt(10);
            baseballNumber.add(randomNumber);
        }
    }

    public int play() {
        while (true) {
            // 1. 유저에게 입력값을 받음
            // 2. 올바른 입력값을 받았는지 검증
            // 3. 게임 진행횟수 증가
            // 4. 스트라이크 개수 계산
            // 5. 정답여부 확인, 만약 정답이면 break 를 이용해 반복문 탈출
            // 6. 볼 개수 계산
            // 7. 힌트 출력

            System.out.print("1~9 의 숫자 "+difficulty+"개를 입력하세요 : ");
            String input = sc.nextLine();
            System.out.println(baseballNumber+ " (테스트용 정답 출력)"); // 테스트용 정답 출력

            try {
                boolean validInput = validateInput(input); // 수정 예정
            } catch (BadInputException e) {
                System.out.println(e.getMessage());
            }

            playCount++;

            strikeCount = countStrike(input);
            ballCount = countBall(input);
            if (strikeCount == 3){
                System.out.println("정답입니다!!");
                break;
            }
            else {
                if (strikeCount == 0 && ballCount == 0) {
                    System.out.println("Out");
                } else {
                    System.out.println(strikeCount + " Strike " + ballCount + " Ball");
                }
            }
        }
        // 게임 진행횟수 반환
        return playCount;
    }

    protected boolean validateInput(String input) throws BadInputException {
        if (!input.matches(NUMBER_REG)) {
            throw new BadInputException("잘못된 입력입니다. 1~9 의 숫자를 입력해주세요.");
        } else if (input.length() != difficulty){
            throw new BadInputException(difficulty + "자리 숫자를 입력해주세요.");
        } else {
            Set<Character> checkDuplicatedNumber = new HashSet<>();
            for (int i = 0; i < input.length(); i++){
                checkDuplicatedNumber.add(input.charAt(i));
            }
            System.out.println(checkDuplicatedNumber);
            if (checkDuplicatedNumber.size() != difficulty){
                throw new BadInputException("중복되지 않은 숫자를 입력해주세요.");
            } else{
                return true;
            }
        }
    }

    // 입력값과 비교값의 숫자 밑 자릿수 대조 Strike 메서드
    private int countStrike(String input) {
        int count = 0;
        Iterator<Integer> iterator = baseballNumber.iterator();
        for (int i = 0; i < input.length(); i++) {
            if (Integer.parseInt(input.substring(i, i + 1)) == iterator.next()) {
                count++;
            }
        }
        return count;
    }

    // 입력값과 비교값의 숫자 밑 자릿수 대조 Ball 메서드
    private int countBall(String input) {
        int count = 0;
        Iterator<Integer> iterator = baseballNumber.iterator();
        for (int i = 0; i < input.length(); i++) {
            if (baseballNumber.contains(Integer.parseInt(input.substring(i, i + 1))) && Integer.parseInt(input.substring(i, i + 1)) != iterator.next()) {
                count++;
            }
        }
        return count;
    }
}
