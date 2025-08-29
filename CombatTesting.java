import java.util.*;
import java.util.Scanner;

// test test 2 
class Heroes {
    private Set<Integer> UnlockedAbilities;
    private String Name;
    private double HP;
    private double MaxHP;
    private double AD;
    private int HeroID;
    private double DF = 0, temp_DF;
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
	double total_def = Enemy.DF + Enemy.temp_DF;
	double damage_done = this.AD*(1.0 - total_def/100.0);
	Enemy.HP -= damage_done;
        System.out.println(this.Name + " Attacked " + Enemy.Name + " for " + damage_done + " damage. " + Enemy.Name + " has " + Enemy.HP + " HP left.");
    }

	public void Guard()
	{
        System.out.println(this.Name + " used Guard");
		this.temp_DF = 50;
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
				System.out.println(this.Name + "'s turn.");
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
                System.out.println(enemy.Name + "'s turn.");
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
        scanner.close();
	}
}
class CombatTesting {
    
    public static void main (String[] args) {
        Heroes TestingHero = new Heroes(1,"TestingHero",new HashSet<>(Arrays.asList(0, 1)), 10, 10); 
        Heroes TestingDummy = new Heroes(2,"TestingDummy",new HashSet<>(Arrays.asList(0,1)), 100, 1);
        TestingHero.Battle(TestingDummy);
    }
}
