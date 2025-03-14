Here’s a **GitHub README** draft for your **Jelly Space** project:

---

# 🪐 Jelly Space  

**Jelly Space** is an exciting and adventurous turn-based strategy game set in a vibrant galaxy. Your mission is to travel across various planets, battle powerful bosses, and restore peace to the cosmos.  

## 🎮 How to Play  
### 🌍 **Objective:**  
Conquer all five space-themed maps by defeating the boss on each map.  

### 🚀 **Gameplay Overview:**  
1. **Exploration and Navigation:**  
   - Start by selecting a map with a unique theme and boss.  
   - Navigate through the map, face challenges, and prepare for the final showdown.  

2. **Battle System:**  
   - Engage in turn-based battles similar to Pokémon.  
   - Control a team of monsters, each with unique attacks, guards, and abilities.  

3. **Using Potions:**  
   - Heal monsters or gain other benefits during battles.  
   - Potions are reusable after defeat but limited per map.  

4. **Victory and Defeat:**  
   - Win by reducing the boss’s HP to zero.  
   - Losing means trying again or strengthening your team elsewhere.  

### 🏆 **Winning the Game:**  
Successfully conquer all five maps to achieve victory!  

---

## 🛠️ Implementation Details  
### 📦 **Main Components:**  
- **Battle System** – Turn-based combat with strategic monster abilities.  
- **Map System** – Different planets with unique challenges and bosses.  
- **Monster Abilities** – Attack, guard, and unique special skills.  
- **Item System** – Potions and poisons to heal or harm during battles.  
- **Player System** – Manage monsters and items effectively.  

### 🚧 **Key Classes:**  
- `Base_Monster` – Base class for all monsters.  
- `ActionPane` – Handles battle actions like attack and guard.  
- `MapPane` – Manages exploration and map transitions.  
- `Player` – Controls player status, inventory, and active monster.  
- `Rocket` – Trigger Action Return to MapSelectPane

---

## 📸 Screenshots   
**Start Pane**
![StartPane](https://github.com/user-attachments/assets/38a07eae-f2e4-46fb-9b90-fc46ab0e36b8)
**MapSelectPane**
![MapSelectPane](https://github.com/user-attachments/assets/ff9210e7-f750-4770-ada9-d1d99d68158c) 
**MapTransition**
![MapTransition](https://github.com/user-attachments/assets/990d1ffc-bbb0-4a0a-b306-9e84bbcbb322)
**MapPane**
![MapPane](https://github.com/user-attachments/assets/7b16820b-cb6e-41b4-9254-878894bbea5c)
**BattlePane**
![BattlePane](https://github.com/user-attachments/assets/f7aa9bc3-7664-4f38-9fa4-fe7e3441ed65)



---

## 🚀 How to Run  
1. Make sure **JavaFX 22** (or at least **JavaFX 21**) is installed.  
2. Download the `.jar` file from [this link](https://drive.google.com/drive/folders/1uvOnpar4JA6-S0kbT1Om6w0IysFNWkql?usp=sharing).  
3. Run using:  
```bash
java --module-path "path-to-javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -jar Jelly_Space.jar
```

---

## 👨‍💻 Authors  
- **Chatrin Yoonchalard** – [@Mysterioucz](https://github.com/Mysterioucz)  
- **Chayaphon Kultanon**  
