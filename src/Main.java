public class Main {
    public static void main(String[] args) {

        BaseballGameDisplay display = new BaseballGameDisplay();

        while(!display.getExit()){
            try {
                display.start();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
