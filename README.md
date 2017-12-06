# Tic-Tac-Toe
## What works
* General AI
* User Interface
* Deathmatch AI
## What doesn't work
* ???
## Not Implemented
* Human user move functionality
* Integration with server
# AI Implementation Information
For integration with the server specific steps need to be taken to ensure the program works properly.
These steps are outlined below.
<br><br>
Firstly, an object needs to be created for the AI like so <br><br>
```Location location = new Location(char ourToken); // our token is x or o``` <br><br>
Next the contents of the character array passed from the server needs to be copied into the variable ```location.board```.
This can be done manually or with the system method ```System.arrayCopy(originArray, 0, location.board, 0, originArray.length);```
Last, the ```bestMove()``` method needs to be called to find the index that our move should be inserted into. 
This should be done as such <br><br>
```int best = location.best();``` <br><br>
Now, the variable ```best``` should be passed back to the server for populating our move.<br>
These steps should be repeated for each move. (with the exception of declaring the ```Location``` object)
# Deathmatch Implementation Information
Three variables should be declared
* ```int optimalMove```
* ```List<Integer> list = new ArrayList<>();```
* ```DeathMatch dm = new DeathMatch(gameBoard, ourToken);```

Next the following loop should be executed:

    for(int i = 0; i < gameBoard.length; i++)
    {
        if(gameBoard[i] == ourToken) //Our token is either x or o
        {
            list.add(i);
        }
    }

Each time it is our move the following methods should be called in this order:

    list = dm.checkMove(list);
    list = dm.removeMiddle(list);
    list = dm.removeCorners(list);
    optimalMove = dm.makeMove(list);
At this point you should be to pass the variable ```optimal move``` back to the server.
These steps should be taken each turn we take on the deathmatch.

