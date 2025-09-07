import java.util.*;
import java.util.Scanner;

class Heroes {
    private Set<Integer> UnlockedAbilities;
    private String Name;
    private int HP;
    private int MaxHP;
    private int AD;
    private int HeroID;
    private int DF = 0, temp_DF;
    private List<String> Abilitiesnames = Arrays.asList("0: Normal Attack", "1: Guard");

    public Heroes(int HeroID, String Name,Set<Integer> UnlockedAbilities, int MaxHP, int AD) {
        this.HeroID = HeroID;
        this.Name = Name;
        this.UnlockedAbilities = UnlockedAbilities;
        this.MaxHP = MaxHP;
        this.HP = MaxHP;
        this.AD = AD;
    }

    public void ShowStats(){
        System.out.println("ID:" +this.HeroID + "\nHP:" + this.HP + "\nAD:"+ this.AD+"\nAbilities:"+this.UnlockedAbilities);
    }

    public void NormalAttack (Heroes Enemy){
	int total_def = Enemy.DF + Enemy.temp_DF;
	double damage_done = this.AD*(1.0 - total_def/100.0);
    int Final_damage = (int) damage_done;
    if (Final_damage < 1){
        Final_damage = 1; 
    }
	Enemy.HP -= Final_damage;
        System.out.println("\n" + this.Name + " Attacked " + Enemy.Name + " for " + Final_damage + " damage. " + Enemy.Name + " has " + Enemy.HP + " HP left.");
    }

	public void Guard()
	{
        System.out.println("\n" + this.Name + " used Guard");
		this.temp_DF = 55;
	}
    
    public void InTown(Heroes TestingDummy){
        Scanner scanner = new Scanner(System.in);
        boolean Checker = true;
        while (Checker) {
            System.out.println(" 0: Exit\n 1: Heal\n 2: Fight the Dummy");
            int choice = scanner.nextInt();
            switch(choice){
                case 0:
                Checker = false;
                case 1:
                this.HP = this.MaxHP;
                break;
                case 2:
                this.Battle(TestingDummy);
                break;
            }
        }
        scanner.close();
    }

    public void UseAbility (Heroes Enemy, int Choice){
        switch (Choice){
            case 0:
                NormalAttack(Enemy);
                break;
            case 1:
		        Guard();
                break;
            default:
                System.out.println("Invalid Ability");
                break;
        }
    }

    public void Battle(Heroes enemy)
	{
        Scanner scanner = new Scanner(System.in);
        
		boolean turn = true;
		while(this.HP > 0 && enemy.HP > 0)
		{
            System.out.println("");
			if(turn)
			{
				this.temp_DF = 0;
				int ability;
                for(int ID: this.UnlockedAbilities)
                {
                    System.out.println(Abilitiesnames.get(ID));
                }
                do
                {
                    System.out.print("Choose Ability: ");
                    ability = scanner.nextInt();
                } while(ability < 0 || !this.UnlockedAbilities.contains(ability));
				this.UseAbility(enemy, ability);
			}
			else
			{
                enemy.temp_DF = 0;
                int index = new Random().nextInt(enemy.UnlockedAbilities.size());
                int ability = new ArrayList<>(enemy.UnlockedAbilities).get(index);
                enemy.UseAbility(this, ability);
			}
			turn = !turn;
        }
        if(this.HP <= 0)
        {
            System.out.println(this.Name + " has been defeated!");
        }
        else
        {
            System.out.println(enemy.Name + " has been defeated!");
        }
        enemy.HP = enemy.MaxHP;
	}
}
class CombatTesting {
    
    public static void main (String[] args) {
        Heroes TestingHero = new Heroes(1,"TestingHero",new HashSet<>(Arrays.asList(0, 1)), 10, 10); 
        Heroes TestingDummy = new Heroes(2,"TestingDummy",new HashSet<>(Arrays.asList(0,1)), 100, 1);
        TestingHero.InTown(TestingDummy);
    }
}
