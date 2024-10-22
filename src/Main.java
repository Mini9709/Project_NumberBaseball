public class Main {
    public static void main(String[] args) {
        BaseballGameDisplay display = new BaseballGameDisplay();
        try {
            display.start();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
