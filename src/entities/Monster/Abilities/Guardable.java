package entities.Monster.Abilities;

import entities.Monster.Base_Monster;

public interface Guardable {
    boolean guard(Base_Monster ChosenMonster);
    String getGuard();
}
