import java.util.*;
public class SimulationManager {
    long currentSimulatedTime = 0L;
    private Random random;
    int minutesPerSimulationStep = 60;


    public SimulationManager(){
        this.random = new Random(); 
    }
    public void advanceTime(DataManager DM){
        currentSimulatedTime += minutesPerSimulationStep;
        simulateWorkout(DM);
        DM.updateMembers();
    }
    public void stopSimulation(DataManager DM){
        List<Member> allMembers = DM.members;
        currentSimulatedTime += minutesPerSimulationStep;
        for(Member member:allMembers){
            if(member.getActiveStatus()==true){
                long durationOfWorkout = currentSimulatedTime - member.getLastAcitivityTimestamp();
                member.increaseMemberHoursSpent((int)durationOfWorkout);
                int caloriesBurnt = (int) (durationOfWorkout * (random.nextInt(5) + 3)); 
                member.increaseMemberCaloriesBurnt(caloriesBurnt);
                System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_MAGENTA, "  - " + member.getMemberName() + " (ID: " + member.getMemberID() + ") ended workout after " + durationOfWorkout + " mins. Burned " + caloriesBurnt + " cal."));
                member.setActive(false);
            }
        }
    }
    public void simulateWorkout(DataManager DM){
        List<Member> allMembers = DM.members;

        int startedWorkouts = 0;
        int endedWorkouts = 0;
        int stillActive = 0;
        System.out.println(ConsoleColors.applyColor(ConsoleColors.WHITE, "  Simulating Member Activities..."));
        for(Member member:allMembers){
            int probability = random.nextInt(5);
            if(member.getActiveStatus()==true){
                if(probability == 3 || probability == 4){
                    member.setActive(false);
                    long durationOfWorkout = currentSimulatedTime - member.getLastAcitivityTimestamp();
                    if(durationOfWorkout>0){
                        member.increaseMemberHoursSpent((int)durationOfWorkout);
                        int caloriesBurnt = (int) (durationOfWorkout * (random.nextInt(5) + 3)); 
                        member.increaseMemberCaloriesBurnt(caloriesBurnt);
                        System.out.println(ConsoleColors.applyColor(ConsoleColors.BRIGHT_MAGENTA, "  - " + member.getMemberName() + " (ID: " + member.getMemberID() + ") ended workout after " + durationOfWorkout + " mins. Burned " + caloriesBurnt + " cal."));
                    }
                }
             } else {
                    if(probability == 1 || probability == 2){
                        member.setActive(true);
                        member.setLastActivityTimestamp((int)currentSimulatedTime); // Set their start time to current simulated time
                        startedWorkouts++;
                        System.out.println(ConsoleColors.applyColor(ConsoleColors.GREEN, "  + " + member.getMemberName() + " (ID: " + member.getMemberID() + ") started workout!"));
                        }
                    }
                }
                System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  " + startedWorkouts + " members started workouts, " + endedWorkouts + " members ended workouts."));
                System.out.println(ConsoleColors.applyColor(ConsoleColors.CYAN, "  Currently active members: " + (startedWorkouts + stillActive) + ".")); // Total active members in this round
            }
            
    
}
