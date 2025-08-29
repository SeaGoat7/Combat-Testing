import java.util.*;
import java.util.Scanner;
// test test
class Heroes {
    private Set<Integer> UnlockedAbilities;
    private String Name;
    private int HP;
    private int MaxHP;
    private int AD;
    private int HeroID;
    private List<String> Abilitiesnames = Arrays.asList("0: Normal Attack");

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
        Enemy.HP -= this.AD;
        System.out.println(this.Name + " Attacked " + Enemy.Name + " for " + this.AD + " damage. " + Enemy.Name + " has " + Enemy.HP + " HP left.");
    }

    public void UseAbility (Heroes Enemy, int Choice){
        switch (Choice){
            case 0:
                NormalAttack(Enemy);
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
				enemy.NormalAttack(this);
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
        Heroes TestingHero = new Heroes(1,"TestingHero",new HashSet<>(Arrays.asList(0)), 10, 10); 
        Heroes TestingDummy = new Heroes(2,"TestingDummy",new HashSet<>(Arrays.asList(0)), 100, 1);
        TestingHero.Battle(TestingDummy);
    }
}