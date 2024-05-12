package entities.Monster.Abilities;

import entities.Monster.Base_Monster;

public interface Attackable {
    boolean attack(Base_Monster otherMonster);
    String getAttack();
}
