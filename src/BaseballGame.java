import exceptionClass.BadInputException;

import java.util.*;

public class BaseballGame {
    private int difficulty;
    private int tryCount;
    private int strikeCount;
    private int ballCount;
    private static final String NUMBER_REG = "^[1-9]*$";

    private Set<Integer> baseballNumber = new LinkedHashSet<>();
    Scanner sc = new Scanner(System.in);

    //난이도에 따라 랜덤숫자 생성
    public BaseballGame(int difficulty) {
        this.difficulty = difficulty;

        while (baseballNumber.size() < difficulty) {
            int randomNumber = new Random().nextInt(1,10);
            baseballNumber.add(randomNumber);
        }
    }

    public int play() {
        while (true) {
            // 유저에게 입력값을 받음
            System.out.print("1~9 의 숫자 "+difficulty+"개를 입력하세요 : ");
            String input = sc.nextLine();
            System.out.println(baseballNumber+ " (테스트용 정답 출력)"); // 테스트용 정답 출력

            // 올바른 입력값을 받았는지 검증
            try {
                validateInput(input);
            } catch (BadInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // 게임 진행횟수 증가
            tryCount++;

            // 스트라이크 및 볼 개수 계산
            strikeCount = countStrike(input);
            ballCount = countBall(input);

            // 5. 정답여부 확인 및 힌트 출력
            if (strikeCount == difficulty) {
                System.out.println("정답입니다!!");
                break;
            } else {
                if (strikeCount == 0 && ballCount == 0) {
                    System.out.println("Out");
                } else {
                    System.out.println(strikeCount + " Strike " + ballCount + " Ball");
                }
            }
        }

        // 게임 진행횟수 반환
        return tryCount;
    }

    // 유효값 여부 확인 메서드 : 예외처리로 수정함
    protected void validateInput(String input) throws BadInputException {
        if (!input.matches(NUMBER_REG)) {
            throw new BadInputException("잘못된 입력입니다. 1~9 의 숫자를 입력해주세요.");
        } else if (input.length() != difficulty) {
            throw new BadInputException(difficulty + "자리 숫자를 입력해주세요.");
        } else {
            Set<Character> checkDuplicatedNumber = new HashSet<>();

            for (int i = 0; i < input.length(); i++) {
                checkDuplicatedNumber.add(input.charAt(i));
            }

            if (checkDuplicatedNumber.size() != difficulty) {
                throw new BadInputException("중복되지 않은 숫자를 입력해주세요.");
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
            if (Integer.parseInt(input.substring(i, i + 1)) != iterator.next() && baseballNumber.contains(Integer.parseInt(input.substring(i, i + 1)))) {
                count++;
            }
        }

        return count;
    }
}
