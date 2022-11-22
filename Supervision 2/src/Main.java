import asd58.suporun.Runner;

public class Main {
    public static void main(String[] args) {
        Runner.Run("Question 4", Question4::Run);
        Runner.Run("Question 5", () -> {
            var ninjaEmployee = new Question5.NinjaEmployee();
            ninjaEmployee.SetName("Joe Bloggs");
            Runner.LogValue("GetName()", ninjaEmployee.GetName());
            ninjaEmployee.DoStuff();
        });
        Runner.Run("Question 7", Question7::testOutput);
        Runner.Run("Question 9", () -> {
            var gm = new Question9.GameManager();
            gm.MainMenu();
        });
    }
}