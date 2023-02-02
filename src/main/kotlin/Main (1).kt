/**
 * Main class to run game
 * @author Matthew Nova
 * @assignment Connect Four
 * @due 2/2/2022
 */
val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
    var currentState: Int = GameConstants.PLAYING
    var userInput: String = ""
    var integerValue: Int = 0


    //game loop
    do {
        FIR_board.printBoard()


        var quit = false
        var clear = false
        //accept user move
        do {
            println("Select a move: ")
            userInput = readLine()!!

            //quits game
            if (userInput == "q") {
                quit = true
                break
            }
            //clears board
            if (userInput == "c") {
                FIR_board.clearBoard()
                clear = true
                break
            }
            integerValue = userInput.toInt()

            // Validation check
        } while (integerValue > 35 || integerValue < 0)

        // quits game
        if (quit) {
            break
        }
        // Clears board continues play
        if (clear) {
            main()
        }
        // sets player position on board
        FIR_board.setMove(1, integerValue)

        // sets computer position on board
        FIR_board.setMove(2, FIR_board.computerMove)

        // Checks winner
        FIR_board.checkForWinner()

        //Prints winner RED
        if (FIR_board.checkForWinner() == 2) {
            println("RED WINS!")
            currentState = GameConstants.RED_WON

            //Prints winner BLUE
        } else if (FIR_board.checkForWinner() == 3) {
            println("YOU LOSE, BLUE WINS!")
            currentState = GameConstants.BLUE_WON
        }
        //Prints TIE
        else if (FIR_board.checkForWinner() == 1) {
            println("ITS A TIE")
            currentState = GameConstants.TIE
        }
        //Ends game loop
    } while (currentState == GameConstants.PLAYING && userInput != "q")

    println("GAME OVER")
    FIR_board.printBoard()
}

