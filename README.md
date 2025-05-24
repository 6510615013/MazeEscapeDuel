# MazeEscapeDuel

### Project's Files Structure

<pre>
MazeEscapeDuel/
├── src/
│   ├── main/
│   │   ├── Game.java              // main() เริ่มโปรแกรม
│   │   ├── MenuScreen.java        // ตั้งชื่อ + ปุ่มเริ่ม
│   │   ├── GameGUI.java           // หน้าหลักที่แสดง Maze
│   │   ├── Maze.java              // generate maze และเก็บข้อมูล
│   │   ├── Player.java            // ตำแหน่ง ชื่อ สี
│   │   ├── Server.java            // host server
│   │   ├── Client.java            // join server
│   │   ├── NetworkHandler.java    // จัดการ socket + thread
│   │   └── GameManager.java       // ควบคุมสถานะเกม / เช็คชัยชนะ / รีสตาร์ต
├── README.md
└── .gitignore
</pre>

### Feature

<pre>
🎮 ตั้งชื่อ Player     
🧩 Generate Maze (สุ่ม) 
🚶‍♂️ เดินใน Maze       
🖥️ GUI Swing         
🌐 เล่น 2 คนผ่าน Socket 
🏆 ประกาศผู้ชนะ        
🔄 เล่นใหม่ (Restart)  
</pre>
