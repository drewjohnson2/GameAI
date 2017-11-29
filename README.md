# Tic-Tac-Toe
## What works
* General AI
* User Interface
## What doesn't work
* Integration with server
* Deathmatch AI
# AI Implementation Information
For integration with the server specific steps need to be taken to ensure the program works properly.
These steps are outlined below.
<br><br>
Firstly, an object needs to be created for the AI like so <br><br>
```Location location = new Location();``` <br><br>
Next the contents of the character array passed from the server needs to be copied into the variable ```location.board```.
This can be done manually or with the system method ```System.arrayCopy(originArray, 0, location.board, 0, originArray.length);```
Last, the ```bestMove()``` method needs to be called to find the index that our move should be inserted into. 
This should be done as such <br><br>
```int best = location.best();``` <br><br>
Now, the variable ```best``` should be passed back to the server for populating our move.<br>
These steps should be repeated for each move. (with the exception of declaring the ```Location``` object)
